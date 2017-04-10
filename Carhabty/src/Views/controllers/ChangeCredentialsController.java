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
import javafx.scene.control.Label;
import tray.animations.AnimationType;
import tray.notification.NotificationType;




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
       
       if(UseService.updateCredentials(u)){
       
     /* User u1 = new User();
      
       u1.setUsername(username.getText());
       u1.setEmail(email.getText()); 
       u1.setNom(nom.getText()); 
       u1.setPrenom(prenom.getText()); 
       u1.setAdresse(adresse.getText()); 
       u1.setTel(telephone.getText()); 
      */
      
       Session.actualUser.setEmail(email.getText());
       Session.actualUser.setPrenom(prenom.getText());
       Session.actualUser.setNom(nom.getText());
       Session.actualUser.setUsername(username.getText());
       Session.actualUser.setTel(telephone.getText());
       Session.actualUser.setAdresse(adresse.getText());
       
      
       
        
            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Modifié avec Succés ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));
       }
       
       
       else{
       
       
            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Erreur de Modification");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));
       
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
