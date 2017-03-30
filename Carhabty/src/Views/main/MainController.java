package Views.main;

import DataBase.Session;
import Views.controllers.DashboardAdminController;
import Views.controllers.ProfileController;
import Views.sidemenu.SideMenuAdminController;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import demos.datafx.AnimatedFlowContainer;
import Views.sidemenu.SideMenuController;
import Views.sidemenu.SideMenuProController;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

@FXMLController(value = "/Views/fxml/Main.fxml")
public class MainController {

	@FXMLViewFlowContext
	private ViewFlowContext context;

	@FXML private StackPane root;

	@FXML private StackPane titleBurgerContainer;
	@FXML private JFXHamburger titleBurger;

	@FXML private StackPane optionsBurger;	
	@FXML private JFXRippler optionsRippler;
	@FXML private JFXDrawer drawer;

	private JFXPopup toolbarPopup;
	private FlowHandler flowHandler;
	private FlowHandler sideMenuFlowHandler;
        
         public String roleParticulier="a:1:{i:0;s:15:\"ROLE_PATICULIER\";}";
        public String rolePartenaire="a:1:{i:0;s:15:\"ROLE_PARTENAIRE\";}";
         public String roleAdmin="a:1:{i:0;s:15:\"ROLE_ADMIN\";}";

	@PostConstruct
	public void init() throws FlowException, VetoException {

            
              try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/popup/MainPopup.fxml"));
			loader.setController(new InputController());
			toolbarPopup = new JFXPopup(loader.load());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
            
            
		// init the title hamburger icon
		drawer.setOnDrawerOpening((e) -> {
			titleBurger.getAnimation().setRate(1);
			titleBurger.getAnimation().play();
		});
		drawer.setOnDrawerClosing((e) -> {
			titleBurger.getAnimation().setRate(-1);
			titleBurger.getAnimation().play();
		});
		titleBurgerContainer.setOnMouseClicked((e)->{
			if (drawer.isHidden() || drawer.isHidding()) drawer.open();
			else drawer.close();
		});


		optionsBurger.setOnMouseClicked((e) -> {
			toolbarPopup.show(optionsBurger, PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15);
		});

		// create the inner flow and content
		context = new ViewFlowContext();
		// set the default controller 
                
                
                
                
                  
      
                
                
		Flow innerFlow = new Flow(ProfileController.class);

		flowHandler = innerFlow.createHandler(context);
		context.register("ContentFlowHandler", flowHandler);
		context.register("ContentFlow", innerFlow);
		drawer.setContent(flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.SWIPE_LEFT)));
		context.register("ContentPane", drawer.getContent().get(0));

                
                
                   Class<?> factory = null;
                if(Session.actualUser.getRole().equals(rolePartenaire)){
                
                factory = SideMenuProController.class;
                    
                }else if(Session.actualUser.getRole().equals(roleParticulier)){
                
                factory = SideMenuController.class;
                }else if(Session.actualUser.getRole().equals(roleAdmin)){
                
                factory = SideMenuAdminController.class;
                    
                }else{
                
                    System.out.println("error");
                    
                }
                
                
		Flow sideMenuFlow = new Flow(factory);
		// side controller will add links to the content flow
		//Flow sideMenuFlow = new Flow(SideMenuController.class);
		sideMenuFlowHandler = sideMenuFlow.createHandler(context);
		drawer.setSidePane(sideMenuFlowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.SWIPE_LEFT)));
	}

	public class InputController{
		@FXML private JFXListView<?> toolbarPopupList;
		// close application
		@FXML private void submit() {
			if(toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) Platform.exit();
		}
	}
}
