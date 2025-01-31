/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dukandaar;


import java.awt.Canvas;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Om
 */
public class HomeController  {
    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane Canvas;
    Stage stage;
    @FXML
    private VBox wholesalers;
    void setStage(Stage stage,BorderPane Canvas)
    {    
    show();
    this.Canvas=Canvas;
        this.stage=stage;
    }  
    public void show(){
        int i=1;
        for (String key : Auth.getTopWholesalers()) {
            System.out.println(key);
            wholesalers.getChildren().add(hb(i++,key));
        }
    }
    private HBox hb(int i,String s){
        HBox card = new HBox();
        card.getStyleClass().add("user-card");
        card.setPrefWidth(600);
        
        Label indexLabel = new Label(String.valueOf(i));
        indexLabel.getStyleClass().add("index-number");
        
        Label usernameLabel = new Label(s);
        usernameLabel.getStyleClass().add("username");
        
        card.getChildren().addAll(indexLabel, usernameLabel);
        card.setOnMouseClicked(event -> {
            System.out.println(s);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchasepage.fxml"));
        Parent root;
            try {
                root = loader.load();
                PurchaseController mpc=loader.getController();
                
                //mpc.setStage(stage);
                mpc.setWholesalerUsername(s);
                Canvas.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    });
        
        return card;
    }
    
}
