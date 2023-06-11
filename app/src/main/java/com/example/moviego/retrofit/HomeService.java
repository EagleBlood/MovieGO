package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class HomeService {

    @SerializedName("id_filmu")
    final int id_filmu;

    @SerializedName("tytul")
    final String tytul;

    @SerializedName("czas_trwania")
    final int czas_trwania;

    @SerializedName("ocena")
    final double ocena;

    @SerializedName("opis")
    final String opis;

    @SerializedName("okladka")
    final String okladka;

    @SerializedName("cena")
    final double cena;

    @SerializedName("nazwa_gatunku")
    final String nazwa_gatunku;

    @SerializedName("data")
    final String data;

    @SerializedName("pora_emisji")
    final String pora_emisja;

    @SerializedName("id_seansu")
    final int id_seansu;

    public HomeService(int id_filmu, String tytul, int czas_trwania, double ocena, String opis, String okladka, double cena, String nazwa_gatunku, String data, String pora_emisja, int id_seansu) {
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
        this.id_seansu = id_seansu;
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

    public int getId_seansu() {
        return id_seansu;
    }
}
