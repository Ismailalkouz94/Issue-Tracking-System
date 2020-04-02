package com.ismail.issuetracking.model;

import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;

}