/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Functions.EmailThread;
import Functions.SendMailContact;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.context.FXMLApplicationContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Contact.fxml", title = "Afficher offre - Carhabty")
public class ContactController {

    @FXMLApplicationContext
    private ApplicationContext myApplicationContext;

    @FXML
    private JFXTextField sujet;

    @FXML
    private JFXTextArea message;

    @FXML
    private JFXButton envoie;

    @FXML
    private Label lab;

    @PostConstruct
    public void init() {

        lab.setStyle("-fx-font: bold 25 System;-fx-text-fill: #00B16A;");

        myApplicationContext = ApplicationContext.getInstance();

        envoie.setOnAction(e -> {

            myApplicationContext.register("sujet", sujet.getText());
            myApplicationContext.register("message", message.getText());
            Thread mythread = new EmailThread();

            mythread.start();

            tray.notification.TrayNotification tr = new tray.notification.TrayNotification();
            tr.setTitle("Carhabty");
            tr.setMessage("Votre Message est envoyé avec Succés ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.SLIDE);
            tr.showAndDismiss(javafx.util.Duration.seconds(5));

        });

    }

}
