package com.ismail.issuetracking.service.impl;

import com.ismail.issuetracking.dao.*;
import com.ismail.issuetracking.dto.IssueDTO;
import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.Status;
import com.ismail.issuetracking.entity.Type;
import com.ismail.issuetracking.entity.User;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Issues add(IssueDTO issueDTO) {
        Issues issues = issueDTO.toIssues();
        issues.setUser(userRepository.getOne(issueDTO.getOwner()));
        issues.setAssignTo(userRepository.getOne(issueDTO.getAssignTo()));
        issues.setType(typeRepository.getOne(issueDTO.getType()));
        issues.setStatus(statusRepository.getOne(1l));
        return issuesRepository.save(issues);
    }

    @Override
    public Issues edit(IssueDTO issueDTO) {
        Issues issues = issueDTO.toIssues();
        issues.setUser(userRepository.getOne(issueDTO.getOwner()));
        issues.setAssignTo(userRepository.getOne(issueDTO.getAssignTo()));
        issues.setType(typeRepository.getOne(issueDTO.getType()));
        issues.setStatus(statusRepository.getOne(issueDTO.getStatus()));
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
        System.out.println(">>>>>>> from findByUser");
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        } else {
            return issuesRepository.findByUser(user);
        }
    }

    @Override
    public List<Issues> findByAssigned(Long id) {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        } else {
            return issuesRepository.findByAssignTo(user);
        }
    }

    @Override
    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public List<Status> findAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public List<Issues> issuesFilter(Long id, int filterId) {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new IssueTrackingException("USER_NOT_FOUND");
        }
        List<Issues> issuesList = new ArrayList<>();
        switch (filterId) {
            case 1:
                issuesList = issuesRepository.findByUser(user);
                break;
            case 2:
                issuesList = issuesRepository.findByAssignTo(user);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                Status status = new Status();
                if (filterId == 3) {
                    status = statusRepository.findById(1L).get();
                } else if (filterId == 4) {
                    status = statusRepository.findById(3L).get();
                } else if (filterId == 5) {
                    status = statusRepository.findById(4L).get();
                } else if (filterId == 6) {
                    status = statusRepository.findById(2L).get();
                }
                issuesList = issuesRepository.findByAssignToAndStatus(user, status);
                break;
        }

        return issuesList;
    }
}
