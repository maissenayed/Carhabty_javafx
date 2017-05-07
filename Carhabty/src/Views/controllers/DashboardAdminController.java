/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Services.CouponServices;
import Services.EventServices;
import Services.OffreServices;
import Services.UserServices;
import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/DashboardAdmin.fxml", title = "Admin dashboard - Carhabty")
public class DashboardAdminController {

    @FXML
    private Label lab;

    @FXML
    private Label lab2;

    @FXML
    private Label lab1;

    @FXML
    private Label lab3;

    @FXML
    private Label lab4;

    @FXML
    private Label lab5;

    @FXML
    private Label lab6;

    @FXML
    private Label lab7;

    @FXML
    private Label lab8;

    @PostConstruct
    public void init() {

        lab.setStyle("-fx-font: bold 24 System;-fx-text-fill: #c0392b;");
        lab1.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab2.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab3.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab4.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab5.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab6.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab7.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");
        lab8.setStyle("-fx-font: bold 20 System;-fx-text-fill: #34495e;");

        UserServices userService = new UserServices();
        OffreServices offreService = new OffreServices();
        EventServices eventService = new EventServices();
        CouponServices couponService = new CouponServices();

        lab5.setText(Integer.toString(couponService.NombreCoupon()));
        lab6.setText(Integer.toString(offreService.NombreOffre()));
        //lab7.setText(Integer.toString(eventService.NombreEvent()));
        lab8.setText(Integer.toString(userService.NombreUser()));

    }

}
