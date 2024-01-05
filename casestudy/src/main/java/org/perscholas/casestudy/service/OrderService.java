package org.perscholas.casestudy.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.dao.OrderDAO;
import org.perscholas.casestudy.databse.entity.Car;
import org.perscholas.casestudy.databse.entity.Order;
import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.perscholas.casestudy.databse.entity.User;
import org.perscholas.casestudy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderService {

   @Autowired
   OrderDAO orderDAO;

    @Autowired
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    private CarDAO carDAO;



    public Order findCartOrder(User user) {

        return orderDAO.findCartOrder(user.getId());
    }


    public Order createCartOrder(User user) {
        // Check if the user already has a cart order
        Order cartOrder = orderDAO.findCartOrder(user.getId());

        if (cartOrder == null) {
            // Create a new cart order
            cartOrder = new Order();
            cartOrder.setUser(user);
            cartOrder.setStatus("cart");
            // Set other properties such as createDate, etc.

            // Save the new cart order to the database
            orderDAO.save(cartOrder);
        }

        return cartOrder; // Return the created cart order
    }


}
