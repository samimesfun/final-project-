package org.perscholas.casestudy.database.dao;
import org.junit.jupiter.api.*;
import org.perscholas.casestudy.databse.dao.UserDAO;
import org.perscholas.casestudy.databse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    public void createUserTest(){
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test password");

        user = userDAO.save(user);
    }
    @Test
    @Order(2)
    public void findByEmailTest(){
        //given
        String email = "test@example.com";

       // when
       User user = userDAO.findByEmailIgnoreCase(email);

       // then
       Assertions.assertNotNull(user);
       Assertions.assertEquals("test@example.com",user.getEmail());
    }
    @Test
    @Order(3)
    public void deleteByEmailIgnoreCase(){

        //given
        String email = "test@example.com";

        //when
        int deleted = userDAO.deleteByEmailIgnoreCase(email);

       //then
       Assertions.assertEquals(1,deleted);


    }
}
