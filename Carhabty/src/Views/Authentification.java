/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Authentification extends Application {

    
   
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Authentification.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 730, 500);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    
   
    
    public static void main(String[] args) {
        launch(args);
    }
}