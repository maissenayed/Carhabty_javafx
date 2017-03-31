/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Functions.InputControl;
import Services.PaymentServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.awt.im.InputContext;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class PaymentController implements Initializable {

    @FXML
    private JFXTextField card;

    @FXML
    private JFXTextField cvc;

    @FXML
    private JFXComboBox year;

    @FXML
    private JFXComboBox month;

    @FXML
    private StackPane pane;

    private String mergeMonthYear, Cvc, Card;

    @FXML
    void pay(ActionEvent event) {

        Card = card.getText();
        Cvc = cvc.getText();

        JFXDialogLayout dl = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ok");

      
       // System.out.println(mergeMonthYear);

        PaymentServices payment = new PaymentServices();
        InputControl Inc = new InputControl();

        if (Inc.InputControlPayment(Card, Cvc) && !month.getValue().toString().concat(year.getValue().toString()).isEmpty()) {

            mergeMonthYear = month.getValue().toString().concat(year.getValue().toString());
            
            if (payment.VerifyCredentialsPayment(Card, Cvc, mergeMonthYear)) {

                
                System.out.println("mriguil");
                
            }

        } else {

            dl.setHeading(new Text("Erreur"));
            dl.setBody(new Text("les informations que vous avez mis sembles incorrectes. Réessayer"));

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    dialog.close();
                }
            });
            dl.setActions(button);

            dialog.show();

        }

        //    System.out.println(Inc.InputControlPayment(Card, Cvc));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        year.getItems().addAll(
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30"
        );

        month.getItems().addAll(
                "01",
                "02",
                "03",
                "04",
                "05",
                "06",
                "07",
                "08",
                "09",
                "10",
                "11",
                "12"
        );

    }

}
