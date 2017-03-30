/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Offre;
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
public class OffreFactory extends ListCell<Offre> { 
  
    private final GridPane gridPane = new GridPane(); 
   
    private final Label DescriptionOffre = new Label(); 
    private final Label NomOffre = new Label(); 
    private final Label solde = new Label(); 
    private final Label Adresse = new Label();
    private final Label dateExpiration = new Label();
    private final ImageView IconOffre = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 
    private float economie,PrixFinal;
  
    public OffreFactory() { 
        
        
        IconOffre.setFitWidth(180);
         IconOffre.setFitHeight(180);
        
        IconOffre.setPreserveRatio(true); 
        GridPane.setConstraints(IconOffre, 0, 0, 1, 3); 
        GridPane.setValignment(IconOffre, VPos.TOP); 
        // 
        
           
        DescriptionOffre.setStyle("-fx-font-size: 1.7em; -fx-font-style: italic;-fx-text-fill: #2c3e50;"); 
        GridPane.setConstraints(DescriptionOffre, 1, 0); 
        
        
        dateExpiration.setStyle("-fx-text-fill: #3498db;-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(dateExpiration, 2, 2);
        
        Adresse.setStyle("-fx-text-fill: #16a085;-fx-font-size: 1.6em;"); 
        GridPane.setConstraints(Adresse, 2, 0);
        //
        
        NomOffre.setStyle("-fx-font-size: 1em;"); 
        GridPane.setConstraints(NomOffre, 1, 1); 
      
        solde.setStyle("-fx-text-fill: #e74c3c;-fx-font-size: 1.7em;");      
        GridPane.setConstraints(solde, 2, 1); 
        //GridPane.setColumnSpan(solde, Integer.MAX_VALUE); 
        //         
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(IconOffre, DescriptionOffre,Adresse,dateExpiration,NomOffre, solde); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane); 
    } 
  
  
    
    
    @Override 
    protected void updateItem(Offre item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        
        
      
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            
            
            
            PrixFinal = item.getPrix() - ((item.getPrix() * item.getReduction())/100);
            economie = (item.getPrix() * item.getReduction())/100;
        
      
            
            NomOffre.setText(item.getDescriptionOffre()); 
            DescriptionOffre.setText(item.getNomOffre());           
            IconOffre.setImage(new Image("Image/"+item.getImage())); 
            solde.setText(PrixFinal+" DT\n"+"Valeur : "+item.getPrix()+" DT\nRemise : "+item.getReduction()+" %\nEconomie :"+economie+" DT"); 
            
            
            int idUser = item.getUser().getId();
            UserServices user = new UserServices();
            String adresse =  user.findById(idUser).getAdresse();
            String Activite = user.findById(idUser).getActivite();
            
           
            
            
            
            Adresse.setText("Adresse : "+adresse);
                    
            dateExpiration.setText("Expire le : "+item.getDateExp());
        
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
}
