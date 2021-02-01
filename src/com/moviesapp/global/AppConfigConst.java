/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.global;

/**
 *
 * @author Oumayma
 */
public interface AppConfigConst {
   
    public static final String DIST = "/com/moviesapp/media/pictures/";
    
    //Database CONST config
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_NAME = "movielens";
    public static final String URL    = "jdbc:mysql://localhost:3308/"+DB_NAME;
    public static final String USER   = "root";
    public static final String PWD    = "JES@db/*336782*/";
    
    // TEMPLATE CONST
    public static final String VIEWS_PATH = "/com/moviesapp/view/";
    public static final String MEDIA_PATH = "/com/moviesapp/media/";
    
}
