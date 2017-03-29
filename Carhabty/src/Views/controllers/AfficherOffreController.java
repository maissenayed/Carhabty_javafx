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

        
        Offre o = new Offre(1,"aaa","fff",20,15);
        Liste.getItems().setAll(o);
        
         Liste.setCellFactory(new Callback<ListView<Offre>, ListCell<Offre>>() { 
  
    @Override 
    public ListCell<Offre> call(ListView<Offre> lv) { 
         return new RichCarListCell(); 
    } 
});
        
        
        
     

       
       
       
       
       
       
       
}
}