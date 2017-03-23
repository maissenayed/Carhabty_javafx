/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.controllers.AuthentificationController;
import Views.main.MainController;
import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Authentification extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void start(Stage stage) throws FlowException, VetoException {

        Flow flow = new Flow(AuthentificationController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);
        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator, 400, 450);
        scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-design.css").toExternalForm());
        scene.getStylesheets().add(Authentification.class.getResource("/Views/css/jfoenix-main-demo.css").toExternalForm());

        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
        /*Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Authentification.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 730, 500);
        
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
