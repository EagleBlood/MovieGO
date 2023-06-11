package com.example.moviego.ui.login;

public class UserData {

    private String password;
    private String email;
    private String name;
    private String surname;
    private String number;
    private String address;
    private String birthdate;

    public UserData(String password, String email, String name, String surname, String number, String address, String birthdate) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
