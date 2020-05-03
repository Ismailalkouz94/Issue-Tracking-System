package com.ismail.issuetracking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ChangePasswordDTO {

    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
