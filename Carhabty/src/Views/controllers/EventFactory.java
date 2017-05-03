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
public class EventFactory extends ListCell<Event> { 
  
    private final GridPane gridPane = new GridPane(); 
   
    private final Label titre = new Label(); 
    private final Label Description = new Label(); 
    private final Label Adresse = new Label();
    private final Label dateEvent = new Label();
    private final ImageView logo = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 
   
    public EventFactory() { 
        
        
        logo.setFitWidth(100);
         logo.setFitHeight(100);
        
        logo.setPreserveRatio(true); 
        GridPane.setConstraints(logo, 0, 0, 1, 4); 
        GridPane.setValignment(logo, VPos.TOP); 
      
                
        titre.setStyle("-fx-font: bold 16 System;-fx-text-fill: #750101;-fx-font-size: 1.7em;"); 
        GridPane.setConstraints(titre, 1, 0); 
        
        
        dateEvent.setStyle("-fx-font: bold 13 System;-fx-text-fill: #300511;-fx-font-size: 1.4em;"); 
        GridPane.setConstraints(dateEvent, 1, 3);
        
        Adresse.setStyle("-fx-text-fill: #060c33;-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(Adresse, 1, 2);
       
      
       Description.setStyle("-fx-text-fill: #060c33;-fx-font-size: 1.6em;");      
        GridPane.setConstraints(Description, 1, 1); 
              
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(logo, titre,Adresse,dateEvent, Description); 
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
            
            
        
            titre.setText(item.getTitle());           
            logo.setImage(new Image("Image/karhabty-logo.png")); 
            Description.setText("\nDescription :"+item.getDescription());          
            Adresse.setText("Adresse : "+item.getAdresse());                  
            dateEvent.setText("La date de l'événement : "+item.getEventDate()+"\n"+"\n");
        
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
}
//"-fx-font-size: bold 16 System;-fx-text-fill: 1.7em;-fx-text-fill: #2c3e50;"