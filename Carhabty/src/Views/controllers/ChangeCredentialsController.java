package Views.controllers;

import DataBase.Session;
import Entities.User;
import Services.UserServices;
import com.jfoenix.controls.*;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;




public class ChangeCredentialsController implements Initializable {

    
      @FXML
    private Label lab;

    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField telephone;

    
    
    
    
     

    @FXML
    void UpadateCredentials(ActionEvent event) throws VetoException, IOException{
  /*
        JFXDialogLayout dl = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ok");
        */
               
       User u = new User();
       
       u.setUsername(username.getText());
       u.setEmail(email.getText()); 
       u.setNom(nom.getText()); 
       u.setPrenom(prenom.getText()); 
       u.setAdresse(adresse.getText()); 
       u.setTel(telephone.getText()); 
       u.setId(Session.actualUser.getId());
         
       UserServices UseService = new UserServices();
       
       if(UseService.update(u)){
       
      
      
        username.setText("");
        email.setText("");
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        telephone.setText("");
       
        
         //dialog
       }
       
       
       else{
       
       
        //Dialog 
       
       }
    
    }
    
    
  
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        
        
      lab.setStyle("-fx-font: bold 16 System;-fx-text-fill: #00B16A;");
        
        username.setText(Session.actualUser.getUsername());
        email.setText(Session.actualUser.getEmail());
        nom.setText(Session.actualUser.getNom());
        prenom.setText(Session.actualUser.getPrenom());
        adresse.setText(Session.actualUser.getAdresse());
        System.out.println(Session.actualUser.getAdresse());
        telephone.setText(Session.actualUser.getTel());
       
    }

}
