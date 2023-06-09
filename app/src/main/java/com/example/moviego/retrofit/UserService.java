package com.example.moviego.retrofit;

public class UserService {

    private int id_uzyt;
    private String login;
    private String haslo;
    private String email;
    private String imie;
    private String nazwisko;
    private String numer_tel;
    private String data_ur;

    public UserService(int id_uzyt, String login, String haslo, String email, String imie, String nazwisko, String numer_tel, String data_ur) {
        this.id_uzyt = id_uzyt;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_tel = numer_tel;
        this.data_ur = data_ur;
    }

    public int getId_uzyt() {
        return id_uzyt;
    }

    public void setId_uzyt(int id_uzyt) {
        this.id_uzyt = id_uzyt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
