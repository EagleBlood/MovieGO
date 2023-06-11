package com.example.moviego.ui.ticket;

public class Ticket {
    private String title;
    private String reservationNumber;
    private String dateTime;
    private int seats;
    private String spots;
    private double price;
    private boolean isExpandable;

    public Ticket(String title, String reservationNumber, String dateTime, int seats, String spots, double price){
        this.title = title;
        this.reservationNumber = reservationNumber;
        this.dateTime = dateTime;
        this.seats = seats;
        this.spots = spots;
        this.price = price;
        isExpandable = false;

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

    public void setDateTime(String dateTime) {
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

    public void setReservationNumber(String reservationNumber) {
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


}
