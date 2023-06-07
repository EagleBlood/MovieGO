package com.example.moviego.retrofit;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

public class HomeService {

    @SerializedName("id_filmu")
    final String id_filmu;

    @SerializedName("tytul")
    final String tytul;

    @SerializedName("czas_trwania")
    final String czas_trwania;

    @SerializedName("ocena")
    final String ocena;

    @SerializedName("opis")
    final String opis;

    @SerializedName("okladka")
    final String okladka;

    @SerializedName("cena")
    final String cena;

    @SerializedName("nazwa_gatunku")
    final String nazwa_gatunku;

    @SerializedName("data")
    final String data;

    @SerializedName("pora_emisji")
    final String pora_emisja;

    public HomeService(String id_filmu, String tytul, String czas_trwania, String ocena, String opis, String okladka, String cena, String nazwa_gatunku, String data, String pora_emisja) {
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

    public String getId_filmu() {
        return id_filmu;
    }

    public String getTytul() {
        return tytul;
    }

    public String getCzas_trwania() {
        return czas_trwania;
    }

    public String getOcena() {
        return ocena;
    }

    public String getOpis() {
        return opis;
    }

    public String getOkladka() {
        return okladka;
    }

    public String getCena() {
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
