package com.ismail.issuetracking.service.impl;

import com.ismail.issuetracking.dao.*;
import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.User;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImpl implements IssuesService {

    @Autowired
    private IssuesRepository issuesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Issues add(Issues issues) {
        issues.setUser(userRepository.getOne(issues.getUser().getId()));
        issues.setAssignTo(userRepository.getOne(issues.getAssignTo().getId()));
        issues.setType(typeRepository.getOne(issues.getType().getId()));
        issues.setStatus(statusRepository.getOne(issues.getStatus().getId()));
        return issuesRepository.save(issues);
    }

    @Override
    public Issues edit(Issues issues) {
        issues.setUser(userRepository.getOne(issues.getUser().getId()));
        issues.setAssignTo(userRepository.getOne(issues.getAssignTo().getId()));
        issues.setType(typeRepository.getOne(issues.getType().getId()));
        issues.setStatus(statusRepository.getOne(issues.getStatus().getId()));
        return issuesRepository.save(issues);
    }

    @Override
    public boolean delete(Long id) {
        if (!issuesRepository.existsById(id)) {
            throw new IssueTrackingException("ISSUE_NOT_FOUND");
        } else {
            issuesRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Issues> findAll() {
        return issuesRepository.findAll();
    }

    @Override
    public Issues find(Long id) {
        if (!issuesRepository.existsById(id)) {
            throw new IssueTrackingException("ISSUE_NOT_FOUND");
        }
        return issuesRepository.findById(id).get();
    }

    @Override
    public List<Issues> findByUser(Long id) {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        } else {
            return issuesRepository.findByUser(user);
        }
    }
}
