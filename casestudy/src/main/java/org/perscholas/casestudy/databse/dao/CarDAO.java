package org.perscholas.casestudy.databse.dao;

import org.perscholas.casestudy.databse.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDAO extends JpaRepository<Car, Long> {

    public Car findById(Integer id);
}
