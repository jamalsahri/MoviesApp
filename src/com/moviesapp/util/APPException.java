/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moviesapp.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.moviesapp.App;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.Arrays;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Oumayma
 */
public class APPException {
    
    //private static JFXDialog dialog;
    
    
    public static void showNotification(final String message, final String title ){
        Notifications notify = Notifications.create().title(title)
                                            .text(message)
                                            .hideAfter(javafx.util.Duration.seconds(7))
                                            .position(Pos.BOTTOM_RIGHT);
        notify.showWarning();
    }
    
    public static void showErrorNotifiction(Exception exception){
        StackPane root = (StackPane)App.getApp().getRoot();
        /*
        if( dialog != null ){
            dialog.close();
            dialog=null;
        }
        */
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog( root, dialogLayout, JFXDialog.DialogTransition.TOP,true);
        dialogLayout.setStyle("-fx-background-color:#ECECEC; -fx-background-radius:8;");
        
        FontAwesomeIconView headerIcon = new FontAwesomeIconView(FontAwesomeIcon.EXCLAMATION_TRIANGLE);
        Label header = new Label( exception.getMessage(), headerIcon);
        headerIcon.setSize(18+"");
        header.setGraphicTextGap(5);
        header.setStyle("-fx-text-fill:#1f1f20;");
        headerIcon.setStyle("-fx-fill:#1f1f20;");
        
        dialogLayout.setHeading(header);
        HBox pan = new HBox();
        
        
        FontAwesomeIconView iconSkip = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        iconSkip.setStyle("-fx-fill:white;");
        JFXButton skip   = new JFXButton("OK");
        skip.graphicTextGapProperty().set(5);
        skip.prefWidthProperty().set(100);
        skip.setStyle("-fx-background-color:#54A6FB; -fx-text-fill:white; -fx-background-radius:5; -fx-cursor : hand; -fx-font-weight:bold; -fx-border-radius:5; -fx-border-color:#D7D7D7;");
            skip.setOnAction((ActionEvent ev)->{
                dialog.close();
                //stage.close();
            });
            
            
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_DOWN);
        icon.setStyle("-fx-fill:#1f1f20;");
        JFXButton details   = new JFXButton("Details",icon);
        details.graphicTextGapProperty().set(5);
        details.prefWidthProperty().set(100);
        details.setStyle("-fx-background-color:white; -fx-text-fill:#1f1f20; -fx-background-radius:5; -fx-cursor : hand; -fx-font-weight:bold; -fx-border-radius:5; -fx-border-color:#D7D7D7;");
            details.setOnAction((ActionEvent ev)->{
                if(dialogLayout.getBody().isEmpty()){
                    rotate(icon,180);
                    //if(ConnectedUser.getInstance().getConnectedUser().getRole()==Role.ADMINISTRATOR){/
                    //}
                    JFXTextArea messageDetails = new JFXTextArea(Arrays.toString(exception.getStackTrace()));
                    messageDetails.editableProperty().setValue(Boolean.FALSE);
                    dialogLayout.setBody(messageDetails);
                }else{
                    rotate(icon,-180);
                    dialogLayout.setBody();
                }
                
            });
        pan.spacingProperty().set( 10 );
        pan.getChildren().addAll( details, skip );
        dialogLayout.setActions(pan);
        dialog.show(); 
    }
    public static void rotate(Node node, double angle){
        //Creating a rotate transition    
        RotateTransition rotateTransition = new RotateTransition(); 
      
        //Setting the duration for the transition 
        rotateTransition.setDuration(Duration.millis(600)); 
      
        //Setting the node for the transition 
        rotateTransition.setNode(node);       
      
        //Setting the angle of the rotation 
        //rotateTransition.axisProperty().set(Point3D.ZERO);
        //rotateTransition.setByAngle(45); 
        rotateTransition.setByAngle(angle);
        //Setting the cycle count for the transition 
        //rotateTransition.setCycleCount( 1 ); 
      
        //Setting auto reverse value to false 
        //rotateTransition.setAutoReverse(true); 
      
        //Playing the animation 
        rotateTransition.play();
    }
}
