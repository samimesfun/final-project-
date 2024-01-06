package org.perscholas.casestudy.database.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.dao.OrderDAO;
import org.perscholas.casestudy.databse.dao.OrderDetailDAO;
import org.perscholas.casestudy.databse.dao.UserDAO;
import org.perscholas.casestudy.databse.entity.Car;
import org.perscholas.casestudy.databse.entity.Order;
import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.perscholas.casestudy.databse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderDAOTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void createOrder(){

        User user = new User();
        user.setPassword("test password");
        user.setEmail("test email");
        userDAO.save(user);

        Order order = new Order();
        order.setStatus("cart");
        order.setUser(user);
        orderDAO.save(order);


      Assertions.assertNotNull(order.getId());
      Assertions.assertNotNull(user.getId());

    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void findCartOrder() {
        // Generate a unique email address for the test
        String uniqueEmail = "test" + System.currentTimeMillis() + "@example.com";
        String uniquePassword = "test" + System.currentTimeMillis() + "password";
        User user = new User();
        user.setPassword(uniquePassword);
        user.setEmail(uniqueEmail);
        userDAO.save(user);


        // Create and save a new Order with status 'cart' for the user
        Order order = new Order();
        order.setStatus("cart");
        order.setUser(user);
        orderDAO.save(order);

        // Call findCartOrder with the user ID
        Order foundOrder = orderDAO.findCartOrder(user.getId());

        // Assert that the foundOrder is not null
        Assertions.assertNotNull(foundOrder, "Cart order should not be null");

        // Assert that the foundOrder status is 'cart'
        Assertions.assertEquals("cart", foundOrder.getStatus(), "Order status should be 'cart'");

        // Assert that the foundOrder's user is the same as the created user
        Assertions.assertEquals(user.getId(), foundOrder.getUser().getId(), "User IDs should match");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void deleteByEmailIgnoreCase(){

        //given
        String email = "test email";

        //when
        int deleted = userDAO.deleteByEmailIgnoreCase(email);

        //then
        Assertions.assertEquals(1,deleted);


    }

}
