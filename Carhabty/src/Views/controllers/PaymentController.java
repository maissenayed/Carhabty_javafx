/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Entities.Coupon;
import Functions.CurrentOffre;
import Functions.InputControl;
import Functions.sendSmsToUser;
import static Functions.sendSmsToUser.sendSmsToUser;
import Services.CouponServices;
import Services.PaymentServices;
import Services.SMSServices;
import Services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

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

    private String mergeMonthYear, Cvc, Card, Month, Year, Ref;

    private int id_account;

    private float prix;

    private int compteur = 0;

    @FXML
    private Label info, sos;

    @FXML
    void pay(ActionEvent event) throws IOException, Exception {

        Card = card.getText();
        Cvc = cvc.getText();

        JFXDialogLayout dl = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(pane, dl, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("ok");

        PaymentServices payment = new PaymentServices();
        InputControl Inc = new InputControl();

        if (Inc.InputControlPayment(Card, Cvc)) {
            Object m = month.getValue();
            Object y = year.getValue();
            Month = m.toString();
            Year = y.toString();
            mergeMonthYear = Month.concat(Year);

            if (payment.VerifyCredentialsPayment(Card, Cvc, mergeMonthYear)) {

                if (payment.getCardPayment().getEnabled() == 1) {

                    id_account = payment.getCardPayment().getIdAccount().getId();
                    prix = CurrentOffre.Currento.getPrix() - ((CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100);
                    if (payment.Withdraw(prix, id_account)) {
                        TrayNotification tray = new TrayNotification("Félicitation", "Votre Paiment a été  avec succées", SUCCESS);
                        tray.showAndWait();

                        CouponServices coupon = new CouponServices();
                        Coupon c = new Coupon();
                        Random rn = new Random();
                        int r = rn.nextInt(700 - 400) + 1;
                        Ref = "RFCC" + r;
                        c.setReference(Ref);
                        System.out.println(Ref);
                        coupon.add(c);

                        pane.getChildren().setAll((StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/GenerateCoupon.fxml")));

                    } else {
                        dl.setHeading(new Text("Erreur"));
                        dl.setBody(new Text("Votre solde est insuffisant ! Merci de recharger votre compte"));

                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event1) {
                                dialog.close();
                            }
                        });
                        dl.setActions(button);

                        dialog.show();

                    }
                } else {

                    dl.setHeading(new Text("Compte Bloqué"));
                    dl.setBody(new Text("Votre compte est bloqué contacter votre Banque"));

                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event1) {
                            dialog.close();
                        }
                    });
                    dl.setActions(button);

                    dialog.show();
                }
            } else {

                compteur++;
                System.out.println(compteur);
                if (payment.BlockAccount(compteur) == 1) {

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

                } else {

                    payment.setEnabled(payment.getIdCard(Card).getId());

                    dl.setHeading(new Text("Compte Bloqué"));
                    dl.setBody(new Text("On vous informe que votre compte bancaire a été bloqué"));
                   sendSmsToUser.sendSmsToUser("+21625532465","");
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event1) {
                            dialog.close();
                        }
                    });
                    dl.setActions(button);

                    dialog.show();
                    compteur = 0;

                }

            }

        } else {

            dl.setHeading(new Text("Erreur"));
            dl.setBody(new Text("Les champs sont vide ou non numérique . Réessayer"));

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

        UserServices users = new UserServices();
        String societe = users.findById(CurrentOffre.Currento.getUser().getId()).getNomSociete();

        sos.setText("Société : " + societe + "\nPrix à Payer : " + (CurrentOffre.Currento.getPrix() - ((CurrentOffre.Currento.getPrix() * CurrentOffre.Currento.getReduction()) / 100)) + "DT");
        info.setText("-Entrez les informations relatives à votre carte Bancaire\n"
                + "afin de terminer le processuce de paiement\n"
                + "-Devise TND");

        sos.setStyle("-fx-font: 15 System;-fx-text-fill: #000000;");
        info.setStyle("-fx-font: bold 12 System;-fx-text-fill: #c0392b;");

    }

    @FXML
    void retour(ActionEvent event) throws IOException {

        pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/DetailsOffre.fxml")));

    }

}
