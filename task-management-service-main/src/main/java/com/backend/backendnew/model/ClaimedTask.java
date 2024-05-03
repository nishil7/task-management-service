package com.backend.backendnew.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="Claimed_Tasks")
@IdClass(CompositeKey.class)
public class ClaimedTask {

    @Id
    @Column(name = "UserName")
    String UserName;

    @Id
    @Column(name = "TasksID")
    Integer TasksID;

    @Column(name = "IsActive")
    Integer IsActive;

}
