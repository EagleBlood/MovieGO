package com.example.moviego.ui.home;

public class MovieItem {

    private String id_filmu;
    private String tytul;
    private String czas_trwania;
    private String ocena;
    private String opis;
    private String okladka;
    private String cena;
    private String nazwa_gatunku;
    private String data;
    private String pora_emisji;

    public MovieItem(String id_filmu, String tytul, String czas_trwania, String ocena, String opis, String okladka, String cena, String nazwa_gatunku, String data, String pora_emisji) {
        this.id_filmu = id_filmu;
        this.tytul = tytul;
        this.czas_trwania = czas_trwania;
        this.ocena = ocena;
        this.opis = opis;
        this.okladka = okladka;
        this.cena = cena;
        this.nazwa_gatunku = nazwa_gatunku;
        this.data = data;
        this.pora_emisji = pora_emisji;
    }

    public String getId_filmu() {
        return id_filmu;
    }

    public void setId_filmu(String id_filmu) {
        this.id_filmu = id_filmu;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getCzas_trwania() {
        return czas_trwania;
    }

    public void setCzas_trwania(String czas_trwania) {
        this.czas_trwania = czas_trwania;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getOkladka() {
        return okladka;
    }

    public void setOkladka(String okladka) {
        this.okladka = okladka;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getNazwa_gatunku() {
        return nazwa_gatunku;
    }

    public void setNazwa_gatunku(String nazwa_gatunku) {
        this.nazwa_gatunku = nazwa_gatunku;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPora_emisji() {
        return pora_emisji;
    }

    public void setPora_emisji(String pora_emisji) {
        this.pora_emisji = pora_emisji;
    }
}
