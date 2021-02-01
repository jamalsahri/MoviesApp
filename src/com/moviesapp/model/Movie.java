/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.model;

import java.util.Date;

/**
 *
 * @author Oumayma
 */
public class Movie {
    private int movID;
    private String imdb_id; 
    private String title;
    private String poster;
    private String genres;
    private String releaseDate;
    private String type;
    private String description;
    private double rating;
    private int year;
    private int active;

    public Movie() {
    }
    
    public Movie(int movID, String imdb_id, String title, String poster, String genres, String releaseDate, String type, String description, double rating, int year, int active) {
        this.movID = movID;
        this.imdb_id = imdb_id;
        this.title = title;
        this.poster = poster;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.type = type;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.active = active;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Movie{" + "movID=" + movID + ", imdb_id=" + imdb_id + ", title=" + title + ", poster=" + poster + ", genres=" + genres + ", releaseDate=" + releaseDate + ", type=" + type + ", description=" + description + ", rating=" + rating + ", year=" + year + ", active=" + active + '}';
    }
    
    
    
}
