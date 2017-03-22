/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import DataBase.Session;
import Entities.User;
import Functions.PasswordGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GARCII
 */
public class AuthentificationServices  {
    
    private Connection conn;
    private  String salt,pass,usern;
 
    
    
    
    public AuthentificationServices(){
    
    this.conn = DataSource.getInstance().getConnection();
    
    }
    
    
    public boolean Authentification(String username,String password){
    
        
        
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
        try{
            String req1 = "SELECT * FROM utilisateur WHERE username='"+username+"' AND password='"+PasswordGenerator.MergePasswordSalt(password,salt)+"'";
    
            PreparedStatement ps1 = conn.prepareStatement(req1);
            ResultSet resultat1 = ps1.executeQuery(); 
            while (resultat1.next()) { 
                
                User u = new User();
                
                
                usern = resultat1.getString("username");
                pass =  resultat1.getString("password");
                
                u.setId(resultat1.getInt("id"));
                u.setUsername(resultat1.getString("username"));
                u.setEmail(resultat1.getString("email"));
                u.setNom(resultat1.getString("nom"));
                u.setPrenom(resultat1.getString("prenom"));                                            
                u.setTel(resultat1.getString("telephone"));
                u.setAdresse(resultat1.getString("adresse"));
                u.setRole(resultat1.getString("roles"));
                u.setActivite(resultat1.getString("activite"));
                u.setActivite(resultat1.getString("nomsociete"));       
                u.setSiret(resultat1.getString("siret"));
                Session.setActualUser(u);
               
                
            }
          } catch (SQLException ex) {
              
               ex.printStackTrace(); 
           
        }
        
        
        
        if(usern == null && pass ==null){
        
            System.out.println("le nom d'utilisateur ou le mot de passe est incorrect");
            return false;
        }
        else {
        
            System.out.println("Authentifié avec succées");
           
            Session.setConnecte(true);
           
            return true ;
        }
        
    
        
    
    }
    
    
    
    
    
}
