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
public class Role {
    private int rolID;
    private String rolName;
    private int movID;
    private int peopleID;

    public Role() {
    }

    
    
    public Role(int rolID, String rolName, int movID, int peopleID) {
        this.rolID = rolID;
        this.rolName = rolName;
        this.movID = movID;
        this.peopleID = peopleID;
    }

    public int getRolID() {
        return rolID;
    }

    public void setRolID(int rolID) {
        this.rolID = rolID;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public int getPeopleID() {
        return peopleID;
    }

    public void setPeopleID(int peopleID) {
        this.peopleID = peopleID;
    }

    @Override
    public String toString() {
        return "Role{" + "rolID=" + rolID + ", rolName=" + rolName + ", movID=" + movID + ", peopleID=" + peopleID + '}';
    }
    
    
}
