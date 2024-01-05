package org.perscholas.casestudy.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.perscholas.casestudy.databse.dao.UserDAO;
import org.perscholas.casestudy.databse.entity.User;
import org.perscholas.casestudy.formbean.RegisterUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDAO userDao;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(RegisterUserFormBean form) {
        User user = new User();

        user.setEmail(form.getEmail().toLowerCase());

        String encoded = passwordEncoder.encode(form.getPassword());
        log.debug("Encoded password: " + encoded);
        user.setPassword(encoded);

        return userDao.save(user);
    }
    public User findById(Long userId) {
        return userDao.findById(userId).orElse(null);
    }
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User findByEmail(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

}
