/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Entities.User;
import Services.UserServices;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Profile.fxml", title = "Material Design Example")
public class ProfileController implements Initializable {

    @FXML
    private ImageView photo;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label bienvenu;

    @FXML
    private JFXButton parametreCompte;

    @FXML
    private JFXButton changePassword;
    
    
    
    @FXML
    private Label lab1;
    
      @FXML
    private Label lab;
    
    InputStream inStream = null;
    OutputStream outStream = null;

    @FXML
    public void changePassword() throws IOException {
        if (pane != null) {

            pane.getChildren().setAll((StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/ChangePassword.fxml")));

        } else {
            System.out.println("probléme");
        }

    }

    @FXML
    public void parametreCompte() throws IOException {

        if (pane != null) {

            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/ChangeCredentials.fxml")));

        } else {
            System.out.println("probléme");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

      //  System.out.println(Session.actualUser.getImage());
       
        if(Session.actualUser.getImage() == null){
          photo.setImage(new Image("Image/avatar.jpg"));
        }
        else{
        
         photo.setImage(new Image ("Image/"+Session.actualUser.getImage()));
        
        }
        bienvenu.setText("Bienvenue " + Session.actualUser.getNom());

        
        lab.setText("Username : "+Session.actualUser.getUsername()+"\n\n\n"+
                "Nom : "+Session.actualUser.getNom()+"\n\n\n"+
                "Prenom : "+Session.actualUser.getPrenom()+"\n\n\n"+
                "Email : "+Session.actualUser.getEmail()+"\n\n\n"+
                "Adresse : "+Session.actualUser.getAdresse()+"\n\n\n"+           
                "Téléphone : "+Session.actualUser.getTel()+"\n\n\n");
        
        lab.setStyle("-fx-font: bold 13 System;");
        lab1.setText("Infomations Générales"+"\n");
        lab1.setStyle("-fx-font: bold 16 System;-fx-text-fill: #00B16A;");
        
        
        
        
        
        
        
        
    }

    @FXML
    void upalod(ActionEvent event) throws URISyntaxException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File image = chooser.showOpenDialog(new Stage());
        String filePath = image.getPath();
        String fileName = image.getName();

      

        try {

            
        //    System.out.println(fileName);
            File ImageUplaoded = new File("src/Image/"+fileName);
                
            inStream = new FileInputStream(filePath);
            outStream = new FileOutputStream(ImageUplaoded);
            byte[] buffer = new byte[1024];
            int length;
          
            while ((length = inStream.read(buffer)) > 0) {
               outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            System.out.println("File is copied successful!");

            
            UserServices userSerivce = new UserServices();
            User u = new User();
            u.setImage(fileName);
            Session.setActualUser(u);
            userSerivce.updatePhoto(u);
            photo.setImage(new Image ("Image/"+Session.getActualUser().getImage()));
          
            
            
            
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
