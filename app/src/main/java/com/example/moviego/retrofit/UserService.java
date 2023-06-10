package com.example.moviego.retrofit;

public class UserService {

    private int id_uzyt;
    private String login;
    private String haslo;
    private String email;
    private String imie;
    private String nazwisko;
    private String numer_tel;
    private String adress;
    private String data_ur;

    public UserService(int id_uzyt, String login, String haslo, String email, String imie, String nazwisko, String numer_tel, String adress, String data_ur) {
        this.id_uzyt = id_uzyt;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_tel = numer_tel;
        this.adress = adress;
        this.data_ur = data_ur;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getId_uzyt() {
        return id_uzyt;
    }

    public void setId_uzyt(int id_uzyt) {
        this.id_uzyt = id_uzyt;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumer_tel() {
        return numer_tel;
    }

    public void setNumer_tel(String numer_tel) {
        this.numer_tel = numer_tel;
    }

    public String getData_ur() {
        return data_ur;
    }

    public void setData_ur(String data_ur) {
        this.data_ur = data_ur;
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

