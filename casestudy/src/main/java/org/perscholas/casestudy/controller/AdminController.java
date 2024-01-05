package org.perscholas.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.entity.Car;
import org.perscholas.casestudy.databse.entity.User;
import org.perscholas.casestudy.service.CarService;
import org.perscholas.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/admin/index")
    public ModelAndView index() {
        log.debug("Admin index page requested");
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/index");
        return response;
    }
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public AdminController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public ModelAndView adminDashboard() {
        log.debug("Admin index page requested");

        List<Car> cars = carService.getAllCars();
        List<User> users = userService.getAllUsers();

        // Create a ModelAndView object and set the view name
        ModelAndView modelAndView = new ModelAndView("admin/index"); // replace with the actual name of your JSP file

        // Add data to the model for rendering in the JSP page
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
