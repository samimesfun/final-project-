package org.perscholas.casestudy.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.databse.dao.OrderDetailDAO;
import org.perscholas.casestudy.databse.entity.Order;
import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailDAO.getOrderDetailsByOrderId(orderId);
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {

        return orderDetailDAO.save(orderDetail);
    }
    public OrderDetail findOrderDetail(Integer orderId, Long carId) {
        return orderDetailDAO.findByOrderAndCarId(orderId, carId);
    }

}
