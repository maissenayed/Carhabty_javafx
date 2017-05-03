/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import DataBase.Session;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.context.FXMLApplicationContext;
import javax.mail.MessagingException;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

/**
 *
 * @author GARCII
 */
public class EmailThread extends Thread {
    
    @FXMLApplicationContext
    private ApplicationContext myApplicationContext;
    
    
     public void run() {
    
         
         myApplicationContext = ApplicationContext.getInstance();
          try {
            new SendMailContact("aminegarci100@gmail.com", "Message de contact\n"
                    + "Message de la par de : " + Session.actualUser.getUsername()
                    + "\n---------------------------------------------------------"
                    + "\nInfomation sur l'utilisateur : "
                    + "\nNom : " + Session.actualUser.getNom()
                    + "\nPrenom : " + Session.actualUser.getPrenom()
                    + "\nEmail : " + Session.actualUser.getEmail()
                    + "\nTéléphone : " + Session.actualUser.getTel()
                    + "\n---------------------------------------------------------"
                    +"\n Sujet : "+myApplicationContext.getRegisteredObject("sujet")
                    +"\nMessage : "+myApplicationContext.getRegisteredObject("message")
            );

         
            
          
            
            

        } catch (MessagingException ex) {

        }
    
     }
    
    
}
