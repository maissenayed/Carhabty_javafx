/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;
import DataBase.Session;
import Entities.User;
import Entities.Voiture;
import Functions.CurrentVoiture;
import Services.UserServices;

import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import io.datafx.controller.FXMLController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Maissen
 */
@FXMLController(value = "/Views/fxml/ajoutervoiture.fxml", title = "Afficher offre - Carhabty")
public class AjoutvoitureController implements Initializable {
    @FXML
    private ImageView photo;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField modele;
    @FXML
    private JFXDatePicker annee;
    @FXML
    private JFXButton insert;
    @FXML
    private JFXButton upload;
    
      String fileName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        photo.setImage(new Image("Image/avatar.jpg"));
        RequiredFieldValidator valid =new RequiredFieldValidator();
        marque.getValidators().add(valid);
        valid.setMessage("can't be empty");
        marque.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    marque.validate();
                    System.out.println("dude");            
                }
            }
        });
         modele.getValidators().add(valid);
        valid.setMessage("can't be empty");
        modele.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    modele.validate();
                    System.out.println("dude");            
                }
            }
        });
        
       
     
    }    

    @FXML
    private void handleinsert(ActionEvent event)throws SQLException, IOException  {
          
        if (marque.getText().isEmpty() || modele.getText().isEmpty() ||annee.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
                ) {
            TrayNotification tray = new TrayNotification("ERROR", "LES CHAMPS NE SONT PAS VALID",NotificationType.ERROR);
        tray.showAndWait();        
            return;
        }
         
      String Marque = marque.getText();
      String Modele = modele.getText();
      java.sql.Date date = java.sql.Date.valueOf(annee.getValue());
      
      
      Voiture cal=new Voiture(); 
      cal.setMarque(Marque);
      cal.setModel(Modele);
      cal.setAnnee(date);
      cal.setImage(CurrentVoiture.Currento.getImage());
        VoitureService crud =new VoitureService();
        System.out.println(cal);
        crud.addVoiture(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre voiture a été ajouter avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
        
        
        
        
        
    }

    @FXML
    private void handleimage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File image = chooser.showOpenDialog(new Stage());
        String filePath = image.getPath();
        fileName = image.getName();

        try {

            InputStream inStream = null;
            OutputStream outStream = null;

            File ImageUplaoded = new File("src/Image/" + fileName);

            inStream = new FileInputStream(filePath);
            outStream = new FileOutputStream(ImageUplaoded);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
           
            VoitureService voitureService = new VoitureService();
            Voiture u = new Voiture();
            CurrentVoiture val =new  CurrentVoiture();
            u.setImage(fileName);
            System.out.println(fileName);
            val.setCurrentVoiture(u);
            System.out.println(u.getImage()); 
            String Photo =u.getImage();
            
            photo.setImage(new Image("Image/avatar.jpg"));
            photo.setImage(new Image ("Image/"+Photo));
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
              TrayNotification tray = new TrayNotification("Félicitation", "Votre Image a été ajouter avec succées",NotificationType.SUCCESS);
              tray.showAndWait();

           

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
        
        
        
        
        
        
    }

   
    }
    

