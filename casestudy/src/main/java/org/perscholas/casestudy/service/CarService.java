package org.perscholas.casestudy.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.entity.Car;
import org.perscholas.casestudy.formbean.CreateCarFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Service
public class CarService {
    private final CarDAO carDAO;

    @Autowired
    public CarService(CarDAO carDAO) {

        this.carDAO = carDAO;
    }


    public Car createCar(CreateCarFormBean form) {
        log.debug("id:" + form.getId());
        log.debug("manufacture:" + form.getCategory());
        log.info("model:" + form.getModel());
        log.info("year:" + form.getYear());
        log.info("price:" + form.getPrice());
        log.info("imageUrl" + form.getImageUrl());


        // if the form.id is null then this is a create - if it is not null then it is an edit
        // first we attempt to load it from the database ( basically we assume that it is going to be an edit )
        // if it was found in the database we know the incoming id was valid so we can edit it
        Car car = carDAO.findById(form.getId());

        // if the customer is null then we know that this is a create and we have to make a new object
        if (car == null) {
            car = new Car();
        }
       //Car car= new Car();
        // set the incoming values to be save to the database
        car.setCategory(form.getCategory());
        car.setModel(form.getModel());
        car.setYear(form.getYear());
        car.setPrice(form.getPrice());
        car.setImageUrl(form.getImageUrl());


        return carDAO.save(car);
    }


    public List<Car> getAllCars() {

        return carDAO.findAll();
    }


}
