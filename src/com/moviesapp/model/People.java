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
public class People {
    private int peoID;
    private String peoName;

    public People() {
    }

    
    
    public People(int peoID, String peoName) {
        this.peoID = peoID;
        this.peoName = peoName;
    }

    public int getPeoID() {
        return peoID;
    }

    public void setPeoID(int peoID) {
        this.peoID = peoID;
    }

    public String getPeoName() {
        return peoName;
    }

    public void setPeoName(String peoName) {
        this.peoName = peoName;
    }

    @Override
    public String toString() {
        return "People{" + "peoID=" + peoID + ", peoName=" + peoName + '}';
    }
    
    
}
