package com.ismail.issuetracking.service;

import com.ismail.issuetracking.entity.Issues;

import java.util.List;

public interface IssuesService {

    Issues add(Issues issues);
    Issues edit(Issues issues);
    boolean delete(Long id);
    List<Issues> findAll();
    Issues find(Long id);
    List<Issues> findByUser(Long id);


}
