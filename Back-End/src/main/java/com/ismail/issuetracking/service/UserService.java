package com.ismail.issuetracking.service;

import com.ismail.issuetracking.dto.ChangePasswordDTO;
import com.ismail.issuetracking.dto.UserDTO;
import com.ismail.issuetracking.entity.Position;
import com.ismail.issuetracking.entity.Role;
import com.ismail.issuetracking.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(UserDTO userDTO);

    User edit(UserDTO userDTO);

    User findByUserName(String userName);

    List<User> find();

    User findById(Long id);

    User delete(Long id);

    List<Role> findRoles();

    List<Position> findPositions();

    void changePassword(ChangePasswordDTO changePasswordDTO);

}
