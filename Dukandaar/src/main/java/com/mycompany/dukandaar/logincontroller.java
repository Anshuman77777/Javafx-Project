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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Anshu
 */
public class logincontroller {
    Stage stage;
    int role=0;
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField passwordField;
    void setStage(Stage stage,int role)
    {
        this.stage=stage;
        this.role=role;
    }
    @FXML
    void next() throws IOException
    {String user=username.getText();
    String pass=passwordField.getText();
       if(Auth.login(user, pass, role))
       {
        if(role==0)
        {   FXMLLoader loader=new FXMLLoader(getClass().getResource("retailerPanel.fxml"));
        Parent root = loader.load();
       retailerpanelcontroller mpc=loader.getController();
        mpc.setStage(stage);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
        else 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("wholesalerPanel.fxml"));
        Parent root = loader.load();
       wholesalerpanelcontroller mpc=loader.getController();
        mpc.setStage(stage);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
       }
       else {System.out.println("Wrong Credentials");
       System.out.println(user);
       System.out.println(pass);
       System.out.println(role);
       }
    }
    @FXML
    void signup() throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SignupPage.fxml"));
        Parent root = loader.load();
        signupcontroller mpc=loader.getController();
       mpc.setStage(stage,role);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
