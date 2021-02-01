/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.dao;

/**
 *
 * @author Oumayma
 */
public class DAOException extends RuntimeException{
    
    public DAOException(String errMsg){
        super( errMsg );
    }
    
    public DAOException( String errMsg, Throwable cause){
        super(errMsg, cause);
    }
    
    public DAOException(Throwable cause){
        super( cause );
    }
}
