package Views;

import Views.controllers.AuthentificationController;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Authentification extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static void main(String[] args) {
     new Thread(()->{
			try {
				SVGGlyphLoader.loadGlyphsFont(Authentification.class.getResourceAsStream("/Views/fonts/icomoon.svg"),"icomoon.svg");
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
		}).start();
   
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Flow flow = new Flow(AuthentificationController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        
        ApplicationContext myApplicationContext=ApplicationContext.getInstance();
        myApplicationContext.register("flowContext", flowContext);
        
        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator, 1020, 700);
      //  scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-design.css").toExternalForm());
        scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-main-demo.css").toExternalForm());
        //		stage.initStyle(StageStyle.UNDECORATED);
        //		stage.setFullScreen(true);
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }
 
}
