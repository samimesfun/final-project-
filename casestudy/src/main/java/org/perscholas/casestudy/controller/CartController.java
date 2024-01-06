package org.perscholas.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.dao.OrderDetailDAO;
import org.perscholas.casestudy.databse.entity.Order;
import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.perscholas.casestudy.databse.entity.User;
import org.perscholas.casestudy.service.OrderDetailService;
import org.perscholas.casestudy.service.OrderService;
import org.perscholas.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class CartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    UserService userService;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @GetMapping("/order/addToCart")
    public ModelAndView addToCart(@RequestParam Long carId) {

        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Use the UserService to find the user by email
        User user = userService.findByEmail(username);

        // Check if there is an existing cart order for the user
        Order cartOrder = orderService.findCartOrder(user);

        // If no existing cart order, create a new one
        if (cartOrder == null) {
            cartOrder = orderService.createCartOrder(user);
        }

        // Check if the product is already in the cart
        OrderDetail orderDetail = orderDetailService.findOrderDetail(cartOrder.getId(), carId);

        // If the product is in the cart, increment quantity; otherwise, create a new order detail
        if (orderDetail != null) {
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
            orderDetailService.saveOrderDetail(orderDetail);
        } else {
            orderDetail = new OrderDetail();
            orderDetail.setOrder(cartOrder);
            orderDetail.setCar(carDAO.getById(carId));
            orderDetail.setQuantity(1);
            orderDetailService.saveOrderDetail(orderDetail);
        }
        log.info("Item added to cart. CarId: {}, Quantity: {}", carId, orderDetail.getQuantity());
        // Redirect to a page that shows the contents of the cart
        return new ModelAndView("redirect:/order/viewCart").addObject("cart", cartOrder);

    }

    @GetMapping("/order/viewCart")
    public ModelAndView viewCart() {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Use the UserService to find the user by email
        User user = userService.findByEmail(username);

        // Check if there is an existing cart order for the user
        Order cartOrder = orderService.findCartOrder(user);

        // If there is a cart order, get the associated car products directly from DAO
        List<OrderDetail> carProducts = orderDetailDAO.findCarProductsByOrderId(cartOrder.getId());


        ModelAndView modelAndView = new ModelAndView("order/viewCart");
        modelAndView.addObject("cartOrder", cartOrder);
        modelAndView.addObject("carProducts", carProducts);

        return modelAndView;
    }
}
