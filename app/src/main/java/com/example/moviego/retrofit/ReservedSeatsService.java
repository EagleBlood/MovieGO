package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class ReservedSeatsService {

    @SerializedName("rzad")
    private int row;
    @SerializedName("fotel")
    private int col;

    public ReservedSeatsService(int row, int col) {
        this.row = row;
        this.col = col;
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
