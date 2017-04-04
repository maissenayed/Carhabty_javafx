/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Authentification.fxml", title = "Ajouter offre - Carhabty")
public class AuthentificationController implements Initializable {

    @FXML
    private StackPane pane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @ActionHandler
    protected FlowActionHandler actionHandler;

    @FXML
    void Inscription(ActionEvent event) throws IOException, VetoException, FlowException {

        actionHandler.navigate(InscriptionController.class);

    }

    @FXML
    void Login(ActionEvent event) throws IOException, FlowException, VetoException {

        JFXDialogLayout dl = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ok");

        String nomUser = username.getText();
        String password = this.password.getText();
        AuthentificationServices as = new AuthentificationServices();

        if (as.Authentification(nomUser, password)) {

            actionHandler.navigate(MainController.class);

            //  pane.getChildren().setAll( (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Main.fxml")));
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

    }

    @FXML
    void Quitter(ActionEvent event) {

        System.exit(0);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validator.setIcon(new Icon(AwesomeIcon.WARNING, "1em", ";", "error"));
        username.getValidators().add(validator);
        username.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                username.validate();
            }
        });

    }

}
