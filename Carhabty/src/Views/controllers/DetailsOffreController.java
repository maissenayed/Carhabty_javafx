/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Functions.CurrentOffre;
import Services.UserServices;
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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class DetailsOffreController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label activite;

    @FXML
    private Label adresse;

    @FXML
    private Label telephone;

    @FXML
    private Label nomSociete;

    @FXML
    private Label desc;

    @FXML
    private Label nomoffre;

    @FXML
    private Label solde;

    @FXML
    private Label countdown;
    
    @FXML
    private Label lab1,lab;
    
    
    @FXML
    private AnchorPane pane;

    private float PrixFinal, economie;

    @FXML
    void Paiement(ActionEvent event) throws IOException {

        
        pane.getChildren().setAll((StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Payment.fxml")));
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        image.setImage(new Image("Image/" + CurrentOffre.Currento.getImage()));

        int idUser = CurrentOffre.Currento.getUser().getId();
        UserServices user = new UserServices();
        String Adresse = user.findById(idUser).getAdresse();
        String Activite = user.findById(idUser).getActivite();
        String tele = user.findById(idUser).getTel();
        String nomS = user.findById(idUser).getNomSociete();
        adresse.setText("Adresse : "+Adresse);
        telephone.setText("Téléphne : "+tele);
        activite.setText("Activité : "+Activite);
        nomSociete.setText("Nom de Societé : "+nomS);
        desc.setText("Description offre : "+CurrentOffre.Currento.getDescriptionOffre());
        nomoffre.setText(" Nom offre : "+CurrentOffre.Currento.getNomOffre());

        PrixFinal = CurrentOffre.Currento.getPrix() - ((CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100);
        economie = (CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100;

        solde.setText("Prix : "+PrixFinal + " DT\n\n\n" + "Valeur : " + CurrentOffre.Currento.getPrix() + " DT\n\n\nRemise : " + CurrentOffre.Currento.getReduction() + " %\n\n\nEconomie :" + economie + " DT");

    
         adresse.setStyle("-fx-font:  14 System;-fx-text-fill: #2c3e50;");
         telephone.setStyle("-fx-font:  14 System;-fx-text-fill: #2c3e50;");
         activite.setStyle("-fx-font:  14 System;-fx-text-fill: #2c3e50;");
         nomSociete.setStyle("-fx-font:  14 System;-fx-text-fill: #2c3e50;");
         lab.setStyle("-fx-font: bold 17 System;-fx-text-fill: #00B16A;");
         lab1.setStyle("-fx-font: bold 18 System;-fx-text-fill: #00B16A;");
         
         
         nomoffre.setStyle("-fx-font:  14 System;");
                desc.setStyle("-fx-font:  14 System;");
                 solde.setStyle("-fx-font: bold 15 System;");
         
         
         
         
         
    }

}
