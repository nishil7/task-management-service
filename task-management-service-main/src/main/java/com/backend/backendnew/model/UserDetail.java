package com.backend.backendnew.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class UserDetail {
    @Id
    String UserName;
    @Column
    String Name;

    @Column
    Integer TasksID;
    @Column
    String TaskName;

}

