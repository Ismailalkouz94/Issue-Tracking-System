package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.Status;
import com.ismail.issuetracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssuesRepository extends JpaRepository<Issues, Long> {
    void deleteById(Issues issues);
    List<Issues> findByUser(User user);
    List<Issues> findByAssignTo(User user);
    List<Issues> findByAssignToAndStatus(User user, Status status);

}
