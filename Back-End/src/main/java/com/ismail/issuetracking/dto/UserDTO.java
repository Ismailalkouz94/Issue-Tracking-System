package com.ismail.issuetracking.dto;

import com.ismail.issuetracking.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean active;

    private String userName;

    private String password;

    private Long roleId;

    private Long positionId;

    public  User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setActive(this.active);
        user.setUserName(this.userName);
        user.setPassword(this.password);
        return user;
    }

}