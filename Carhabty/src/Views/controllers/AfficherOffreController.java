/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Offre;
import Services.OffreServices;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/AfficheOffre.fxml", title = "Afficher offre - Carhabty")
public class AfficherOffreController implements Initializable {

    @FXML
    private ListView Liste;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

                System.out.println(newValue);

            }

        });

    }
}
