/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dukandaar;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Anshu
 */
public class wholesalerpanelcontroller {
     Stage stage;
     @FXML
     Label fullName;
     String user;
     
     @FXML
   BorderPane Canvas;
     @FXML
   
    void setStage(Stage stage,String Name)
    {
        this.stage=stage;
        fullName.setText(Name);
        user=Name;
        
    }
    @FXML
    Button analysis,profile,inventory,chatbot;
     @FXML
    void logout() throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("choosebetween.fxml"));
        Parent root = loader.load();
       choosebetweencontroller mpc=loader.getController();
       mpc.setStage(stage);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void analysis() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("wanalysis.fxml"));
    
     Parent root=loader.load();
    // HomePageController hpc = loader.getController();
   // hpc.Canvas=Canvas;
    //hpc.email=Gmail;
    //hpc.setStage(stage);
    // Assuming Canvas is a BorderPane (or another Pane type)
    Canvas.setCenter(root);
        
    }
    @FXML
    void chatbot() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("wchatbot.fxml"));
    
     Parent root=loader.load();
    // HomePageController hpc = loader.getController();
   // hpc.Canvas=Canvas;
    //hpc.email=Gmail;
    //hpc.setStage(stage);
    // Assuming Canvas is a BorderPane (or another Pane type)
    Canvas.setCenter(root);
        
    }
    @FXML
    void profile() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("wprofile.fxml"));
    
     Parent root=loader.load();
    // HomePageController hpc = loader.getController();
   // hpc.Canvas=Canvas;
    //hpc.email=Gmail;
    //hpc.setStage(stage);
    // Assuming Canvas is a BorderPane (or another Pane type)
    Canvas.setCenter(root);
        
    }
    @FXML
    void inventory() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("winventory.fxml"));
    
     Parent root=loader.load();
    InventoryController hpc = loader.getController();
    hpc.setUser(user);
    hpc.initialize();
    // Assuming Canvas is a BorderPane (or another Pane type)
    Canvas.setCenter(root);
        
    }
    
}
