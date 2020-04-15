package com.ismail.issuetracking.service.impl;

import com.ismail.issuetracking.dao.PositionRepository;
import com.ismail.issuetracking.dao.RoleRepository;
import com.ismail.issuetracking.dao.UserRepository;
import com.ismail.issuetracking.dto.UserDTO;
import com.ismail.issuetracking.entity.Position;
import com.ismail.issuetracking.entity.Role;
import com.ismail.issuetracking.entity.User;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User add(UserDTO userDTO) {
        if (userRepository.findByUserName(userDTO.getUserName()) != null) {
            throw new IssueTrackingException("USER NAME IS EXIST");
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IssueTrackingException("EMAIL IS EXIST");
        }

        User user = userDTO.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPosition(positionRepository.findById(userDTO.getPositionId()).get());
        user.setRole(roleRepository.findById(userDTO.getRoleId()).get());
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public User edit(UserDTO userDTO) {
        if (userRepository.findByUserNameAndIdNot(userDTO.getUserName(),userDTO.getId()).isPresent()) {
            throw new IssueTrackingException("USER NAME IS EXIST");
        }
        if (userRepository.findByEmailAndIdNot(userDTO.getEmail(),userDTO.getId()).isPresent() ) {
            throw new IssueTrackingException("EMAIL IS EXIST");
        }

        User user = userDTO.toUser();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPosition(positionRepository.findById(userDTO.getPositionId()).get());
        user.setRole(roleRepository.findById(userDTO.getRoleId()).get());
//        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        User user = null;
        user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        } else {
            return user;
        }
    }

    @Override
    public List<User> find() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User delete(Long id) {
        User user = null;
        if (!userRepository.existsById(id)) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        } else {
            userRepository.deleteById(id);
        }
        return user;
    }

    @Override
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Position> findPositions() {
        return positionRepository.findAll();
    }
}
