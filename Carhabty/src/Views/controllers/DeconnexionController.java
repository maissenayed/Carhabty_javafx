/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Views.Authentification;
import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.FXMLController;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.context.FXMLApplicationContext;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;

/**
 *
 * @author GARCII
 */
@FXMLController(value = "/Views/fxml/Authentification.fxml", title = "Authentification")
public class DeconnexionController {

    @FXMLApplicationContext
    private ApplicationContext myApplicationContext;

   
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @PostConstruct
    public void init() throws FlowException, VetoException, Exception {

        ApplicationContext myApplicationContext = ApplicationContext.getInstance();
        flowContext = (ViewFlowContext) myApplicationContext.getRegisteredObject("flowContext");
        Stage stage = (Stage) flowContext.getRegisteredObject("Stage"); 
        stage.close();
        Authentification a = new Authentification();
        a.start(new Stage());
    }

}
