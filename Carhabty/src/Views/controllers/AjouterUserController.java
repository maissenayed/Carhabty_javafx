/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.User;
import Services.UserServices;
import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class AjouterUserController implements Initializable {

   
    
       @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField rpassword;

    @FXML
    private JFXTextField adresse;
    
    @FXML
    private JFXTextField telephone;
    
         @FXML
    private JFXToggleButton role;

  
        
         
         
    @FXML
    void register(ActionEvent event) {

        
        User u = new User();
        
        u.setAdresse(adresse.getText());
        u.setNom( nom.getText());
        u.setPrenom(prenom.getText());
        u.setAdresse(adresse.getText());
        u.setPassword(password.getText());
        u.setEmail(email.getText());
        u.setUsername(username.getText());
        u.setTel(telephone.getText());
        
    
           // System.out.println("fff");
       // username.setVisible(false);
        
        
        
        
       UserServices userService = new UserServices();
       userService.add(u);
        
   
        
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
