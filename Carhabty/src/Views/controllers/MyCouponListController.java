/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Coupon;
import Entities.Offre;
import Services.CouponServices;
import Services.OffreServices;
import Services.UserServices;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.annotation.PostConstruct;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/MyCouponList.fxml", title = "Afficher offre - Carhabty")
public class MyCouponListController{

    
    
     @FXML
    private Label lab;

    @FXML
    private TableView table;

    @FXML
    private TableColumn offreColumn;

    @FXML
    private TableColumn partenaireColumn;

    @FXML
    private TableColumn adresseColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn refColumn;
    
     String adresse,nomoffre,nomsociete;
    
    
    @PostConstruct
    public void init() {
        
         lab.setStyle("-fx-font: bold 25 System;-fx-text-fill: #00B16A;");
        
        
         try {
             FillTable();
         } catch (SQLException ex) {
             Logger.getLogger(MyCouponListController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
        
        
    } 
    
    
    private void FillTable() throws SQLException {

        CouponServices CoupnService = new CouponServices();
        
        
        ObservableList<Coupon> data = FXCollections.observableArrayList((Coupon) null);
        ResultSet set;
      
        
        set = CoupnService.MyListCoupon();

        while (set.next()) {

              
            
            
            int id_offre = set.getInt("idOffre");
           
            OffreServices offreservice = new OffreServices();
             nomoffre = offreservice.findById(id_offre).getNomOffre();
            int id_user = offreservice.findById(id_offre).getUser().getId();
           
            UserServices userservice = new UserServices();
             adresse = userservice.findById(id_user).getAdresse();
             nomsociete = userservice.findById(id_user).getNomSociete();
            System.out.println("ad"+adresse+"nom"+nomsociete+"nomof"+nomoffre);
            Coupon c = new Coupon(set.getString("reference"),set.getDate("date").toString(),adresse,nomoffre, nomsociete);
          
             data.add(c);
        }

      

        offreColumn.setCellValueFactory(
                new PropertyValueFactory<Coupon, String>("offre")
        );
        
        partenaireColumn.setCellValueFactory(
                new PropertyValueFactory<Coupon, String>("nomsociete")
        );
        
        adresseColumn.setCellValueFactory(
                new PropertyValueFactory<Coupon, String>("adresse")
        );
        
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<Coupon, String>("fakeDate")
        );
        
        refColumn.setCellValueFactory(
                new PropertyValueFactory<Coupon, String>("reference")
        );
      
        
        
        
        

        table.setItems(data);
  
    }
    
  
    
    
}
