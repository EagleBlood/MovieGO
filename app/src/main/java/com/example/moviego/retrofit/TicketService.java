package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
public class TicketService {

    @SerializedName("tytul")
    final String title;

    @SerializedName("nr_rezerwacji")
    final String reservationNumber;

    @SerializedName("data")
    final String dateTime;

    @SerializedName("ilosc_biletow")
    final int seats;

    @SerializedName("miejsca")
    final String spots;

    @SerializedName("cena")
    final double price;

    public TicketService(String title, String reservationNumber, String dateTime, int seats, String spots, double price) {
        this.title = title;
        this.reservationNumber = reservationNumber;
        this.dateTime = dateTime;
        this.seats = seats;
        this.spots = spots;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getSeats() {
        return seats;
    }

    public String getSpots() {
        return spots;
    }

    public double getPrice() {
        return price;
    }
}
