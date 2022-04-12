package com.example.restapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "account")
public class Account {

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;


}
