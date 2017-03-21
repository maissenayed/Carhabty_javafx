/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 *
 * @author skin
 */
public class Session {
    
    
        public static int id;
        
        public Session(){}
        
        
        public int getId(){
            
          return id;
        }
        
        public static void setId(int id){
        
          Session.id=id;
            
        }
    
    
}
