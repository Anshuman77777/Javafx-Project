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
import javafx.stage.Stage;

/**
 *
 * @author Anshu
 */
public class choosebetweencontroller {
    Stage stage;
    void setStage(Stage stage)
    {
        this.stage=stage;
    }
    @FXML
    void next() throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SignupPage.fxml"));
        Parent root = loader.load();
        signupcontroller mpc=loader.getController();
        mpc.setStage(stage,0);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void next2() throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SignupPage.fxml"));
        Parent root = loader.load();
        signupcontroller mpc=loader.getController();
        mpc.setStage(stage,1);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
