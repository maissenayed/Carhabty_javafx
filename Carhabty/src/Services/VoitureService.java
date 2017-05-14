/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import DataBase.DataSource;
import DataBase.Session;
import Entities.User;
import Entities.Voiture;
import Interfaces.IVoiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maissen
 */
public class VoitureService implements IVoiture{
     private Connection conn;

    public VoitureService() {
        this.conn = DataSource.getInstance().getConnection();
    }
 public boolean updatePhoto(Voiture t)  {
        try {
         
            String req = "UPDATE voiture SET photo = ? WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getImage());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("photo modifiée avec succées");
            return true;
        
        } catch (SQLException ex) {
                     System.out.println("photo n'est pas modifiée");
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public void addVoiture(Voiture V) {
          
            try {
                System.out.println(V);
        String req ="INSERT INTO `voiture`(`marque`,`annee`, `model`,`idUser`,image_name) VALUES (?,?,?,?,?)";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, V.getMarque());
        pst.setDate(2, V.getAnnee());
        pst.setString(3, V.getModel());
        pst.setInt(4, Session.getActualUser().getId());
        pst.setString(5, V.getImageName());
        pst.executeUpdate();
                System.out.println("insert done dude ");
          
      } catch (SQLException e) {
                System.out.println(e);
      }
    
    }

    @Override
    public void UpdateVoiture(Voiture V) {
               try {
                System.out.println(V);
            
        String req =" UPDATE `voiture` SET `marque`=?,`annee`=?,`model`=? WHERE id=?";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, V.getMarque());
        pst.setDate(2, V.getAnnee());
        pst.setString(3, V.getModel());
        pst.setInt(4, V.getId());
        pst.executeUpdate();
                System.out.println("update done dude ");
          
      } catch (SQLException e) {
                System.out.println(e);
      }
    }
     
    public void insertVoiture(Voiture V) {
               try {
                System.out.println(V);
            
        String req =" UPDATE `voiture` SET `marque`=?,`annee`=?,`model`=? WHERE id=?";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, V.getMarque());
        pst.setDate(2, V.getAnnee());
        pst.setString(3, V.getModel());
        pst.setInt(4, V.getId());
        pst.executeUpdate();
                System.out.println("update done dude ");
          
      } catch (SQLException e) {
                System.out.println(e);
      }
    }

    @Override
    public void DeleteVoiture(int id) {
        try {
            String req = "DELETE FROM `voiture` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Voiture supprimé");

        } catch (SQLException ex) {System.out.println(ex);}
               
        
    }

    @Override
    public ObservableList<Voiture> ShowAllVoiture() {
           ObservableList<Voiture> Voitures=FXCollections.observableArrayList();;
            try {
                String req ="SELECT * FROM `voiture` WHERE `idUser` = ? ";  
                PreparedStatement pst =conn.prepareStatement("SELECT * FROM `voiture` WHERE `idUser` = ? ");
                pst.setInt(1, Session.getActualUser().getId());
                
                ResultSet result= pst.executeQuery();
                
                while(result.next()){
                    Voiture A= new Voiture();                   
                    A.setId(result.getInt(1));
                    A.setMarque(result.getString(2));
                    A.setAnnee(result.getDate(3));
                    A.setModel(result.getString(4));
                    User u = new User();
                    u.setId(Session.getActualUser().getId());
                    A.setUser(u);
                    Voitures.add(A);
                    
                }             
                System.out.println("search done dude");
      } catch (SQLException e) {
                System.out.println("dude"+e);
      }
             return Voitures;
    
    }

    @Override
    public Voiture showVoiture(int id) {
         Voiture A= new Voiture();
      try {
            
            String req = "select * FROM `voiture` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet result=ps.executeQuery();
             
                
                    A.setId(result.getInt(1));
                    A.setMarque(result.getString(2));
                    A.setAnnee(result.getDate(3));
                    A.setModel(result.getString(4));
                    User u = new User();
                    u.setId(result.getInt(7));
                    A.setUser(u);
                    
           
        } catch (SQLException ex) {System.out.println(ex);}
         
           return A;
    }
    public Voiture showrecherche(String b) {
         Voiture A= new Voiture();
      try {
            
            String req = "select * FROM `voiture` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, "%" + b + "%");
     
          
            ResultSet result=ps.executeQuery();
             
                
                    A.setId(result.getInt(1));
                    A.setMarque(result.getString(2));
                    A.setAnnee(result.getDate(3));
                    A.setModel(result.getString(4));
                    User u = new User();
                    u.setId(result.getInt(7));
                    A.setUser(u);
                    
           
        } catch (SQLException ex) {System.out.println(ex);}
         
           return A;
    }
    
    
    
    
    
    
}
