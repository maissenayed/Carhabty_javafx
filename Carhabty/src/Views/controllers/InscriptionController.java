/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.User;
import Services.UserServices;
import com.jfoenix.controls.*;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Inscription.fxml", title = "Carhabty")
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField rpassword;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField telephone;

    @FXML
    private JFXToggleButton role;

    @FXML
    private JFXTextField nomsociete;

    @FXML
    private JFXComboBox activite;
    @FXML
    private JFXTextField SIRET;
    @FXML
    private Label lab;

    
     @ActionHandler
    protected FlowActionHandler actionHandler;

    
    @FXML
    void register(ActionEvent event) {

        User u = new User();

        u.setAdresse(adresse.getText());
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setAdresse(adresse.getText());
        u.setPassword(password.getText());
        u.setEmail(email.getText());
        u.setUsername(username.getText());
        u.setTel(telephone.getText());

        UserServices userService = new UserServices();
        userService.add(u);

    }

    
       @FXML
        void back(ActionEvent event) throws IOException, VetoException, FlowException {

        actionHandler.navigate(AuthentificationController.class);
        // pane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AjouterUser.fxml")));
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        activite.getItems().addAll("Mécanicien", "Lavage", "Vendeur de Piéces", "Auto-école");

        lab.setVisible(false);
        nomsociete.setVisible(false);
        SIRET.setVisible(false);
        activite.setVisible(false);
        role.selectedProperty().addListener((obs, oldVal, newVal) -> {

            if (role.isSelected()) {

                nomsociete.setVisible(true);
                SIRET.setVisible(true);
                activite.setVisible(true);
                lab.setVisible(true);
            } else {

                nomsociete.setVisible(false);
                SIRET.setVisible(false);
                activite.setVisible(false);
                lab.setVisible(false);

            }

        });
    }

}
