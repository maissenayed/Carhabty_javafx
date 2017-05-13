/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Annonce;
import Entities.Voiture;
import Services.AnnonceService;
import Services.VoitureService;
import static Views.controllers.CalanderController.fromDate;
import com.jfoenix.controls.*;
import io.datafx.controller.FXMLController;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Maissen
 */
@FXMLController(value = "/Views/fxml/AfficherAnnonce.fxml", title = "Afficher offre - Carhabty")
public class AfficherAnnonceController implements Initializable {
       @FXML
    private AnchorPane pane;
        @FXML
    private Label id;

 @FXML
    private JFXTextField recherche;

    @FXML
    private TableView<Annonce> annonceview;

    @FXML
    private TableColumn titlecal;

    @FXML
    private TableColumn categorycal;

    @FXML
    private TableColumn prixcal;

    @FXML
    private TableColumn datecal;

    @FXML
    private TableColumn payecal;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea descreption;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField ville;

    @FXML
    private JFXTextField region;

    @FXML
    private JFXTextField paye;

    @FXML
    private JFXButton modification;

    @FXML
    private JFXButton supp;
    @FXML
    private JFXDatePicker annepub;
    @FXML
    private JFXComboBox categorie;
    @FXML
    private JFXButton inserer;

    ObservableList<Annonce> List= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String[] possibleWords={"bmw","audi","any","test","violo","dude"};
        TextFields.bindAutoCompletion(recherche, possibleWords);
        LoadDataFromDATAbase();
        annonceview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              Date startDate = null;
                Annonce cal = annonceview.getItems().get(annonceview.getSelectionModel().getSelectedIndex());
                title.setText(cal.getMarque());   
                descreption.setText(cal.getDescreption()); 
                prix.setText(String.valueOf(cal.getPrix()));
                ville.setText(cal.getVille());
                region.setText(cal.getRegion());
                paye.setText(cal.getPaye());
                startDate = cal.getAnneePub(); 
                annepub.setValue(fromDate(startDate));
                categorie.getItems().removeAll(categorie.getItems());
                categorie.getItems().addAll("voiture", "rechange", "piece");
                categorie.getSelectionModel().select("voiture");
                id.setText(String.valueOf(cal.getId()));
            }
        });
        
        
        
        
    }    
     public void LoadDataFromDATAbase(){
        AnnonceService crud =new AnnonceService();
      
       
        List=crud.ShowAllAnnonces(); 
        titlecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("title"));
        categorycal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("category"));
        prixcal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("prix"));
        datecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("AnneePub"));
        payecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("paye"));
        annonceview.setItems(List);
    }
    
     
     
    @FXML
    void inserer(ActionEvent event) throws IOException {
         if (pane != null) {

            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AjouterAnnonce.fxml")));
                    
        } else {
            System.out.println("probléme");
        } 
        
        
        
    }

    

    @FXML
    void modifier(ActionEvent event) {
         if (title.getText().isEmpty() || descreption.getText().isEmpty() ||annepub.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).isEmpty()
             || ville.getText().isEmpty()  || region.getText().isEmpty() || paye.getText().isEmpty()  ) {
            TrayNotification tray = new TrayNotification("ERROR", "LES CHAMPS NE SONT PAS VALID",NotificationType.ERROR);
        tray.showAndWait();        
            return;
        }
      String Title = title.getText();
      String Descreption = descreption.getText();
      java.sql.Date date = java.sql.Date.valueOf(annepub.getValue());
      float Prix =Float.valueOf(prix.getText()) ;
       String Ville = ville.getText();
       String Region = region.getText();
        String Paye = paye.getText();
        String cat= categorie.getSelectionModel().getSelectedItem().toString();
        System.out.println(cat);
      int Id =Integer.valueOf(id.getText());
      Annonce cal=new Annonce(); 
      cal.setTitle(Title);
      cal.setDescreption(Descreption);
      cal.setAnneePub(date);
      cal.setId(Id);
      cal.setPaye(Paye);
      cal.setPrix(Prix);
      cal.setVille(Ville);
      cal.setRegion(Region);
      cal.setCategory(cat);
        AnnonceService crud =new AnnonceService();
        System.out.println(cal);
        crud.UpdateAnnonce(cal);
        TrayNotification tray = new TrayNotification("Félicitation", "Votre information a été modifier avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
        LoadDataFromDATAbase();
      title.clear();
      descreption.clear();
      ville.clear();
      region.clear();
      paye.clear();
      
        
    }

    @FXML
    void supprimer(ActionEvent event) {
        Annonce cal =annonceview.getItems().get(annonceview.getSelectionModel().getSelectedIndex());
        AnnonceService crud =new AnnonceService();
        crud.DeleteAnnonce(cal.getId());
        LoadDataFromDATAbase();
        TrayNotification tray = new TrayNotification("Félicitation", "Votre information a été modifier avec succées",NotificationType.SUCCESS);
        tray.showAndWait();
    }
     
        
        @FXML
    void recherche(KeyEvent event) {
       String b= recherche.getText();
        AnnonceService crud =new AnnonceService();
        List= crud.rechercheAnnonces(b);
        titlecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("title"));
        categorycal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("category"));
        prixcal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("prix"));
        datecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("AnneePub"));
        payecal.setCellValueFactory(new PropertyValueFactory<Voiture,String>("paye"));
        annonceview.setItems(List);
          

    }
     
     
     
     
     
}
