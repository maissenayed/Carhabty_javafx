/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;



import Entities.Voiture;
import Functions.CurrentVoiture;
import static Views.controllers.CalanderController.fromDate;


import Services.VoitureService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Maissen
 */
@FXMLController(value = "/Views/fxml/affichervoiture.fxml", title = "Afficher offre - Carhabty")
public class AfficherVoitureController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Voiture> voitureview;
    @FXML
    private JFXButton insertvoiture;
    @FXML
    private JFXButton modvoiture;
    @FXML
    private JFXButton deletevoiture;
    @FXML
    private JFXButton todolist;
    
    ObservableList<Voiture> List= FXCollections.observableArrayList();
    @FXML
    private TableColumn marquecell;
    @FXML
    private TableColumn modelcell;
    @FXML
    private TableColumn anneecell;
   
    private TableColumn photocell;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField model;
    @FXML
    private DatePicker annee;
    @FXML
    private JFXTextField id;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadDataFromDATAbase();
      voitureview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Date startDate = null;
                Voiture cal = voitureview.getItems().get(voitureview.getSelectionModel().getSelectedIndex());
                marque.setText(cal.getMarque());   
                startDate = cal.getAnnee(); 
                annee.setValue(fromDate(startDate));
                model.setText(cal.getModel());
                id.setText(String.valueOf(cal.getId()));
            }
        });
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         

       // TODO
    }    

    @FXML
    private void insertvoiture(ActionEvent event) throws IOException {
    if (pane != null) {

            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/ajoutvoiture.fxml")));
                    
        } else {
            System.out.println("probléme");
        } 
        
         
    }

    @FXML
    private void modificationvoiture(ActionEvent event) {
        if (marque.getText().isEmpty() || model.getText().isEmpty() ||annee.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
                ) {
            TrayNotification tray = new TrayNotification("ERROR", "LES CHAMPS NE SONT PAS VALID",NotificationType.ERROR);
        tray.showAndWait();        
            return;
        }   
      String Marque = marque.getText();
      String Modele = model.getText();
      java.sql.Date date = java.sql.Date.valueOf(annee.getValue());
       int Id =Integer.valueOf(id.getText());
      Voiture cal=new Voiture(); 
      cal.setMarque(Marque);
      cal.setModel(Modele);
      cal.setAnnee(date);
      cal.setId(Id);
        VoitureService crud =new VoitureService();
        System.out.println(cal);
        crud.UpdateVoiture(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre information a été modifier avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
        LoadDataFromDATAbase();
        marque.clear();
        model.clear();
        
        
      
    
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void deletevoiture(ActionEvent event) {
        Voiture cal = voitureview.getItems().get(voitureview.getSelectionModel().getSelectedIndex());
        VoitureService crud =new VoitureService();
        crud.DeleteVoiture(cal.getId());
        LoadDataFromDATAbase();
    }

    @FXML
    private void todolist(ActionEvent event) throws IOException {
             Voiture cal = voitureview.getItems().get(voitureview.getSelectionModel().getSelectedIndex());
             System.out.println(cal.getId()); 
             CurrentVoiture val =new  CurrentVoiture();
             val.setCurrentVoiture(cal);
    if (pane != null) {

            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Calander.fxml")));
                    
        } else {
            System.out.println("probléme");
        }
    }
    
    
    
    
    public void LoadDataFromDATAbase(){
        VoitureService crud =new VoitureService();
        //get to do list
        
        List=crud.ShowAllVoiture(); 
        marquecell.setCellValueFactory(new PropertyValueFactory<Voiture,String>("marque"));
        modelcell.setCellValueFactory(new PropertyValueFactory<Voiture,String>("model"));
        anneecell.setCellValueFactory(new PropertyValueFactory<Voiture,String>("annee"));
        voitureview.setItems(List);
    }
}
