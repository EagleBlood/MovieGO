package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class UserService {

    @SerializedName("id_uzyt")
    final int id;

    @SerializedName("login")
    final String login;

    @SerializedName("haslo")
    final String password;

    @SerializedName("email")
    final String email;

    @SerializedName("imie")
    final String name;

    @SerializedName("nazwisko")
    final String surname;

    @SerializedName("numer_tel")
    final String number;

    @SerializedName("adres")
    final String address;

    @SerializedName("data_ur")
    final String birthdate;

    public UserService(int id, String login, String password, String email, String name, String surname, String number, String address, String birthdate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthdate() {
        return birthdate;
    }
}

