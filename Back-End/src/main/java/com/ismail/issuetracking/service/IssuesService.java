package com.ismail.issuetracking.service;

import com.ismail.issuetracking.dto.IssueDTO;
import com.ismail.issuetracking.entity.Issues;
import com.ismail.issuetracking.entity.Status;
import com.ismail.issuetracking.entity.Type;

import java.util.List;

public interface IssuesService {

    Issues add(IssueDTO issueDTO);
    Issues edit(IssueDTO issueDTO);
    boolean delete(Long id);
    List<Issues> findAll();
    Issues find(Long id);
    List<Issues> findByUser(Long id);
    List<Issues> findByAssigned(Long id);
    List<Type> findAllTypes();
    List<Status> findAllStatus();
    List<Issues> issuesFilter(Long id,int filterId);


}
