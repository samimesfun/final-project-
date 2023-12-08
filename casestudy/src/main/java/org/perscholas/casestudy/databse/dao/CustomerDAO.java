package org.perscholas.casestudy.databse.dao;

import org.perscholas.casestudy.databse.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
    public Customer findById(Integer id);
}
