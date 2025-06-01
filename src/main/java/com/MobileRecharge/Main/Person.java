package com.MobileRecharge.Main;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {

    @Id
    private String id;

    private String name;

    private String password;

    public Person() {}

    public Person(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
