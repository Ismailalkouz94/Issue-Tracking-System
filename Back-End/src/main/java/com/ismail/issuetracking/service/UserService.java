package com.ismail.issuetracking.service;

import com.ismail.issuetracking.dto.UserDTO;
import com.ismail.issuetracking.entity.User;

import java.util.Optional;

public interface UserService {
    User add(UserDTO userDTO);

    User findByUserName(String userName);

    Optional<User> findById(Long id);

    User delete(Long id);
}
