/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;

/**
 *
 * @author GARCII
 */
 @FXMLController(value = "/Views/fxml/Test.fxml", title = "Test weld 9ahba")
public class TestController {
   
@FXMLViewFlowContext
	private ViewFlowContext context;

                    
        
    
    
   
    
    
   
    
       @FXML
    void hamma(ActionEvent event) throws FlowException, VetoException{
        
               System.out.println("hamma ya mnayék");
               
		

    }
    
    
    }
