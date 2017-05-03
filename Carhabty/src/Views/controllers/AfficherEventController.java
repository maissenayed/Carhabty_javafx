/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Offre;
import Functions.CurrentOffre;
import Services.OffreServices;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/EventOffre.fxml", title = "Afficher Event - Carhabty")
public class AfficherEventController implements Initializable {

    @FXML
    private ListView Liste;
    
    @FXML
    private AnchorPane pane;
    
     @FXML
    private Label lab;

    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
       lab.setStyle("-fx-font: bold 24 System;-fx-text-fill: #34495e;");
        
        OffreServices offreService = new OffreServices();

        Liste.getItems().setAll(offreService.findALL());

        Liste.setCellFactory(new Callback<ListView<Offre>, ListCell<Offre>>() {

            @Override
            public ListCell<Offre> call(ListView<Offre> lv) {
                return new OffreFactory();
            }
        });

        Liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Offre>() {

            @Override
            public void changed(ObservableValue<? extends Offre> observable, Offre oldValue, Offre newValue) {

                
                CurrentOffre.setCurrentOffre(newValue);
                System.out.println(newValue);
                
                
                try {
                    pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/DetailsOffre.fxml")));
                } catch (IOException ex) {
                   ex.printStackTrace();
                }

            }

        });


    }


       
            

}
