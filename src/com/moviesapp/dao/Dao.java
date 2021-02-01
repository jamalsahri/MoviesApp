/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.dao;

import com.moviesapp.global.AppConfigConst;
import com.moviesapp.model.Movie;
import com.moviesapp.model.People;
import com.moviesapp.model.Role;
import com.moviesapp.util.APPException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oumayma
 */
public class Dao {
    
    private Connection conn;
    private Statement stm;
    private static Dao instance;
    
    private Dao(){
        try{
            Class.forName( AppConfigConst.DRIVER ).newInstance();
            conn = DriverManager.getConnection(AppConfigConst.URL, AppConfigConst.USER, AppConfigConst.PWD);
            stm = conn.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static Dao getInstance(){
        try{
            if( instance == null){
                instance = new Dao();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return instance;
    }
    
    public Movie getMovieByID(final int movid){
        Movie movie = null;
        
        try{
            String query = "SELECT * FROM `movie` WHERE mov_id="+movid+";";
            
            try(ResultSet rs= stm.executeQuery( query )){
                rs.next();
                movie = new Movie();
                movie.setMovID(rs.getInt(1));
                movie.setImdb_id(rs.getString(2));
                movie.setTitle(rs.getString(3));
                movie.setPoster(rs.getString(4));
                movie.setGenres(rs.getString(5));
                movie.setReleaseDate(rs.getString(6));
                movie.setType(rs.getString(7));
                movie.setDescription(rs.getString(8));
                movie.setRating(rs.getDouble(9));
                movie.setYear(rs.getInt(10));
                movie.setActive(rs.getInt(11));
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return movie;
    }
    
    
    
    public List<Movie> getPersonMovies(final People people){
        List<Movie> movies  = new ArrayList<>();
        String query = "SELECT * FROM `role` INNER JOIN `movie` ON role.mov_id=movie.mov_id WHERE role.peo_id="+people.getPeoID()+";";
        try(ResultSet rs = stm.executeQuery(query)){
            while( rs.next() ){
                Movie movie = new Movie();
                movie.setMovID(rs.getInt("mov_id"));
                movie.setImdb_id(rs.getString("imdb_id"));
                movie.setTitle(rs.getString("title"));
                movie.setPoster(rs.getString("poster"));
                movie.setGenres(rs.getString("genres"));
                movie.setReleaseDate(rs.getString("release_date"));
                movie.setType(rs.getString("type"));
                movie.setDescription(rs.getString("synopsis"));
                movie.setRating(rs.getDouble("rating"));
                movie.setYear(rs.getInt("year"));
                movie.setActive(rs.getInt("active"));
                movies.add( movie );
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
        return movies;
    }
    
    public Movie getMovieByTitle(final String title){
        Movie movie = null;
        
        try{
            String query = "SELECT * FROM `movie` WHERE title='%"+title+"%';";
            
            try(ResultSet rs= stm.executeQuery( query )){
                rs.next();
                movie = new Movie();
                movie.setMovID(rs.getInt(1));
                movie.setImdb_id(rs.getString(2));
                movie.setTitle(rs.getString(3));
                movie.setPoster(rs.getString(4));
                movie.setGenres(rs.getString(5));
                movie.setReleaseDate(rs.getString(6));
                movie.setType(rs.getString(7));
                movie.setDescription(rs.getString(8));
                movie.setRating(rs.getDouble(9));
                movie.setYear(rs.getInt(10));
                movie.setActive(rs.getInt(11));
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return movie;
    }
    
    public List<Movie> getMovies(){
        List<Movie> movies  = new ArrayList<>();
        String query = "SELECT * FROM movie;";
        try(ResultSet rs = stm.executeQuery(query)){
            while( rs.next() ){
                Movie movie = new Movie();
                movie.setMovID(rs.getInt(0));
                movie.setImdb_id(rs.getString(1));
                movie.setTitle(rs.getString(2));
                movie.setPoster(rs.getString(3));
                movie.setGenres(rs.getString(4));
                movie.setReleaseDate(rs.getString(5));
                movie.setType(rs.getString(6));
                movie.setDescription(rs.getString(7));
                movie.setRating(rs.getDouble(8));
                movie.setYear(rs.getInt(9));
                movie.setActive(rs.getInt(10));
                movies.add( movie );
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
        return movies;
    }
    
    public List<Role> getRoles(final int movid){
        List<Role> roles  = new ArrayList<>();
        String query = "SELECT * FROM `role` INNER JOIN `people` ON people.peo_id=role.peo_id WHERE mov_id="+movid+";";
        try(ResultSet rs = stm.executeQuery(query)){
            while( rs.next() ){
                Role role = new Role();
                role.setMovID(rs.getInt("mov_id"));
                role.setPeopleID(rs.getInt("peo_id"));
                role.setRolID(rs.getInt("rol_id"));
                role.setRolName(rs.getString("rol_name"));
                roles.add(role);
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
        return roles;
    }
    
    public List<Role> getRolesByPerson(final int peoid){
        List<Role> roles  = new ArrayList<>();
        String query = "SELECT * FROM `role` INNER JOIN `people` ON people.peo_id=role.peo_id WHERE role.peo_id="+peoid+";";
        try(ResultSet rs = stm.executeQuery(query)){
            while( rs.next() ){
                Role role = new Role();
                role.setMovID(rs.getInt("mov_id"));
                role.setPeopleID(rs.getInt("peo_id"));
                role.setRolID(rs.getInt("rol_id"));
                role.setRolName(rs.getString("rol_name"));
                roles.add(role);
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
        return roles;
    }
    
    public String getRole(final int movid, final int peoid){
        String role = null ;
        String query = "SELECT rol_name FROM `role` WHERE mov_id="+movid+" and peo_id="+peoid+";";
        try(ResultSet rs = stm.executeQuery(query)){
            while( rs.next() ){
                role = rs.getString("rol_name");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return role;
    }
    
    public People getPerson(final int peoid){
        People people = null;
        
        try{
            String query = "SELECT * FROM `people` WHERE peo_id="+peoid+";";
            
            try(ResultSet rs= stm.executeQuery( query )){
                rs.next();
                people = new People();
                people.setPeoID(rs.getInt("peo_id"));
                people.setPeoName(rs.getString("peo_name"));
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return people;
    }
}
