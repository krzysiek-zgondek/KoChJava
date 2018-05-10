package com.koch.java.kochjava.base.model;

import io.objectbox.annotation.BaseEntity;
import io.objectbox.annotation.Id;

@BaseEntity
public class BaseModel {
    @Id(assignable = true) public long id;
}
