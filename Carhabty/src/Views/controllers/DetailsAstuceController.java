/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Entities.AstuceRate;
import Functions.CurrentAstuce;
import Services.AstuceRateServices;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import java.io.IOException;
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
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */

@FXMLController(value = "/Views/fxml/DetailsAstuce.fxml", title = "")
public class DetailsAstuceController implements Initializable {

    final Rating rating = new Rating();
    private AstuceRateServices rateService = new AstuceRateServices();

    @FXML
    private AnchorPane pane;
    
    
    @FXML
    private ImageView image;

    @FXML
    private HBox rate;

    @FXML
    private Label theme;

    @FXML
    private Label titre;

    @FXML
    private Label description;
    @FXML
    private JFXButton confirmer;

    @FXML
    private JFXButton retour;

    @FXML
    void retour(ActionEvent event) throws IOException {

      pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AfficherAstuce.fxml")));

    }

    @FXML
    void confirmerRate(ActionEvent event) {
        AstuceRate ar = new AstuceRate();
        ar.setAstuce(CurrentAstuce.Currenta);
        ar.setRate((float) rating.getRating());
        if (rateService.exist(CurrentAstuce.Currenta.getId())) {
            rateService.update(ar);
        } else {
            ar.setUser(Session.actualUser);
            rateService.add(ar);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rating.setRating(rateService.getAstuceRate(CurrentAstuce.Currenta.getId()));
        image.setImage(new Image("Image/" + CurrentAstuce.Currenta.getImage_name()));

        titre.setText(CurrentAstuce.Currenta.getTitre());
        theme.setText(CurrentAstuce.Currenta.getTheme());
        description.setText(CurrentAstuce.Currenta.getDescription());

        //rating.setRating(0);
        rate.getChildren().setAll(rating);

    }

}
