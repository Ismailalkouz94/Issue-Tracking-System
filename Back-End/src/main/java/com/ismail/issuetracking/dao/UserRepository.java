package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    void deleteById(Long id);
    Optional<User> findByUserNameAndIdNot(String userName,Long id);
    Optional<User> findByEmailAndIdNot(String email,Long id);
}
