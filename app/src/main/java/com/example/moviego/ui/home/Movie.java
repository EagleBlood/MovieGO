package com.example.moviego.ui.home;

import android.graphics.Bitmap;

public class Movie {

    private Bitmap image;
    private String title;
    private double score;

    public Movie(Bitmap imageResourceId, String title, double score) {
        this.image = imageResourceId;
        this.title = title;
        this.score = score;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}