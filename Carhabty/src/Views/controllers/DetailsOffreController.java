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
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
        desc.setText(CurrentOffre.Currento.getDescriptionOffre());
        nomoffre.setText(CurrentOffre.Currento.getNomOffre());

        PrixFinal = CurrentOffre.Currento.getPrix() - ((CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100);
        economie = (CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100;

        solde.setText(PrixFinal + " DT\n" + "Valeur : " + CurrentOffre.Currento.getPrix() + " DT\nRemise : " + CurrentOffre.Currento.getReduction() + " %\nEconomie :" + economie + " DT");

    }

}
