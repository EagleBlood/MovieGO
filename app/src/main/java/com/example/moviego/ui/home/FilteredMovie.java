package com.example.moviego.ui.home;

public class FilteredMovie {
    private Movie movie;
    private String selectedTime;

    public FilteredMovie(Movie movie, String selectedTime) {
        this.movie = movie;
        this.selectedTime = selectedTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }
}
