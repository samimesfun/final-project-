package org.perscholas.casestudy.databse.dao;

import org.perscholas.casestudy.databse.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long>  {
    public List<UserRole> findByUserId(Integer userId);
}
