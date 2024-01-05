package org.perscholas.casestudy.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.entity.Car;
import org.perscholas.casestudy.formbean.CreateCarFormBean;
import org.perscholas.casestudy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;
@Slf4j
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarDAO carDAO;

    @GetMapping("/car/create")
    public ModelAndView createCar() {
        ModelAndView response = new ModelAndView("car/create");

        log.debug("In create car with no args - log.debug");
        log.info("In create car with no args - log.info");

        return response;
    }

    @GetMapping("/car/createSubmit")
    public ModelAndView createCarSubmit(@Valid CreateCarFormBean form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("############ In create car submit - has errors #########");
            ModelAndView response = new ModelAndView("car/create");

            // loop over the errors in the form
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        Car c = carService.createCar(form);

        ModelAndView response = new ModelAndView();
       response.setViewName("redirect:/car/edit/" + c.getId() + "?success=Car Saved Successfully");

        log.info("In create car with no args - log.info");

        return response;
    }


    @GetMapping("/car/search")
    public ModelAndView search(@RequestParam(required = false) String query) {
        ModelAndView response = new ModelAndView("car/search");
        log.debug("In the car search controller method: query={}", query);

        List<Car> cars;

        if (query != null && !query.isEmpty()) {
            cars = carDAO.findByModelOrCategory(query, query);
        } else {
            cars = new ArrayList<>(); // Or provide all cars or an empty list
        }

        response.addObject("carVar", cars);
        response.addObject("query", query);

        return response;
    }
        @GetMapping("/car/edit/{id}")
      public ModelAndView createEdit(@PathVariable int id, @RequestParam(required = false) String success ){

        ModelAndView response = new ModelAndView("car/create");

        Car car=carDAO.findById(id);

        if (!StringUtils.isEmpty(success)){
            response.addObject("success",success);
        }
        CreateCarFormBean form = new CreateCarFormBean();
        if (car != null){
            form.setId (car.getId());
            form.setCategory(car.getCategory());
            form.setModel(car.getModel());
            form.setYear(car.getYear());
            form.setPrice(car.getPrice());
            form.setImageUrl(car.getImageUrl());
        } else{
            log.warn("car with the id" + id +"was not found");
        }
        response.addObject("form",form);
        return response;

        }
    @GetMapping("/car/detail")
    public ModelAndView customerDetail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("car/detail");

        Car car = carDAO.findById(id);

        if (car == null) {
            log.warn("Customer with id " + id + " was not found");
            // in a real application you might redirect to a 404 here because the customer was nto found
            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("car", car);
        return response;
    }


    @GetMapping("/car/category/{category}")
    public ModelAndView viewCarsByCategory(@PathVariable String category) {
        ModelAndView response = new ModelAndView("car/category");
        log.debug("In the car category controller method: category={}", category);

        List<Car> cars;

        if (category != null && !category.isEmpty()) {
            cars = carDAO.findByCategoryIgnoreCase(category);
            log.debug("Number of cars for " + category + ": " + cars.size());
        } else {
            cars = new ArrayList<>(); // Or provide all cars or an empty list
        }

        response.addObject("cars", cars);
        response.addObject("category", category);

        return response;
    }


}
