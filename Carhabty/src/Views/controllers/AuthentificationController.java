/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Services.AuthentificationServices;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.LinkAction;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Authentification.fxml", title = "Carhabty")
public class AuthentificationController  {

    
    Stage stage;
    public String roleParticulier="a:1:{i:0;s:15:\"ROLE_PATICULIER\";}";
    public String rolePartenaire="a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}";
    

    @FXMLViewFlowContext
	private ViewFlowContext context;
    
    @FXML private StackPane root;
    //private 
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
     
    @FXML
    @ActionTrigger("login")
    private JFXButton login;

    @FXML
    @LinkAction(AjouterUserController.class)
    private JFXButton inscription;
    
  
    
   
	

    
    
    
   @ActionMethod("login")
   public void Login()  {

        
        String nomUser = username.getText();
        String password = this.password.getText();
        AuthentificationServices as = new AuthentificationServices();
         
        if(as.Authentification(nomUser,password)){
            
            
            if(Session.actualUser.getRole().equals(rolePartenaire)){
                
            

            
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

    
    
    
    
   
    
}
