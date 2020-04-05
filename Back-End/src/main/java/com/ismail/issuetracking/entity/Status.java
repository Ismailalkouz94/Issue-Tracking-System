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
@Table(name = "STATUS")
public class Status implements Serializable {
    private static final long serialVersionUID = -8091879091924046845L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STATUS_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
