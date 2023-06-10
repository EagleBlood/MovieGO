package com.example.moviego.retrofit;

public class BookTicket {
    private int id_miejsca;
    private int id_seansu;
    private double cena;

    public BookTicket(int id_miejsca, int id_seansu, double cena) {
        this.id_miejsca = id_miejsca;
        this.id_seansu = id_seansu;
        this.cena = cena;
    }

    public int getId_miejsca() {
        return id_miejsca;
    }

    public int getId_seansu() {
        return id_seansu;
    }

    public double getCena() {
        return cena;
    }

    public void setId_miejsca(int id_miejsca) {
        this.id_miejsca = id_miejsca;
    }

    public void setId_seansu(int id_seansu) {
        this.id_seansu = id_seansu;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
