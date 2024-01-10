package org.perscholas.casestudy.databse.dao;

import jakarta.transaction.Transactional;
import org.perscholas.casestudy.databse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public User findByEmailIgnoreCase(String email);

//    @Query(value = "DELETE FROM users WHERE id = :userId", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteByEmailIgnoreCase(String email);
}
