package com.example.moviego.ui.home;

public class Movie {

    private int imageResourceId;
    private String title;
    private double score;

    public Movie(int imageResourceId, String title, double score) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.score = score;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
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