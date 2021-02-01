/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.global;

import com.moviesapp.model.Controller;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 *
 * @author Oumayma
 */
public class ViewManager {
    
    private static ViewManager instance;
    private static final HashMap<String, Node> SCREENS = new HashMap<>();//to-do
    private static final HashMap<String, Controller> CONTROLLERS = new HashMap<>();
    private static String currentView;
    
    
    public static ViewManager getInstance(){
        if(instance == null)
            instance = new ViewManager();
        return instance;
    }
    
    public void putController(String name, Controller controller){
        CONTROLLERS.put(name, controller);
    }
    
    public Controller getController(String name){
        return CONTROLLERS.get( name );
    }
    
    //Prob: a changer plus tard
    public Controller getCurrentController(){
        return CONTROLLERS.get(currentView);
    }
    
    public void put(String name, Node node){
        System.out.println("<"+name+"> has been downloaded...");
        currentView = name;
        SCREENS.put( name, node );
    }
    
    public Node get(String name){
        currentView = name;
        return SCREENS.get( name );
    }
    
    public int getSize(){
        return SCREENS.size();
    }
    
    //Prob: a changer plus tard
    public Node getCurrentView(){
        return SCREENS.get( currentView );
    }
    
    public ObservableList<Node> getAll(){
        return FXCollections.observableArrayList( SCREENS.values() );
    }

    
    public void remove(String name){
        SCREENS.remove(name);
        CONTROLLERS.remove(name);
    }
}
