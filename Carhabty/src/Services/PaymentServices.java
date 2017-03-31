/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.History;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author GARCII
 */
public class PaymentServices {

    private Connection conn;
 

    public PaymentServices() {

        this.conn = DataSource.getInstance().getConnection();

    }


            public boolean VerifyCredentialsPayment(String card,String cvc,String dateExp){
            
            String req ="SELECT * FROM card WHERE num_card = ? AND cvc = ? AND expdate = ? ";
                
                
                 try {
             
              PreparedStatement ps = conn.prepareStatement(req);
              ps.setString(1,card);
              ps.setString(2,cvc);
              ps.setString(3,dateExp);
              ResultSet resultat = ps.executeQuery(); 
              while (resultat.next()) {
        
                       
                  System.out.println("Credentials Verified successfully");
                  return true;
                  
                  
                }
            
            
             } catch (SQLException ex) {
               
               
               ex.printStackTrace(); 
            
        }
                
            
            System.out.println("Erreur wrong Credentials");
              return false;
            
            
         
            
            
            }

              public boolean Withdraw(double price){
              
              
                    
                  
                  
                  
                  
                  
              return false;
              
              
              
              
              }  


              
                public List<History> CheckHistroy(){
                
                
                List<History> history = null ;
                
                return history;
                
                
                
                }






}
