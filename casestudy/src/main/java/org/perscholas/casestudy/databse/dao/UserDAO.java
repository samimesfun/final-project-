package org.perscholas.casestudy.databse.dao;

import org.perscholas.casestudy.databse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    public User findByEmailIgnoreCase(String email);
}
