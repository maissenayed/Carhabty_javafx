/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Entities.User;

/**
 *
 * @author skin
 */
public class Session {
    
    
        public static boolean connecte;
        
        public static User actualUser;
        
        public Session(){
        
        }

        public static User getActualUser() {
        
            return actualUser;
        }

           public static void setActualUser(User actualUser) {
        
          Session.actualUser = actualUser;
            }
        
       
    
        public boolean getConnecte(){
        
        return connecte;
        }
        
        public static void setConnecte(boolean connecte){
        
        Session.connecte=connecte;
            
        }
    
}
