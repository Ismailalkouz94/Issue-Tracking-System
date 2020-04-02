package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
