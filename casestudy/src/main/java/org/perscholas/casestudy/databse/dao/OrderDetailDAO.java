package org.perscholas.casestudy.databse.dao;


import org.perscholas.casestudy.databse.entity.Order;
import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
    public  OrderDetail findById(Integer id);
}
