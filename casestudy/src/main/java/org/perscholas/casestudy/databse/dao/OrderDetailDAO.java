package org.perscholas.casestudy.databse.dao;



import org.perscholas.casestudy.databse.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId AND od.car.id = :carId")
    OrderDetail findByOrderAndCarId(@Param("orderId") Integer orderId, @Param("carId") Long carId);

    @Query("SELECT od FROM OrderDetail od JOIN FETCH od.car WHERE od.order.id = :orderId")
    List<OrderDetail> findCarProductsByOrderId( @Param("orderId") Integer orderId);


}
