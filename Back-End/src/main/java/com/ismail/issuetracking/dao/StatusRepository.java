package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
