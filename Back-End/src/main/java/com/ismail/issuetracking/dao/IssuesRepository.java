package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuesRepository extends JpaRepository<Issues, Long> {
    void deleteById(Issues issues);
    List<Issues> findByUser(User user);

}
