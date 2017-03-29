/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Entities.Offre;
import javafx.scene.control.ListCell;

/**
 *
 * @author GARCII
 */
public class SimpleCarListCell extends ListCell<Offre> { 
  
    @Override 
    protected void updateItem(Offre item, boolean empty) { 
        super.updateItem(item, empty); 
        setText(null); 
        if (!empty && item != null) { 
            final String text = String.format("%s %s", item.getId(), item.getNomOffre()); 
            setText(text); 
        } 
    } 
}
