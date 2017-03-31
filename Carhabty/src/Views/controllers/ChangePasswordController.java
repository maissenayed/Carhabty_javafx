package Views.controllers;

import DataBase.Session;
import Entities.User;
import Functions.PasswordGenerator;
import Functions.getSalt;
import Services.UserServices;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;



public class ChangePasswordController implements Initializable {

    @FXML
    private StackPane pane;

    @FXML
    private JFXPasswordField acc;

    @FXML
    private JFXPasswordField npc;

    @FXML
    private JFXPasswordField rnpc;

    @FXML
    void ChangePassword(ActionEvent event) throws IOException {

        String acl = acc.getText();
        String npl = npc.getText();
        String rnpl = rnpc.getText();

        getSalt gs = new getSalt();
        String salt = gs.getCurrentSalt(Session.actualUser.getUsername());
        System.out.println(salt);

        JFXDialogLayout dl = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ok");

        if (acl.isEmpty() || npl.isEmpty() || rnpl.isEmpty()) {

            dl.setHeading(new Text("Erreur"));
            dl.setBody(new Text("Les champs sont vides. Réessayer"));

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    dialog.close();
                }
            });
            dl.setActions(button);

            dialog.show();

        } else if (!Session.actualUser.getPassword().equals(PasswordGenerator.MergePasswordSalt(acl, salt))) {

            dl.setHeading(new Text("Erreur"));
            dl.setBody(new Text("Le mot de passe actuel est invalide. Réessayer"));

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    dialog.close();
                }
            });
            dl.setActions(button);

            dialog.show();

        } else if (!npl.equals(rnpl)) {

            dl.setHeading(new Text("Erreur"));
            dl.setBody(new Text("Les deux mots de passe ne sont pas identiques. Réessayer"));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    dialog.close();
                }
            });
            dl.setActions(button);
            dialog.show();

        } else {

            User u = new User();
            u.setPassword(npl);
            System.out.println(u.getPassword());
            u.setId(Session.actualUser.getId());
            u.setUsername(Session.actualUser.getUsername());
            u.setEmail(Session.actualUser.getEmail());
            u.setNom(Session.actualUser.getNom());
            u.setPrenom(Session.actualUser.getPrenom());
            u.setTel(Session.actualUser.getTel());

            UserServices UserService = new UserServices();
            UserService.update(u);
            
            
             dl.setHeading(new Text("Information"));
            dl.setBody(new Text("Votre mot de passe a été change avec succés"));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    dialog.close();
                }
            });
            dl.setActions(button);
            dialog.show();
            
            acc.setText("");
            npc.setText("");
            rnpc.setText("");
            
            
            
            
            // pane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Profile.fxml")));
            if (pane != null) {

//                pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Profile.fxml")));

            } else {
               // System.out.println("probléme");
            }
        }

    }

    /*
       
        // User u = new User();
        //UserServices userService = new UserServices();
        //userService.update(u);
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
