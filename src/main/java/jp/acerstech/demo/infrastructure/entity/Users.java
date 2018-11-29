package jp.acerstech.demo.infrastructure.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name="users")
public class Users {

    @Column(name="user_id")
    @Id
    private String userId;
    @Column(name="age")
    private Integer age;
    @Column(name="name")
    private String name;
    @Column(name="department")
    private String department;
}
