/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import DataBase.Session;
import Services.AuthentificationServices;
import Views.main.MainController;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import demos.datafx.AnimatedFlowContainer;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.LinkAction;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.annotation.*;




/**
 * FXML Controller class
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Authentification.fxml", title = "Ajouter offre - Carhabty")
public class AuthentificationController implements Initializable {

    
      @FXML
    private AnchorPane pane;
    
    @FXML
    private JFXTextField username;

   
    @FXML
    private JFXPasswordField password;
     
    @FXML 
    private JFXButton login;

    @FXML 
    private JFXButton inscription;
    
  
    
  
  
    
      @ActionHandler
    protected FlowActionHandler actionHandler;

    @FXML
    void Inscription(ActionEvent event) throws IOException {

        
         pane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/AjouterUser.fxml")));
        
    }

    @FXML
    void Login(ActionEvent event) throws IOException, FlowException, VetoException {
        
       

         String nomUser = username.getText();
        String password = this.password.getText();
        AuthentificationServices as = new AuthentificationServices();
         
        if(as.Authentification(nomUser,password)){
            
           
		
              
		actionHandler.navigate(MainController.class);
            
            
             //  pane.getChildren().setAll( (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Main.fxml")));
            
                          
        }
        
        
    }
    
    

   
    
    
      @FXML
    void Quitter(ActionEvent event) {

        System.exit(0);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
    
    
    
   
    
}
