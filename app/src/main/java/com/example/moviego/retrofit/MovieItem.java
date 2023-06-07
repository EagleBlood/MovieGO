package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class MovieItem {

    final int id_filmu;
    final String tytul;
    final int czas_trwania;
    final double ocena;
    final String opis;
    final String okladka;
    final double cena;
    final String nazwa_gatunku;
    final String data;
    final String pora_emisja;

    public MovieItem(int id_filmu, String tytul, int czas_trwania, double ocena, String opis, String okladka, double cena, String nazwa_gatunku, String data, String pora_emisja) {
        this.id_filmu = id_filmu;
        this.tytul = tytul;
        this.czas_trwania = czas_trwania;
        this.ocena = ocena;
        this.opis = opis;
        this.okladka = okladka;
        this.cena = cena;
        this.nazwa_gatunku = nazwa_gatunku;
        this.data = data;
        this.pora_emisja = pora_emisja;
    }

    public int getId_filmu() {
        return id_filmu;
    }

    public String getTytul() {
        return tytul;
    }

    public int getCzas_trwania() {
        return czas_trwania;
    }

    public double getOcena() {
        return ocena;
    }

    public String getOpis() {
        return opis;
    }

    public String getOkladka() {
        return okladka;
    }

    public double getCena() {
        return cena;
    }

    public String getNazwa_gatunku() {
        return nazwa_gatunku;
    }

    public String getData() {
        return data;
    }

    public String getPora_emisja() {
        return pora_emisja;
    }
}
