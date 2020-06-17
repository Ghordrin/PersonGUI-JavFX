package com.yannick.Person;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String lastName;
    private String email;
    private String gender;
    private String profession;

    public Person(String name, String lastName, String email, String gender, String profession) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.profession = profession;
    }
}
