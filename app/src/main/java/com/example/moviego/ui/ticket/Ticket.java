package com.example.moviego.ui.ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String title;
    private int reservationNumber;
    private LocalDateTime dateTime;
    private int seats;

    public Ticket(String title, int reservationNumber, String stringDateTime, int seats){
        this.title = title;
        this.reservationNumber = reservationNumber;
        this.seats = seats;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(stringDateTime, formatter);

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

    public int getSeats() {
        return seats;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return this.dateTime.format(formatter);
    }

}
