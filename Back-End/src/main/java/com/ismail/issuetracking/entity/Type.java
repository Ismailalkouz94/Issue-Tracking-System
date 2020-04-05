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
@Table(name = "TYPE")
public class Type implements Serializable {
    private static final long serialVersionUID = -8091879092924046844L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TYPE_ID")
    private Long id;

    @Column(name = "NAME")
    private String neme;
}
