package org.perscholas.casestudy.databse.dao;


import jakarta.transaction.Transactional;
import org.perscholas.casestudy.databse.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = 'cart'")
    Order findCartOrder(@Param("userId") Integer userId);

}


