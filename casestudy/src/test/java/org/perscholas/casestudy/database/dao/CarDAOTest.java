package org.perscholas.casestudy.database.dao;

import org.junit.jupiter.api.*;
import org.perscholas.casestudy.databse.dao.CarDAO;
import org.perscholas.casestudy.databse.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDAOTest {
    @Autowired
    private CarDAO carDAO;

    @Test
    @Order(1)
    public void createCarTest() {
        Car car = new Car();
        car.setCategory("Test Category 123");
        car.setModel("Test Model");
        car.setYear(2023);
        car.setPrice(30000.0);
        car.setImageUrl("image url");

        car = carDAO.save(car);

        Assertions.assertNotNull(car.getId(), "Car ID should not be null");
        Assertions.assertEquals("Test Category 123", car.getCategory(), "Category mismatch");
        Assertions.assertEquals("Test Model", car.getModel(), "Model mismatch");
        Assertions.assertEquals(2023, car.getYear(), "Year mismatch");
        Assertions.assertEquals(30000.0, car.getPrice(), "Price mismatch");
        Assertions.assertEquals("image url", car.getImageUrl(), "Image URL mismatch");
    }
    @Test
    @Order(2)
    public void findByModelOrCategoryTest() {
        String category = "Test Category 123";
        List<Car>  cars =carDAO.findByModelOrCategory(null,category);
        Assertions.assertEquals(1, cars.size());

        Car car = cars.get(0);

        Assertions.assertNotNull(car.getId(), "Car ID should not be null");
        Assertions.assertEquals("Test Category 123", car.getCategory(), "Category mismatch");
        Assertions.assertEquals("Test Model", car.getModel(), "Model mismatch");
        Assertions.assertEquals(2023, car.getYear(), "Year mismatch");
        Assertions.assertEquals(30000.0, car.getPrice(), "Price mismatch");
        Assertions.assertEquals("image url", car.getImageUrl(), "Image URL mismatch");

    }
    @Test
    @Order(3)
    public void deleteCategory(){

       List<Car>  car =carDAO.findByModelOrCategory("Test Model", "Test Category 123");

       Assertions.assertNotNull(car,"category should not be null");

       String category = "Test Category 123";

       int deleted = carDAO.deleteByCategory(category);

       Assertions.assertEquals(1, deleted);
    }

}
