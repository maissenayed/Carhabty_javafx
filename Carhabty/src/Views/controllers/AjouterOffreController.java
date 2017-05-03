/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import BackBone.ClientToServer.ImageSender;
import Entities.Offre;
import Services.OffreServices;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/AjouterOffre.fxml", title = "Ajouter offre - Carhabty")
public class AjouterOffreController {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField reduc;

    @FXML
    private JFXTextArea desc;

    @FXML
    private DatePicker date;

    File image;

    @FXML
    void uploadPhoto(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
         image = chooser.showOpenDialog(new Stage());
        
        
        

    }

    @FXML
    void AjouterOffre(ActionEvent event) throws FlowException, VetoException, IOException {

      /*  try {

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

            TrayNotification tray = new TrayNotification("Félicitation", "Votre Image a été ajouter avec succées", SUCCESS);
            tray.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

         ImageSender is=new ImageSender();
         is.executeMultiPartRequest("http://localhost/Carhabtyy/web/app_dev.php/quiz/uploadImg", image, image.getName(),"offres");
         //Session.actualUser.setImage(image.getName());
       //  OffreServices offreService = new OffreServices();
        
      
      
      
        Offre o = new Offre();
        o.setNomOffre(nom.getText());
        o.setDescriptionOffre(desc.getText());
        o.setPrix(Float.parseFloat(prix.getText()));
        o.setReduction(Integer.parseInt(reduc.getText()));
        o.setDateExp(java.sql.Date.valueOf(date.getValue()));
        o.setImage(image.getName());
     //   System.out.println(fileName);
       // o.setImage(fileName);

        OffreServices OffreService = new OffreServices();

        if (OffreService.add(o)) {

            TrayNotification tray = new TrayNotification("Félicitation", "Votre offre a été ajouter avec succées", SUCCESS);
            tray.showAndWait();
        }

        nom.setText("");
        desc.setText("");
        prix.setText("");
        reduc.setText("");
    }
    //  date.setValue(LocalDate.now());

}
