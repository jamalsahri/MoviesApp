/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.moviesapp.App;
import com.moviesapp.dao.Dao;
import com.moviesapp.model.Controller;
import com.moviesapp.model.Movie;
import com.moviesapp.model.People;
import com.moviesapp.model.Role;
import com.moviesapp.util.APPException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Oumayma
 */
public class PersonUIController implements Initializable, Controller {

    @FXML
    private Label lbl_nomPerson;
    @FXML
    private JFXButton btn_prev;
    @FXML
    private GNAvatarView avatar_affiche_1;
    @FXML
    private Label lbl_titre_1;
    @FXML
    private Label lbl_anneeProd_1;
    @FXML
    private Label lbl_role_1;
    @FXML
    private GNAvatarView avatar_affiche_2;
    @FXML
    private Label lbl_titre_2;
    @FXML
    private Label lbl_anneeProd_2;
    @FXML
    private Label lbl_role_2;
    @FXML
    private GNAvatarView avatar_affiche_3;
    @FXML
    private Label lbl_titre_3;
    @FXML
    private Label lbl_anneeProd_3;
    @FXML
    private Label lbl_role_3;
    @FXML
    private JFXButton btn_next;

    
    private App app;
    private People person;
    private List<Movie> movies = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private Movie midCurrentMovie ;
    private int midCurrentIndex ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void prev(ActionEvent event) {
        if(roles.size()>3 && (midCurrentIndex-1)>0){
            midCurrentIndex--;
            updateView();
        }   
    }

    @FXML
    private void next(ActionEvent event) {
        if(roles.size()>3 && (roles.size()-(midCurrentIndex+1))>1){
            midCurrentIndex++;
            updateView();
        }
    }

    @Override
    public void setApp(App application) {
        if( application != null)
            this.app = app;
    }

    
    @FXML
    void showFilmLeft(MouseEvent event) {
        try{
            System.err.println("LLLL");
            App.getApp().gotoFilm(Dao.getInstance().getMovieByID(this.roles.get(midCurrentIndex-1).getMovID()));
        }catch(Exception ex){
            
        }
    }

    @FXML
    void showFilmMid(MouseEvent event) {
        try{
            System.err.println("MMMM");
            App.getApp().gotoFilm(Dao.getInstance().getMovieByID(this.roles.get(midCurrentIndex).getMovID()));
        }catch(Exception ex){
            
        }
    }

    @FXML
    void showFilmRight(MouseEvent event) {
        try{
            System.err.println("RRRR");
            App.getApp().gotoFilm(Dao.getInstance().getMovieByID(this.roles.get(midCurrentIndex+1).getMovID()));
        }catch(Exception ex){
            
        }
    }
    
    @Override
    public void init() {
        try{
            lbl_nomPerson.setText(person.getPeoName());
            roles =  Dao.getInstance().getRolesByPerson(person.getPeoID());
            //movies = Dao.getInstance().getPersonMovies(this.person);
            if(roles.size()>3){
                midCurrentIndex = 1;
            }else if(roles.size()==2) {
                midCurrentIndex = 1;
            }else if(roles.size()==1){
                midCurrentIndex = 0;
            }
            updateView();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void setPerson(final People people){
        if(people != null)
            this.person = people;
    }
    
    private void updateView(){
        
        if(roles.size()>3){
            Movie tmp=Dao.getInstance().getMovieByID(roles.get(midCurrentIndex).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex), avatar_affiche_2, lbl_titre_2, lbl_role_2, lbl_role_2);
            tmp = Dao.getInstance().getMovieByID(roles.get(midCurrentIndex-1).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex-1),avatar_affiche_1, lbl_titre_1, lbl_role_1, lbl_role_1);
            tmp = Dao.getInstance().getMovieByID(roles.get(midCurrentIndex+1).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex+1),avatar_affiche_3, lbl_titre_3, lbl_role_3, lbl_role_3);
        }else if(roles.size()==2){
            Movie tmp=Dao.getInstance().getMovieByID(roles.get(midCurrentIndex).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex), avatar_affiche_2, lbl_titre_2, lbl_role_2, lbl_role_2);
            tmp = Dao.getInstance().getMovieByID(roles.get(midCurrentIndex-1).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex-1),avatar_affiche_1, lbl_titre_1, lbl_role_1, lbl_role_1);
        }else if(roles.size()==1){
            Movie tmp=Dao.getInstance().getMovieByID(roles.get(midCurrentIndex).getMovID());
            updateCell(tmp, roles.get(midCurrentIndex), avatar_affiche_2, lbl_titre_2, lbl_role_2, lbl_role_2);
        }
        
        
        
    }
    private void updateCell(Movie m,Role rol,GNAvatarView av, Label t, Label ap, Label r){
        try{
            
            Platform.runLater(() -> {
                System.out.println(">>>>"+m.getPoster());
                Image img = new Image( "/com/moviesapp/media/pictures/no-image-available.png" );
                if(img==null)
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
                avatar_affiche_1.setImage(img);                    
            });
            
            t.setText(m.getTitle());
            ap.setText(m.getReleaseDate());
            r.setText(rol.getRolName()+" "+rol.getRolID());
        }catch(Exception ex){
            System.err.println(ex.getCause().toString());;
        }
    }
}
