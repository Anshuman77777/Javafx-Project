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
public class signupcontroller {
    Stage stage;
    int role=0;
    @FXML
     TextField emailField;
    @FXML
    TextField pin;
    @FXML
     PasswordField passwordField;
    @FXML
    TextField Brand;
    @FXML
    TextField Info;
    
    @FXML
    private PasswordField confirmPasswordField;
    //0->retailer 1->wholesaler
    void setStage(Stage stage,int role)
    {
        this.stage=stage;
        this.role=role;
    }
    @FXML
    void next() throws IOException
    {
        String email=emailField.getText();
        String pass=passwordField.getText();
        String cpass=confirmPasswordField.getText();
        String brand=Brand.getText();
        String info=Info.getText();
        String p=pin.getText();
        System.out.println(email);
        System.out.println(pass);
        System.out.println(cpass);
        System.out.println(brand);
        System.out.println(info);
        System.out.println(p);
        
        if(!(pass.equals(cpass)))return ;
        if(Auth.signup(email, pass, role, brand, p, info));
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
        mpc.setStage(stage,email);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
        }
    }
    @FXML
    void login() throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();
       logincontroller mpc=loader.getController();
        mpc.setStage(stage,role);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
