package Views.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;

import Views.controllers.SideMenuController;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

@FXMLController(value = "Main.fxml", title = "Material Design Example")
public class MainController {

	@FXMLViewFlowContext
	private ViewFlowContext context;

	@FXML private StackPane root;

	@FXML private StackPane titleBurgerContainer;
	@FXML private JFXHamburger titleBurger;

	@FXML private StackPane optionsBurger;	

	@FXML private JFXDrawer drawer;

	private JFXPopup toolbarPopup;
	

	@PostConstruct
	public void init() throws FlowException, VetoException {

         titleBurgerContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent e) {
                 if (drawer.isHidden() || drawer.isHidding()) drawer.open();
                 else drawer.close();
             }
         });
       
            
            
            
	}

	
}
