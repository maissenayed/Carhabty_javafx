/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import DataBase.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GARCII
 */
public class getSalt {
    
    private Connection conn ;
    private String salt;
    public getSalt(){
    
    this.conn = DataSource.getInstance().getConnection();
    }
    
    public String getCurrentSalt(String username){
    
    
    String req ="SELECT salt FROM utilisateur WHERE username = ? ";

      try {
             
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,username);
            ResultSet resultat = ps.executeQuery(); 
            while (resultat.next()) {
            salt = resultat.getString("salt");
                       
            }
            
            
             } catch (SQLException ex) {
               System.out.println("erreur isfound");
               ex.printStackTrace(); 
           
        }
    
    return salt;
    
    } 
    
    
    
    
}
