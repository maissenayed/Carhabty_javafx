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
import Functions.SaltGenerator;
import Interfaces.IService;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skin
 */
public class UserServices implements IService<User>{
    
     private Connection conn;
     private String salt = SaltGenerator.GenerateSalt();
   public UserServices(){
   
         this.conn = DataSource.getInstance().getConnection();
       
   }

    @Override
    public boolean add(User t) {
       
        
        String req = "INSERT INTO utilisateur (username,username_canonical,email,email_canonical,enabled,salt,password,roles,nom,prenom,telephone,adresse,nomsociete,activite,siret) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               
        StringBuffer y = new StringBuffer();
        y.append("a:1:{i:0;s:15:");
        y.append('"');
        y.append("ROLE_PATICULIER");
        y.append('"');
        y.append(";}");
       
        try {
             
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getUsername());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getEmail());
            ps.setInt(5, 1);
            ps.setString(6,salt);
            ps.setString(7, PasswordGenerator.MergePasswordSalt(t.getPassword(), salt));          
            ps.setString(8, y.toString());
            ps.setString(9, t.getNom());
            ps.setString(10, t.getPrenom());
            ps.setString(11, t.getTel());
            ps.setString(12, t.getAdresse());
            ps.setString(13, t.getNomSociete());
            ps.setString(14, t.getActivite());
            ps.setString(15, t.getSiret());

            ps.executeUpdate();
            System.out.println("Insertion terminé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Problème d'insertion");
            ex.printStackTrace(); 
            return false;
        }
        
        
        
    }

    @Override
    public boolean update(User t) {
       
         String req = "UPDATE utilisateur SET username = ? , username_canonical = ? , email = ?, email_canonical = ? , salt = ? , password = ? , nom = ? , prenom = ? , telephone = ? , adresse = ? WHERE id = ?";
        
          try {
             
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getUsername());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getEmail());
            ps.setString(5, salt);
            ps.setString(6, PasswordGenerator.MergePasswordSalt(t.getPassword(), salt));
            ps.setString(7, t.getNom());
            ps.setString(8, t.getPrenom());
            ps.setString(9, t.getTel());
            ps.setString(10, t.getAdresse());
            ps.setInt(11, t.getId());
            
            
            ps.executeUpdate();
            System.out.println("Modification terminé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Problème de Modification");
            ex.printStackTrace(); 
            return false;
        }
        
    }

    @Override
    public boolean remove(User t) {
        
        try {
            String req = "DELETE FROM utilisateur WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("utilisateur supprimé");
            return true;
       } catch (SQLException ex) {
            System.out.println("Problème de suppression");
            ex.printStackTrace(); 
            return false;
        }
        
    }

    @Override
    public List<User> findALL() {
       
        List<User> ListeUtilisateur = new ArrayList<>();

        User u;
        try {

            String req = " SELECT * FROM utilisateur";  
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet resultat = ps.executeQuery(); 
            while (resultat.next()) {
             
                u = new User();
              
                u.setId(resultat.getInt("id"));
                u.setUsername(resultat.getString("username"));
                u.setEmail(resultat.getString("email"));
                u.setNom(resultat.getString("nom"));
                u.setPrenom(resultat.getString("prenom"));                                            
                u.setTel(resultat.getString("telephone"));
                u.setAdresse(resultat.getString("adresse"));
                u.setRole(resultat.getString("roles"));
                u.setActivite(resultat.getString("activite"));
                u.setActivite(resultat.getString("nomsociete"));       
                u.setSiret(resultat.getString("siret"));
                ListeUtilisateur.add(u);
                
            }
       } catch (SQLException ex) {
            System.out.println("La liste ne peut pas etre affiché");
            ex.printStackTrace(); 
            
        }
        return ListeUtilisateur;        
    }

    @Override
    public User findById(int id) {
        
        User u = null;
        
        try {

            String req = "SELECT `username`,`email`,`roles`, `nom`, `prenom`, `telephone`, `adresse`, `nomsociete`, `activite`, `siret` FROM `utilisateur` WHERE id = ? ";
            
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery(); 
            while (resultat.next()) {
             
                u = new User();
                             
                u.setUsername(resultat.getString("username"));
                u.setEmail(resultat.getString("email"));
                u.setRole(resultat.getString("roles"));
                u.setNom(resultat.getString("nom"));
                u.setPrenom(resultat.getString("prenom"));                                            
                u.setTel(resultat.getString("telephone"));
                u.setAdresse(resultat.getString("adresse"));
                u.setActivite(resultat.getString("nomsociete"));   
                u.setActivite(resultat.getString("activite"));                   
                u.setSiret(resultat.getString("siret"));
                     
            }
       } catch (SQLException ex) {
            System.out.println("Problème d'affichage");
            ex.printStackTrace(); 
            
        }
            return u ;      
    }
    
    
    
     public boolean updatePhoto(User t)  {
        try {
         
            String req = "UPDATE utilisateur SET photo = ? WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
           
            
            ps.setString(1, t.getImage());
            ps.setInt(2, Session.actualUser.getId());
            ps.executeUpdate();
            System.out.println("photo modifiée avec succées");
            return true;
        
        } catch (SQLException ex) {
                     System.out.println("photo n'est pas modifiée");
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<User> getListePartenaire() {
    
        
        List<User> ListPartenaire = null ; 
        User partenaire;
        StringBuffer y = new StringBuffer();        
        y.append("a:1:{i:0;s:15:");
        y.append('"');
        y.append("ROLE_PARTENAIRE");
        y.append('"');
        y.append(";}");
        
        try {
            String req = "SELECT * FROM utilisateur WHERE roles = ?";
            ListPartenaire = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(req);
           
            ps.setString(1, y.toString());            
            ResultSet resultat = ps.executeQuery();
            
          

            while (resultat.next()) {
                
               
                partenaire = new User();
                partenaire.setId(resultat.getInt("id"));
                partenaire.setNomSociete(resultat.getString("nomsociete"));
                partenaire.setActivite(resultat.getString("activite"));
                partenaire.setAdresse(resultat.getString("adresse"));
                ListPartenaire.add(partenaire);    
               

            }
        } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ListPartenaire;
    }
     
    
     
      public boolean isfound(User t) {

        try {
            String req = "SELECT 'username', 'email' FROM utilisateur WHERE username = ? or email = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getEmail());

            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                System.out.println("trouvée!!");
                return true;
            }
        } catch (SQLException ex) {
           
            System.out.println("erreur isfound");
            ex.printStackTrace();
        }

        return false;
    }

   
    public ResultSet List() throws SQLException {
       
        return List();
        
        
    }
    
    
}