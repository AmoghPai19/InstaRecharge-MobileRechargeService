package com.MobileRecharge.Main;

public class Admin extends Person {

    public Admin(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin ID: " + getId() + ", Name: " + getName());
    }
}
