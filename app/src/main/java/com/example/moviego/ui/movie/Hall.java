package com.example.moviego.ui.movie;

import com.google.gson.annotations.SerializedName;

public class Hall {

    private int seatId;

    private int row;

    private int armchair;

    public Hall(int seatId, int row, int armchair) {
        this.seatId = seatId;
        this.row = row;
        this.armchair = armchair;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getArmchair() {
        return armchair;
    }

    public void setArmchair(int armchair) {
        this.armchair = armchair;
    }
}
