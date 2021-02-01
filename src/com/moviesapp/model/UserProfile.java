/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.model;

/**
 *
 * @author Oumayma
 */
public class UserProfile {
    private int id;
    private StringBuffer data;
    private double average;
    
    public UserProfile() {
    }
    
    public UserProfile(int id, StringBuffer data, double average) {
        this.id = id;
        this.data = data;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringBuffer getData() {
        return data;
    }

    public void setData(StringBuffer data) {
        this.data = data;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "UserProfile{" + "id=" + id + ", data=" + data + ", average=" + average + '}';
    }

    
    
    
}
