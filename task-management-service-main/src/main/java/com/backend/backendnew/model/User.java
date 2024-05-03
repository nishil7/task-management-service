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
public class User {
//i hv defined a model user which i get from user_profile_service
//    not imported role and password
    @Id
    String UserName;
    @Column
    String Name;
}
