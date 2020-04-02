package com.ismail.issuetracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    @Email(message = "*Please provide a valid Email")
    private String email;

    @Column(name = "ACIIVE", columnDefinition = "boolean default false")
    private Boolean active;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

//    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "POS_ID")
    private Position position;


}
