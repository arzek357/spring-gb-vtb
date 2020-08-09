package com.vtb.zolotarev.homeWork5.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    @Override
    public String toString() {
        return String.format("User [id = %d, name = %s, age=%d]", id, name, age);
    }

    public User(String name,int age){
        this.name=name;
        this.age=age;
    }
}
