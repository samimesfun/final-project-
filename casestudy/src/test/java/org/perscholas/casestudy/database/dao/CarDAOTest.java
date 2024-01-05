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
        // Create a test car with the specified model and category
        Car testCar = new Car();
        testCar.setCategory("Test Category 123");
        testCar.setModel("Test Model");
        testCar.setYear(2023);
        testCar.setPrice(30000.0);
        testCar.setImageUrl("image url");
        carDAO.save(testCar);

        // Query the database using findByModelOrCategory
        List<Car> cars = carDAO.findByModelOrCategory("Test Model", "Test Category");

        System.out.println("Found " + cars.size() + " cars with the specified model or category:");

        for (Car retrievedCar : cars) {
            System.out.println("Car: " + retrievedCar);
        }

        Assertions.assertEquals(14, cars.size(), "Expected 1 car with the specified model or category, but got " + cars.size());

        Car retrievedCar = cars.get(0);

        Assertions.assertTrue(retrievedCar.getModel().toLowerCase().contains("test model"), "Model mismatch");
        Assertions.assertTrue(retrievedCar.getCategory().toLowerCase().contains("test category"), "Category mismatch");
        // ... additional assertions for other fields

        // Log information for debugging
        System.out.println("Found Car: " + retrievedCar);
    }
}
