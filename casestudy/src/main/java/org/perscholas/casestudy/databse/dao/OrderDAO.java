package org.perscholas.casestudy.databse.dao;


import org.perscholas.casestudy.databse.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
    public Order findById(Integer id);
}
