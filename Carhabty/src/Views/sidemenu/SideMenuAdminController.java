package Views.sidemenu;

import com.jfoenix.controls.JFXListView;
import Views.controllers.*;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

@FXMLController(value = "/Views/fxml/SideMenuAdmin.fxml", title = "")
public class SideMenuAdminController {

	@FXMLViewFlowContext
	private ViewFlowContext context;

	
	@FXML
	@ActionTrigger("profile")
	private Label profile;
	
        @FXML
	@ActionTrigger("dashboard")
	private Label dashboard;
        
    
        
        @FXML
	@ActionTrigger("deconnexion")
	private Label deconnexion;
        
	
	
	@FXML
	private JFXListView<Label> sideList;

	@PostConstruct
	public void init() throws FlowException, VetoException {
		FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
		sideList.propagateMouseEventsToParent();
		sideList.getSelectionModel().selectedItemProperty().addListener((o,oldVal,newVal)->{
			if(newVal!=null){
				try {
					contentFlowHandler.handle(newVal.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});
		  Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");

		
                  bindNodeToController(profile, ProfileController.class, contentFlow, contentFlowHandler);
                  bindNodeToController(dashboard, DashboardAdminController.class, contentFlow, contentFlowHandler);                
                  bindNodeToController(deconnexion, AuthentificationController.class, contentFlow, contentFlowHandler);

	}

	private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
		flow.withGlobalLink(node.getId(), controllerClass);
	}

}
