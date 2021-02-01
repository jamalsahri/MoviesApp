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
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Oumayma
 */
public class PrincipaleUIController implements Initializable, Controller {

    
    @FXML
    private StackPane body;
    @FXML
    private VBox vb_top;
    @FXML
    private VBox vb_root;
    @FXML
    private TextField tf_chercherParTitre;
    @FXML
    private JFXButton btn_chercherParTitre;
    @FXML
    private TextField tf_chercherParNom;
    @FXML
    private JFXButton btn_chercherParNom;
    @FXML
    private JFXButton btn_precedent;
    @FXML
    private TextField tf_index;
    @FXML
    private JFXButton btn_suivant;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label lbl_genres;
    @FXML
    private JFXTextArea ta_synopsis;
    @FXML
    private GNAvatarView avatar_affiche;
    @FXML
    private Label lbl_anneeProd;
    @FXML
    private Label lbl_note;
    @FXML
    private JFXListView<Node> lv_participants;
    
    
    // ==== //
    
    private App app;
    private Movie currentMovie;
    private List<Movie> movies = new ArrayList<>();
    private int currentIndex;
    private ObservableList<Node> participantsNodes ;
    // ==== //

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //movies = Dao.getInstance().getMovies();
        
        participantsNodes = FXCollections.observableArrayList();
        currentMovie = Dao.getInstance().getMovieByID(1);
        currentIndex = 1;
        System.out.println(currentMovie);
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
    
    private void clear(){
        try{
            tf_chercherParNom.clear();
            tf_chercherParTitre.clear();
            tf_index.clear();
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
    }
    
    @FXML
    void principale(ActionEvent event) {
        App.getApp().gotoPrincipale();
    }
    
    @FXML
    private void chercherParTitre(ActionEvent event) {
        try{
            if( !tf_chercherParTitre.getText().trim().isEmpty() ){
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void chercherParNom(ActionEvent event) {
        try{
            if( !tf_chercherParNom.getText().trim().isEmpty() ){
                System.out.println(tf_chercherParNom.getText().trim());
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
    }

    @FXML
    private void precedent(ActionEvent event) {
        if( currentIndex > 1 ){
            currentIndex--;
            currentMovie = Dao.getInstance().getMovieByID(currentIndex);
            //currentMovie = movies.get(currentIndex);
            updateView();
        }
    }

    @FXML
    private void enter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            try{
                int index = Integer.valueOf(tf_index.getText().trim());
                if( index > 1 && index != currentIndex){
                    Movie tmpMovie = Dao.getInstance().getMovieByID(index);
                    if( tmpMovie != null){
                        currentMovie = tmpMovie;
                        currentIndex = index;
                        updateView();
                    }
                }else{
                    tf_index.setText(currentIndex+"");
                }
            }catch(Exception ex){
                //APPException.showErrorNotifiction(ex);
            }
        }
    }

    @FXML
    private void suivant(ActionEvent event) {
        try{
            Movie tmpMovie = Dao.getInstance().getMovieByID(currentIndex+1);
            if(tmpMovie != null){
                currentMovie = tmpMovie;
                currentIndex++;
                updateView();
            }
        }catch(Exception ex){
            
        }
    }

    
    
    
    
    public void updateView(){
        try {
            if(this.currentMovie != null){
                tf_index.setText(this.currentMovie.getMovID()+"");
                lbl_titre.setText(this.currentMovie.getTitle());
                lbl_anneeProd.setText(this.currentMovie.getReleaseDate().toString());
                lbl_note.setText(this.currentMovie.getRating()+"/10");
                /**/
                try{
                    //URL url = new URL(this.currentMovie.getPoster());
                    
                    
                    Platform.runLater(() -> {
                        Image img = null;
                        try {
                            img = new Image( this.currentMovie.getPoster() );
                            avatar_affiche.setImage(img);
                        } catch (Throwable ex) {
                            avatar_affiche.setImage(new Image( "/com/moviesapp/media/pictures/no-image-available.png" ));
                        }finally{
                            if(img==null)
                                avatar_affiche.setImage(new Image( "/com/moviesapp/media/pictures/no-image-available.png" ));
                        }
                    });
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                /**/
                lbl_genres.setText(this.currentMovie.getGenres());
                ta_synopsis.setWrapText(true);
                ta_synopsis.setText(this.currentMovie.getDescription());
                
                
                List<Role> roles = Dao.getInstance().getRoles(currentIndex);
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
