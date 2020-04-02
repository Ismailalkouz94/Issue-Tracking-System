package com.ismail.issuetracking.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ISSUES")
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISSUE_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCPTION")
    private String descption;

    @Column(name = "ATTACHMENT")
    private String attachment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ASSIGN_TO", referencedColumnName = "USER_ID")
    private User assignTo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "TYPE_ID")
    private Type type;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
