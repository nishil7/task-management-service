package com.backend.backendnew.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer TasksID;
    @Column
    String TaskName;
    @Column
    Integer StatusID;
    @Column
    Integer PriorityID;
    @Column
    String Description;
    @Column
    String CompletedBy;



}
