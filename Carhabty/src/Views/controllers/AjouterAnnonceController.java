/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Annonce;
import Entities.Voiture;
import Functions.CurrentAnnonce;
import Functions.CurrentVoiture;
import Services.AnnonceService;
import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
@FXMLController(value = "/Views/fxml/AjouterAnnonce.fxml", title = "Ajouter offre - Carhabty")
public class AjouterAnnonceController implements Initializable {
 @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField descreption;

    @FXML
    private JFXDatePicker anneepub;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField ville;

    @FXML
    private JFXTextField region;

    @FXML
    private JFXTextField paye;

    @FXML
    private JFXComboBox category;

    @FXML
    private JFXButton uploadphoto;

    @FXML
    private ImageView photo;
    @FXML
    private JFXButton confirmer;

 String fileName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        photo.setImage(new Image("Image/avatar.jpg"));
         RequiredFieldValidator valid =new RequiredFieldValidator();
        title.getValidators().add(valid);
        valid.setMessage("can't be empty");
        title.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    title.validate();
                    System.out.println("dude");            
                }
            }
        });
         descreption.getValidators().add(valid);
        valid.setMessage("can't be empty");
        descreption.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    descreption.validate();
                    System.out.println("dude");            
                }
            }
        });
 category.getItems().removeAll(category.getItems());
                category.getItems().addAll("voiture", "rechange", "piece");
                category.getSelectionModel().select("voiture");



// TODO
    }    


 @FXML
    void handleimage(ActionEvent event) {
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
           
           
            Annonce u = new Annonce();
            CurrentAnnonce val =new  CurrentAnnonce();
            u.setImage(fileName);
            System.out.println(fileName);
            val.setCurrentAnnonce(u);
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

    @FXML
    void handleinsert(ActionEvent event) {
      
        
        if (title.getText().isEmpty() || descreption.getText().isEmpty() ||anneepub.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
              || ville.getText().isEmpty()||region.getText().isEmpty()) {
            TrayNotification tray = new TrayNotification("ERROR", "LES CHAMPS NE SONT PAS VALID",NotificationType.ERROR);
        tray.showAndWait();        
            return;
        }
      
      String Title = title.getText();
      String Ville = ville.getText();
      String Region = region.getText();
      String Paye = paye.getText();
      float Prix =Float.valueOf(prix.getText()) ;
      String cat= category.getSelectionModel().getSelectedItem().toString();
      String Descreption = descreption.getText();
      java.sql.Date date = java.sql.Date.valueOf(anneepub.getValue());
      Annonce cal=new Annonce(); 
      cal.setTitle(Title);
      cal.setDescreption(Descreption);
      cal.setAnneeDeProduit(date);
      cal.setImage(CurrentAnnonce.Currento.getImage());
      cal.setVille(Ville);
      cal.setCategory(cat);
      cal.setPaye(Paye);
      cal.setRegion(Region);
      cal.setPrix(Prix);
        if (cal.getImage().equals(null)) {
                  TrayNotification tray = new TrayNotification("ERROR", "upload une image",NotificationType.ERROR);
        tray.showAndWait();   
            
        }
      
      
        AnnonceService crud =new AnnonceService();
        System.out.println(cal);
        crud.addAnnonce(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre voiture a été ajouter avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
        
        
    }




    
}
