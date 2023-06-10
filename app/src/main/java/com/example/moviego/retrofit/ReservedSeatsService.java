package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class ReservedSeatsService {

    @SerializedName("id_miejsca")
    final int seatId;

    public ReservedSeatsService(int seatId) {
        this.seatId = seatId;
    }

    public int getSeatId() {
        return seatId;
    }
}
