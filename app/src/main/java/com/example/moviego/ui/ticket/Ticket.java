package com.example.moviego.ui.ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ticket {
    private String title;
    private int reservationNumber;
    private LocalDateTime dateTime;
    private int seats;
    private String spots;
    private double price;
    private boolean isExpandable;

    public Ticket(String title, int reservationNumber, String stringDateTime, int seats, List<String> spots, double price){
        this.title = title;
        this.reservationNumber = reservationNumber;
        this.seats = seats;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(stringDateTime, formatter);

        this.spots = String.join(" | ", spots);
        this.price = price;
        isExpandable = false;

    }

    public String getTitle() {
        return title;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return this.dateTime.format(formatter);
    }

}
