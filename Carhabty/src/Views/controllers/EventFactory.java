/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Event;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
public class EventFactory extends ListCell<Event> { 
  
    private final GridPane gridPane = new GridPane(); 
   
    private final Label DescriptionOffre = new Label(); 
    private final Label solde = new Label(); 
    private final Label Adresse = new Label();
    private final Label dateExpiration = new Label();
    private final ImageView IconOffre = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 
   
    public EventFactory() { 
        
        
         IconOffre.setFitWidth(100);
         IconOffre.setFitHeight(100);
        
        IconOffre.setPreserveRatio(true); 
        GridPane.setConstraints(IconOffre, 0, 0, 1, 4); 
        GridPane.setValignment(IconOffre, VPos.TOP); 
      
                
        DescriptionOffre.setStyle("-fx-font-size: 1.7em;-fx-text-fill: #2c3e50;"); 
        GridPane.setConstraints(DescriptionOffre, 1, 0); 
        
        
        dateExpiration.setStyle("-fx-text-fill: #3498db;-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(dateExpiration, 1, 2);
        
        Adresse.setStyle("-fx-text-fill: #16a085;-fx-font-size: 1.6em;"); 
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
    protected void updateItem(Event item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        
        
      
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            
            
        
            DescriptionOffre.setText(item.getTitle());           
            //IconOffre.setImage(new Image("Image/"+item.getPhoto())); 
            solde.setText("\nDescription :"+item.getDescription());          
            Adresse.setText("Adresse : "+item.getAdresse());                  
            dateExpiration.setText("date de l'evenément : "+item.getEventDate());
        
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
}
