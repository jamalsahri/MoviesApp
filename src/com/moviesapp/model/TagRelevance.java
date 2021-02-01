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
public class TagRelevance {
    private int movID;
    private int tagID;
    private double relevance;

    public TagRelevance() {
    }

    
    
    public TagRelevance(int movID, int tagID, double relevance) {
        this.movID = movID;
        this.tagID = tagID;
        this.relevance = relevance;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    @Override
    public String toString() {
        return "TagRelevance{" + "movID=" + movID + ", tagID=" + tagID + ", relevance=" + relevance + '}';
    }
    
    
}
