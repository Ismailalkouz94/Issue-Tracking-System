package com.ismail.issuetracking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = -8091879091924046846L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
