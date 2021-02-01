/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.moviesapp.App;
import com.moviesapp.dao.Dao;
import com.moviesapp.model.Controller;
import com.moviesapp.model.Movie;
import com.moviesapp.model.People;
import com.moviesapp.model.Role;
import com.moviesapp.util.APPException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Oumayma
 */
public class FilmUIController implements Initializable, Controller {

    @FXML
    private Label lbl_titre;
    @FXML
    private Label lbl_genres;
    @FXML
    private Label lbl_annee_prod;
    @FXML
    private Label lbl_note;
    @FXML
    private JFXTextArea ta_synopsis;
    @FXML
    private GNAvatarView avatar_affiche;
    @FXML
    private JFXListView<Node> lv_participants;

    
    private App app;
    private Movie movie;
    private ObservableList<Node> participantsNodes ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        participantsNodes = FXCollections.observableArrayList();
    }    

    @Override
    public void setApp(App application) {
        if(application != null){
            this.app = application;
        }
    }

    @Override
    public void init() {
        updateView();
    }
    
    public void setMovie(Movie movie){
        if(movie != null)
            this.movie = movie;
    }
    
    public void updateView(){
        try {
            if(this.movie != null){
                lbl_titre.setText(this.movie.getTitle());
                lbl_annee_prod.setText(this.movie.getReleaseDate().toString());
                lbl_note.setText(this.movie.getRating()+"/10");
                /**/
                try{
                    //URL url = new URL(this.currentMovie.getPoster());
                    
                    
                    Platform.runLater(() -> {
                        try {
                            Image img = new Image( this.movie.getPoster() );
                            avatar_affiche.setImage(img);
                        } catch (Exception ex) {
                            avatar_affiche.setImage(new Image( "/com/moviesapp/media/pictures/no-image-available.png" ));
                        }
                    });
                }catch(Exception ex){
                    avatar_affiche.setImage(new Image( "/com/moviesapp/media/pictures/no-image-available.png" ));
                }
                /**/
                lbl_genres.setText(this.movie.getGenres());
                ta_synopsis.setWrapText(true);
                ta_synopsis.setText(this.movie.getDescription());
                
                
                List<Role> roles = Dao.getInstance().getRoles(movie.getMovID());
                if(roles.size()>0){
                    participantsNodes.clear();
                    for(Role role:roles){
                        People p = Dao.getInstance().getPerson(role.getPeopleID());
                        JFXButton btn = new JFXButton(p.getPeoName()+" - "+role.getRolName());
                        btn.setId(p.getPeoID()+"");
                        btn.setStyle("-fx-background-color: #02C852;-fx-background-radius:5px;-fx-border-color:#AA66CC;" +
                                     "	-fx-font-size : 10px;" +
                                     "	-fx-text-fill:white; -fx-cursor : hand;" +
                                     "	-fx-border-radius:5px;" +
                                     "	-fx-prompt-text-fill:#4d4d4d;" +
                                     "	-fx-font-weight:bold;");
                        btn.setMinWidth(280);
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent e) {
                                final Node source = (Node) e.getSource();
                                String id = source.getId();
                                App.getApp().gotoPerson(id);
                            }
                        });
                        participantsNodes.add(btn);
                    }
                    System.err.println(participantsNodes.size()+" ??");
                    lv_participants.setItems(participantsNodes);
                }
                
            }
        } catch (Exception ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
}
