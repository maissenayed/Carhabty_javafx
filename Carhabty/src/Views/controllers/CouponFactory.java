/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Coupon;
import Entities.Event;
import Entities.Offre;
import Entities.User;
import Services.OffreServices;
import Services.UserServices;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;


/**
 *
 * @author GARCII
 */
public class CouponFactory extends ListCell<Coupon> { 
  
    private final GridPane gridPane = new GridPane(); 
   
    private final Label DescriptionOffre = new Label(); 
    private final Label solde = new Label(); 
    private final Label Adresse = new Label();
    private final Label dateExpiration = new Label();
    private final ImageView IconOffre = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 
   
    public CouponFactory() { 
        
        
         IconOffre.setFitWidth(200);
         IconOffre.setFitHeight(200);
        
        IconOffre.setPreserveRatio(true); 
        GridPane.setConstraints(IconOffre, 0, 0, 1, 4); 
        GridPane.setValignment(IconOffre, VPos.TOP); 
      
                
        DescriptionOffre.setStyle("-fx-font-size: 1.7em;-fx-text-fill: #2c3e50;"); 
        GridPane.setConstraints(DescriptionOffre, 1, 0); 
        
        
        dateExpiration.setStyle("-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(dateExpiration, 1, 2);
        
        Adresse.setStyle("-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(Adresse, 1, 3);
       
      
        solde.setStyle("-fx-font: bold 16 System;-fx-text-fill: #c0392b;-fx-font-size: 1.7em;");      
        GridPane.setConstraints(solde, 1, 1); 
              
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(IconOffre, DescriptionOffre,Adresse,dateExpiration, solde); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane); 
    } 
  
  
    
    
    @Override 
    protected void updateItem(Coupon item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        
        
      
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            
            OffreServices ofs = new OffreServices();
            Offre o = ofs.findById(item.getOffreAchete().getId());
            int idUser = o.getUser().getId();
            UserServices us = new UserServices();
            User u = us.findById(idUser);
            
            
            
        
            solde.setText("Référence : "+item.getReference());           
            IconOffre.setImage(new Image("Image/coupon1.png")); 
            DescriptionOffre.setText("Nom Offre : "+o.getNomOffre());          
            Adresse.setText("Partenaire : "+u.getNomSociete());                  
            dateExpiration.setText("Date d'Achat : "+item.getDatee()+"\nAdresse : "+u.getAdresse());
        
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
}
