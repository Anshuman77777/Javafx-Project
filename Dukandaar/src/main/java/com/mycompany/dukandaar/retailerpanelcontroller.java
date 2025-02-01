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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Anshu
 */
public class retailerpanelcontroller {
    @FXML
    BorderPane Canvas;
    @FXML
    Label fullName;
    Stage stage;
    void setStage(Stage stage,String Name)
    {
        this.stage=stage;
        fullName.setText(Name);
        
    }
    void starting()throws IOException{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root=loader.load();
        HomeController hpc = loader.getController();
        
        
        hpc.setStage(stage,Canvas);
    // Assuming Canvas is a BorderPane (or another Pane type)
        Canvas.setCenter(root);
        Canvas.getStylesheets().add(getClass().getResource("home.css").toExternalForm());    
     }
    @FXML
    void profile()throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("wprofile.fxml"));
        Parent root=loader.load();
        Canvas.setCenter(root);
    }
    @FXML
    void chatbot()throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("wchatbot.fxml"));
        Parent root=loader.load();
       
        Canvas.setCenter(root);
   
    }
    @FXML
    void home()throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root=loader.load();
        HomeController hpc = loader.getController();
        
        
        hpc.setStage(stage,Canvas);
    // Assuming Canvas is a BorderPane (or another Pane type)
        Canvas.setCenter(root);
        Canvas.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
         
         

    }
    
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
    
}
