package com.ismail.issuetracking.model;

import com.ismail.issuetracking.entity.User;
import lombok.*;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class LoginResponse  {
    private User user;
    private JwtResponse jwtResponse;
}
