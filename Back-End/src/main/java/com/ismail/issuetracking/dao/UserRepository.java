package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    void deleteById(Long id);
}
