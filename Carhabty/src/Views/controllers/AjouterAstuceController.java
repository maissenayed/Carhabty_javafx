/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Entities.Astuce;
import Services.AstuceServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
@FXMLController(value = "/Views/fxml/AjouterAstuce.fxml", title = "")
public class AjouterAstuceController implements Initializable {

    InputStream inStream = null;
    OutputStream outStream = null;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private JFXTextField titre;

    @FXML
    private JFXTextArea desc;

    @FXML
    private JFXComboBox theme;

    @FXML
    private JFXButton ajouterphoto;

    @FXML
    private ImageView photo;

    String fileName, filePath;

    @FXML
    void go(ActionEvent event) throws IOException {

        AnchorPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AfficherAstuce.fxml")));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ajouterphoto.setOnAction(e -> {

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File image = chooser.showOpenDialog(new Stage());
            filePath = image.getPath();
            fileName = image.getName();

        });

        theme.getItems().addAll("Entretien", "Conduite", "Lavage", "Autre");

    }

    @FXML
    void ajouterAstuce(ActionEvent event) {

        try {

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
            System.out.println("File is copied successful!");

            AstuceServices as = new AstuceServices();
            Astuce s = new Astuce();

            s.setTitre(titre.getText());
            s.setDescription(desc.getText());
            s.setTheme((String) theme.getValue());
            s.setImage_name(fileName);

            as.add(s);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterAstuceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjouterAstuceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
