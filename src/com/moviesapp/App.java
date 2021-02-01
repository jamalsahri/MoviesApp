/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp;

import com.moviesapp.controller.FilmUIController;
import com.moviesapp.controller.PersonUIController;
import com.moviesapp.controller.PrincipaleUIController;
import com.moviesapp.dao.Dao;
import com.moviesapp.global.AppConfigConst;
import com.moviesapp.global.ViewManager;
import com.moviesapp.model.Controller;
import com.moviesapp.model.Movie;
import com.moviesapp.model.People;
import com.moviesapp.util.APPException;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import moviesapp.preloader.MoviesApp_Preloader;

/**
 *
 * @author Oumayma
 */
public class App extends Application {
    
    private Stage stage;
    private StackPane body;
    private float  increment = 0;
    private float  progress  = 0;
    private static App app;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            body = new StackPane();
            this.stage = stage;
            this.stage.setScene(new Scene(body));
            stage.resizableProperty().setValue(Boolean.TRUE);
            this.stage.centerOnScreen();
            gotoPrincipale();
            this.stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        LauncherImpl.launchApplication(App.class, MoviesApp_Preloader.class,args);
    }
    
    public static App getApp(){
        return app;
    }
    
    public Node getRoot(){
        return body;
    }
    
    @Override
    public synchronized void init(){
        app = this;
        System.out.println("Progress="+progress+" incremente="+increment);
        try{
            float total = 3; //count of screens
            increment = 100f / total;
            
            load("FilmUI.fxml", "Film");
            
            load("PersonUI.fxml", "Person");
            
            load("PrincipaleUI.fxml", "Principale");
            
        }  catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }
             
        try {
            wait(500);
        } catch (InterruptedException ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
    
    public void load( String uiName, String name ){
        try {
            ViewManager viewManager = ViewManager.getInstance(); 
            FXMLLoader loader = new FXMLLoader(getClass().getResource(AppConfigConst.VIEWS_PATH+uiName));
            
            viewManager.put( name, loader.load() ); //Sauvgarde de chaque scene avec son controller
            viewManager.putController(name, loader.getController());
            viewManager.getController(name).setApp(this);
            
            preloaderNotify();
        } catch (Exception ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
    
    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }
    
    public void gotoPrincipale(){
        try {
            System.out.println("Size: "+ViewManager.getInstance().getSize());
            Controller principale = (PrincipaleUIController) replaceSceneContent("Principale");
            principale.init();
            //stage.getIcons().add(new Image(AppConfigConst.MEDIA_PATH+"newton.png"));
            stage.setTitle("Principale");
            //stage.sizeToScene();
        } catch (Exception ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
    
    public void gotoPerson(final String peoid){
        try {
            
            System.out.println("Size: "+ViewManager.getInstance().getSize());
            Controller person = (PersonUIController) replaceSceneContent("Person");
            People p = Dao.getInstance().getPerson(Integer.valueOf(peoid));
            ((PersonUIController)person).setPerson(p);
            person.init();
            //stage.getIcons().add(new Image(AppConfigConst.MEDIA_PATH+"newton.png"));
            stage.setTitle("Person");
            stage.sizeToScene();
        } catch (Exception ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
    
    public void gotoFilm(Movie movie){
        try {
            
            System.out.println(movie);
            Controller film = (FilmUIController) replaceSceneContent("Principale");
            ((FilmUIController)film).setMovie(movie);
            //stage.getIcons().add(new Image(AppConfigConst.MEDIA_PATH+"newton.png"));
            film.init();
            stage.setTitle("Film");
            stage.sizeToScene();
        } catch (Exception ex) {
            APPException.showErrorNotifiction(ex);
        }
    }
    
    private Controller replaceSceneContent(final String name) throws Exception{
        Node node = ViewManager.getInstance().get(name);
        try{
            if( this.body.getChildren().size() > 0 ){
                this.body.getChildren().remove( 0 );
            }
        }catch(Exception ex){
            APPException.showErrorNotifiction(ex);
        }finally{
            this.body.getChildren().add(node);
        }
        return ViewManager.getInstance().getController(name);
        
    }
}
