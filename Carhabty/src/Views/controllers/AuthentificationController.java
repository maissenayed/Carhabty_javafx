/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Services.AuthentificationServices;
import Views.AccueilPartenaire;
import Views.Authentification;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class AuthentificationController implements Initializable {

    
    public String roleParticulier="a:1:{i:0;s:15:\"ROLE_PATICULIER\";}";
    public String rolePartenaire="a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}";
   
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    
     @FXML
    private AnchorPane rootPane;
    
    
    
    @FXML
    void Login(ActionEvent event) throws IOException, Exception {

        
        String nomUser = username.getText();
        String password = this.password.getText();
        AuthentificationServices as = new AuthentificationServices();
         
        if(as.Authentification(nomUser,password)){
            
            
            if(Session.actualUser.getRole().equals(rolePartenaire)){
               
                
             try {
             AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource(("Views/fxml/AccueilPartenaire.fxml")));
            rootPane.getChildren().setAll(pane);

                } catch (IOException ex) {
                    Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                }    

            
            }else if(Session.actualUser.getRole().equals(roleParticulier)){
            
                System.out.println("role particulier");
            
            }else {
            
            
            
            }
        //System.out.println(as.Authentification(nomUser,password));
       // System.out.println(Session.actualUser.getId()); 
        //System.out.println(Session.actualUser.getRole());
        
        
        
        
        }
        
        
        
    }

    @FXML
    void Register(ActionEvent event) {

        
        
        
        
        
    }
    
    
      @FXML
    void Exit(ActionEvent event) {

        System.exit(0);
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    
   
    
}
