package com.ismail.issuetracking.dao;

import com.ismail.issuetracking.entity.Position;
import com.ismail.issuetracking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
