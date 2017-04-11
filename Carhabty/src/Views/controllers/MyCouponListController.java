/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Coupon;
import Services.CouponServices;
import io.datafx.controller.FXMLController;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javax.annotation.PostConstruct;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/MyCouponList.fxml", title = "Afficher offre - Carhabty")
public class MyCouponListController {

    @FXML
    private ListView Liste;
    
    
    
     @FXML
    private Label lab;
    
    
    
    @PostConstruct
    public void init() throws SQLException{
    
       lab.setStyle("-fx-font: bold 24 System;-fx-text-fill: #34495e;");
        
        
         CouponServices coupon = new CouponServices();

        Liste.getItems().setAll(coupon.FindALL());

        Liste.setCellFactory(new Callback<ListView<Coupon>, ListCell<Coupon>>() {
           @Override
           public ListCell<Coupon> call(ListView<Coupon> param) {
               return new CouponFactory();
           }

           

      
        
        
    
    
    });
    
    
    
    
}
}