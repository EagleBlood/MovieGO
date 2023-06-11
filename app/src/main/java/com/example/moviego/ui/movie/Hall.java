package com.example.moviego.ui.movie;

public class Hall {

    private int seatId;

    private int row;

    private int col;

    public Hall(int seatId, int row, int col) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
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

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
