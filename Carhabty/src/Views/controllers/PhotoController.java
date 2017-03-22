/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.controllers;

import Services.UserServices;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GARCII
 */
public class PhotoController implements Initializable {

    
    
     @FXML
    void upalod(ActionEvent event) {
                
        
         FileChooser chooser = new FileChooser();
         chooser.setTitle("Open File");
         File image = chooser.showOpenDialog(new Stage());
         String filePath = image.getPath();
         String fileName = image.getName();
            
     
         InputStream inStream = null;
	 OutputStream outStream = null;

    	try{

            UserServices aa = new UserServices();
           // aa.updatePhoto(t);
    	    File ImageUplaoded =new File("Image/aa"+fileName);

    	    inStream = new FileInputStream(filePath);
    	    outStream = new FileOutputStream(ImageUplaoded);

    	    byte[] buffer = new byte[1024];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	    System.out.println("File is copied successful!");

    	}catch(IOException e){
    		e.printStackTrace();
    	}   
         
        
    }
    
        
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
}
