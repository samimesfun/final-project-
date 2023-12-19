package org.perscholas.casestudy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.CustomerDAO;
import org.perscholas.casestudy.databse.entity.Customer;
import org.perscholas.casestudy.formbean.CreateCustomerFormBean;
import org.perscholas.casestudy.security.AuthenticatedUserService;
import org.perscholas.casestudy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Slf4j
@Controller
public class CustomerController {
    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @GetMapping("/customer/search")
    public ModelAndView search(@RequestParam(required = false) String firstNameSearch,
                               @RequestParam(required = false) String lastNameSearch) {
        ModelAndView response = new ModelAndView("customer/search");

        log.debug("in the customer search controller method : first name = " + firstNameSearch + " last name = " + lastNameSearch);

        if (!StringUtils.isEmpty(firstNameSearch) || !StringUtils.isEmpty(lastNameSearch)) {

            response.addObject("firstNameSearch", firstNameSearch);
            response.addObject("lastNameSearch", lastNameSearch);

            if (!StringUtils.isEmpty(firstNameSearch)) {
                firstNameSearch = "%" + firstNameSearch + "%";
            }

            if (!StringUtils.isEmpty(lastNameSearch)) {
                lastNameSearch = "%" + lastNameSearch + "%";
            }

            // we only want to do this code if the user has entered either a first name or a last name
            List<Customer> customers = customerDao.findByFirstNameOrLastName(firstNameSearch, lastNameSearch);

            response.addObject("customerVar", customers);


            for (Customer customer : customers) {
                log.debug("customer: id = " + customer.getId() + " last name = " + customer.getLastName());
            }
        }

        return response;
    }



    @GetMapping("/customer/edit/{customerId}")
    public ModelAndView editCustomer(@PathVariable int customerId,
                                     @RequestParam(required = false) String success) {
        log.info("############# In /customer/edit ##################");
        ModelAndView response = new ModelAndView("customer/create");

        Customer customer = customerDao.findById(customerId);

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }

        CreateCustomerFormBean form = new CreateCustomerFormBean();

        if (customer != null) {
            form.setId(customer.getId());
            form.setFirstName(customer.getFirstName());
            form.setLastName(customer.getLastName());
            form.setPhone(customer.getPhone());
            form.setCity(customer.getCity());
          //  form.setImageUrl(customer.getImageUrl());
        } else {
            log.warn("Customer with id " + customerId + " was not found");
        }

        response.addObject("form", form);

        return response;

    }

    @GetMapping("/customer/create")
    public ModelAndView createCustomer() {
        ModelAndView response = new ModelAndView("customer/create");

        log.debug("In create customer with no args - log.debug");
        log.info("In create customer with no args - log.info");

        return response;
    }


    // the action attribute on the form tag is set to /customer/createSubmit so this method will be called when the user clicks the submit button
    @GetMapping("/customer/createSubmit")
    public ModelAndView createCustomerSubmit(@Valid CreateCustomerFormBean form,  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("############ In create customer submit - has errors #########");
            ModelAndView response = new ModelAndView("customer/create");

           // loop over the errors in the form
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        log.info("######### In create customer submit - no error found ###############");

        Customer c = customerService.createCustomer(form);

        // the view name can either be a jsp file name or a redirect to another controller method
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/customer/edit/" + c.getId() + "?success=Customer Saved Successfully");

        return response;


    }
    @GetMapping("/customer/detail")
    public ModelAndView customerDetail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("customer/detail");

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            log.warn("Customer with id " + id + " was not found");
            // in a real application you might redirect to a 404 here because the customer was nto found
            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("customer", customer);
        return response;
    }


}