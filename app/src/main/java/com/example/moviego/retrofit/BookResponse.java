package com.example.moviego.retrofit;

import java.util.List;

public class BookResponse {

    private int userId;
    private double price;
    private List<BookTicket> ticketList;

    public BookResponse(int userId, double price, List<BookTicket> ticketList) {
        this.userId = userId;
        this.price = price;
        this.ticketList = ticketList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<BookTicket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<BookTicket> ticketList) {
        this.ticketList = ticketList;
    }
}
