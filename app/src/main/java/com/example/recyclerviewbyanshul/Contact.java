package com.example.recyclerviewbyanshul;

public class Contact {
    private String name,gender,number;

    public Contact(String name, String gender, String number) {
        this.name = name;
        this.gender = gender;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
