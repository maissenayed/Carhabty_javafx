/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import BackBone.ClientToServer.ImageSender;
import DataBase.Session;
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
import java.util.Timer;
import java.util.TimerTask;
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

    private static final String VOICENAME = "garci";

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
     @FXML
    public void voiture() throws IOException {
        if (pane != null) {

            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/affichervoiture.fxml")));

        } else {
            System.out.println("probléme");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        photo.setFitHeight(245);
        photo.setFitWidth(352);
        
        /*
        Voice voice;
        VoiceManager vm =  VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);    
        voice.allocate();
        
        try{
        
        voice.speak("Hello "+Session.actualUser.getUsername()+" this is carhabty robot have a nice day");
        
        }catch(Exception e)
        {e.printStackTrace();}
            
         */
        if (Session.actualUser.getImage() == null) {
            photo.setImage(new Image("/Image/avatar.jpg"));
        } else {
            System.out.println(Session.actualUser.getImage());
            photo.setImage(new Image("http://localhost/Carhabtyy/web/images/offres/" + Session.actualUser.getImage()));

        }
        bienvenu.setText("Bienvenue " + Session.actualUser.getNom());

        lab.setText("Username : " + Session.actualUser.getUsername() + "\n\n\n"
                + "Nom : " + Session.actualUser.getNom() + "\n\n\n"
                + "Prenom : " + Session.actualUser.getPrenom() + "\n\n\n"
                + "Email : " + Session.actualUser.getEmail() + "\n\n\n"
                + "Adresse : " + Session.actualUser.getAdresse() + "\n\n\n"
                + "Téléphone : " + Session.actualUser.getTel() + "\n\n\n");

        lab.setStyle("-fx-font: bold 13 System;");
        lab1.setText("Infomations Générales" + "\n");
        lab1.setStyle("-fx-font: bold 16 System;-fx-text-fill: #00B16A;");

    }

    @FXML
    void upalod(ActionEvent event) throws URISyntaxException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File image = chooser.showOpenDialog(new Stage());
       
        
        ImageSender is=new ImageSender();
        is.executeMultiPartRequest("http://localhost/Carhabtyy/web/app_dev.php/quiz/uploadImg", image, image.getName(),"offres");
         Session.actualUser.setImage(image.getName());
         UserServices userSerivce = new UserServices();
         userSerivce.updatePhoto(Session.actualUser);
         photo.setImage(new Image("http://localhost/Carhabtyy/web/images/offres/" + Session.actualUser.getImage()));
        
        
        
        
        
     /*   String filePath = image.getPath();
        String fileName = image.getName();

        try {

            //    System.out.println(fileName);
            File ImageUplaoded = new File("src/Image/" + fileName);

            inStream = new FileInputStream(filePath);
            outStream = new FileOutputStream(ImageUplaoded);
            byte[] buffer = new byte[10240];
            int length;

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            System.out.println("File is copied successful!");

            UserServices userSerivce = new UserServices();

            //  System.out.println(fileName);
            //   System.out.println(Session.actualUser.getImage());
            //    System.out.println(fileName);
            Session.actualUser.setImage(fileName);
            userSerivce.updatePhoto(Session.actualUser);
            photo.setImage(new Image("/Image/" + Session.actualUser.getImage()));

            //   userSerivce.findById(Session.actualUser.getId()).getImage()
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //     System.out.println(userSerivce.findById(Session.actualUser.getId()).getImage());
                    System.out.println("Image/" + Session.actualUser.getImage());
                   // photo.setImage(new Image("/Image/" + Session.actualUser.getImage()));
                }
            }, 3000);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
}



}
