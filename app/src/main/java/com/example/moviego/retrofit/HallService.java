package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class HallService {

    @SerializedName("id_miejsca")
    final int seatId;

    @SerializedName("rzad")
    final int row;

    @SerializedName("fotel")
    final int armchair;

    public HallService(int seatId, int row, int armchair) {
        this.seatId = seatId;
        this.row = row;
        this.armchair = armchair;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public int getArmchair() {
        return armchair;
    }
}
