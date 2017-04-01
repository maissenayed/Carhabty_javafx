/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author dainer
 */
public class Test extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("AjouterTrajet.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/fxml/Spinner.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Panneau d'administration - Join My Ride");                
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
    
}
