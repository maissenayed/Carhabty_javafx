/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import java.sql.Connection;

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
            
            
                
                
                
                
            
            
            
            
            
            return false;
            
            
            }

              public boolean Withdraw(double price){
              
              
              
                  
                  
                  
                  
                  
              return false;
              
              
              
              
              }  









}
