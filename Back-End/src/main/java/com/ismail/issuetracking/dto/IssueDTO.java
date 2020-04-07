package com.ismail.issuetracking.dto;

import com.ismail.issuetracking.entity.Issues;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class IssueDTO {

    private String title;
    private String descrption;
    private Long owner;
    private Long assignTo;
    private Long type;

    public Issues toIssues() {
        Issues issues = new Issues();
        issues.setTitle(this.title);
        issues.setDescption(this.descrption);
        return issues;
    }

}
