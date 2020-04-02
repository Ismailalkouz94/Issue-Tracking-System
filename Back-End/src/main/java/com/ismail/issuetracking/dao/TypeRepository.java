package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Status;
import com.ismail.issuetracking.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
}
