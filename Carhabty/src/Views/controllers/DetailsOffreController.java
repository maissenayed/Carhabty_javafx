/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Functions.CurrentOffre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class DetailsOffreController implements Initializable {

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        /*
        AfficherOffreController Af = new AfficherOffreController();
        Af.getSelected();
        System.out.println(Af.getSelected().getNomOffre());
        
        */
        
         System.out.println(CurrentOffre.Currento.getNomOffre());
        
        
        
        
        
    }    
    
}
