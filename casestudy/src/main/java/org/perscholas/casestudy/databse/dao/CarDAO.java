package org.perscholas.casestudy.databse.dao;

import jakarta.transaction.Transactional;
import org.perscholas.casestudy.databse.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Long> {

    public Car findById(Integer id);

    @Query("SELECT c FROM Car c WHERE LOWER(c.category) = LOWER(:category)")
    List<Car> findByCategoryIgnoreCase(@Param("category") String category);


    @Query("SELECT c FROM Car c WHERE LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%')) OR LOWER(c.category) LIKE LOWER(CONCAT('%', :category, '%'))")
    List<Car> findByModelOrCategory(@Param("model") String model, @Param("category") String category);

//    List<Car> findAll();

    @Modifying
    @Transactional
    int deleteByCategory(String category);
}
