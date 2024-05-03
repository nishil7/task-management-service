package com.backend.backendnew.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CompositeKey implements Serializable {
    private String UserName;
    private Integer TasksID;
}
