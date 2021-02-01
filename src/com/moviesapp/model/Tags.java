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
public class Tags {
    private int tagID;
    private String tagWord;
    private int tagFreq;

    public Tags() {
    }

    public Tags(int tagID, String tagWord, int tagFreq) {
        this.tagID = tagID;
        this.tagWord = tagWord;
        this.tagFreq = tagFreq;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagWord() {
        return tagWord;
    }

    public void setTagWord(String tagWord) {
        this.tagWord = tagWord;
    }

    public int getTagFreq() {
        return tagFreq;
    }

    public void setTagFreq(int tagFreq) {
        this.tagFreq = tagFreq;
    }

    @Override
    public String toString() {
        return "Tags{" + "tagID=" + tagID + ", tagWord=" + tagWord + ", tagFreq=" + tagFreq + '}';
    }
    
    
}
