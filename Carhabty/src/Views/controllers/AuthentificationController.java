/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Interfaces.IService;
import Services.AuthentificationServices;
import Views.main.MainController;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.annotation.PostConstruct;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Authentification.fxml", title = "Ajouter offre - Carhabty")
public class AuthentificationController {

    @FXML
    private StackPane pane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @ActionHandler
    protected FlowActionHandler actionHandler;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton quitter;

    @FXML
    private JFXButton inscription;

    @PostConstruct
    public void init() {

      
        inscription.setOnAction(e -> {
            try {
                actionHandler.navigate(InscriptionController.class);
            } catch (VetoException ex) {
                Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlowException ex) {
                Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        login.setOnAction(e -> {

            JFXDialogLayout dl = new JFXDialogLayout();
            JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("ok");

            String nomUser = username.getText();
            String password = this.password.getText();

            AuthentificationServices as = new AuthentificationServices();

            if (as.Authentification(nomUser, password)) {

                try {
                    actionHandler.navigate(MainController.class);

                    //  pane.getChildren().setAll( (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Main.fxml")));
                } catch (VetoException ex) {
                    Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FlowException ex) {
                    Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                dl.setHeading(new Text("Erreur"));
                dl.setBody(new Text("Nom d'utilisateur ou mot de passe incorrect"));
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event1) {
                        dialog.close();
                    }
                });
                dl.setActions(button);
                dialog.show();

            }

        });

        quitter.setOnAction(e -> {
        
        System.exit(0);
        });
        
    }

}
