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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Anshu
 */
public class wholesalerpanelcontroller {
     Stage stage;
     @FXML
   BorderPane Canvas;
    void setStage(Stage stage)
    {
        this.stage=stage;
        
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
    void analysis() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
    
     Parent root=loader.load();
    // HomePageController hpc = loader.getController();
   // hpc.Canvas=Canvas;
    //hpc.email=Gmail;
    //hpc.setStage(stage);
    // Assuming Canvas is a BorderPane (or another Pane type)
    Canvas.setCenter(root);
        
    }
    
}
