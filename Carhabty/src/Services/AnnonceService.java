/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Date;
import DataBase.DataSource;
import DataBase.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Annonce;
import Entities.User;
import Entities.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maissen
 */
public class AnnonceService implements Interfaces.IAnnonce{
    private Connection conn;
     private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
    Date date1 = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

    public AnnonceService() {
        this.conn = DataSource.getInstance().getConnection();
    }
    @Override
    public void addAnnonce(Annonce A) {
        
        try {
              
              String req =" INSERT INTO `annonce`( `title`, `annee_de_produit`, `region`, `ville`, `paye`, `prix`, `category`,`idUser`, `descreption`,annee_pub,image_name) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                  
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, A.getTitle());
        pst.setDate(2, A.getAnneeDeProduit());
        pst.setDate(10,sqlDate);
        pst.setString(3, A.getRegion());
        pst.setString(4, A.getVille());
        pst.setString(5, A.getPaye());
        pst.setFloat(6, A.getPrix());
        pst.setString(7, A.getCategory());
        pst.setObject(8,Session.getActualUser().getId());
        pst.setString(9, A.getDescreption());
        pst.setString(11, A.getImageName());
        pst.executeUpdate();
                System.out.println("insert done dude ");
          
      } catch (SQLException e) {
          Logger.getLogger(Carhabty.Carhabty.class.getName()).log(Level.SEVERE,null,e);
      }
    
      
    }
    @Override
    public void UpdateAnnonce(Annonce V) {
                try {
                System.out.println(V);
            
        String req ="  UPDATE `annonce` SET `title`=?,`annee_pub`=?,`region`=?,`ville`=?,`paye`=?,`prix`=?,`category`=?,`descreption`=? WHERE id=?";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, V.getTitle());
        pst.setDate(2, V.getAnneePub());
        pst.setString(3, V.getRegion());
        pst.setString(4, V.getVille());
        pst.setString(5, V.getPaye());
        pst.setFloat(6, V.getPrix());
        pst.setString(7, V.getCategory());
        pst.setString(8, V.getDescreption());
        pst.setInt(9, V.getId());
        pst.executeUpdate();
                System.out.println("update done dude ");
          
      } catch (SQLException e) {
                System.out.println(e);
      }
        
        
        
        
     
    }
    @Override
    public void DeleteAnnonce(int id) {
    try {
            
            String req = "DELETE FROM `ANNONCE` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Annonce supprimé");

        } catch (SQLException ex) {System.out.println(ex);}
    
    }
    @Override
    public ObservableList<Annonce> ShowAllAnnonces() {
      ObservableList<Annonce> Annonces=FXCollections.observableArrayList();;
            try {
                String req ="SELECT * FROM annonce";
                
                Statement pst =conn.createStatement();
                ResultSet result= pst.executeQuery(req);
                while(result.next()){
                    Annonce A= new Annonce();
                    A.setId(result.getInt(1));
                    A.setTitle(result.getString(2));
                    A.setAnneeDeProduit(result.getDate(3));
                    A.setAnneePub(result.getDate(4));
                    A.setModel(result.getString(5));
                    A.setMarque(result.getString(6));
                    A.setRegion(result.getString(7));
                    A.setVille(result.getString(8));
                    A.setPaye(result.getString(9));
                    A.setPrix(result.getFloat(10));
                    A.setCategory(result.getString(11));
                    User u = new User();
                    u.setId(result.getInt(14));
                    A.setUser(u);
                    Annonces.add(A);
                  
                }             
                System.out.println("search done dude");
      } catch (SQLException e) {
          Logger.getLogger(Carhabty.Carhabty.class.getName()).log(Level.SEVERE,null,e);
      }
             return Annonces;
    }
    @Override
   public Annonce showAnnonces(int id) {
        Annonce A= new Annonce();
      try {
            String req = "select * FROM `annonce` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet result=ps.executeQuery();
                    A.setId(result.getInt(1));
                    A.setTitle(result.getString(2));
                    A.setAnneeDeProduit(result.getDate(3));
                    A.setAnneePub(result.getDate(4));
                    A.setModel(result.getString(5));
                    A.setMarque(result.getString(6));
                    A.setRegion(result.getString(7));
                    A.setVille(result.getString(8));
                    A.setPaye(result.getString(9));
                    A.setPrix(result.getFloat(10));
                    A.setCategory(result.getString(11));
                    User u = new User();
                    u.setId(result.getInt(14));
                    A.setUser(u);
        } catch (SQLException ex) {System.out.println(ex);}
           return A;
    }
   public ArrayList<Annonce> findAllOrderedBydate(){
    ArrayList<Annonce> Annonces=new ArrayList<Annonce>();
            try {
                String req = "SELECT * FROM annonce ORDER BY AnneePub DESC ";
                Statement pst =conn.createStatement();
                ResultSet result= pst.executeQuery(req);
                while(result.next()){
                    Annonce A= new Annonce();
                    A.setId(result.getInt(1));
                    A.setTitle(result.getString(2));
                    A.setAnneeDeProduit(result.getDate(3));
                    A.setAnneePub(result.getDate(4));
                    A.setModel(result.getString(5));
                    A.setMarque(result.getString(6));
                    A.setRegion(result.getString(7));
                    A.setVille(result.getString(8));
                    A.setPaye(result.getString(9));
                    A.setPrix(result.getFloat(10));
                    A.setCategory(result.getString(11));
                    User u = new User();
                    u.setId(result.getInt(14));
                    A.setUser(u);
                    Annonces.add(A);
                  
                }             
                System.out.println("search done dude");
      } catch (SQLException e) {
          Logger.getLogger(Carhabty.Carhabty.class.getName()).log(Level.SEVERE,null,e);
      }
             return Annonces;
    
   } 
  
     public ObservableList<Annonce> rechercheAnnonces(String b) {
      ObservableList<Annonce> Annonces=FXCollections.observableArrayList();;
            try {
                String req ="SELECT * FROM annonce WHERE `title` Like ? ";
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM annonce WHERE `title` Like ? ");
                ps.setString(1, "%" + b + "%");
                ResultSet result= ps.executeQuery();
                while(result.next()){
                    Annonce A= new Annonce();
                    A.setId(result.getInt(1));
                    A.setTitle(result.getString(2));
                    A.setAnneeDeProduit(result.getDate(3));
                    A.setAnneePub(result.getDate(4));
                    A.setModel(result.getString(5));
                    A.setMarque(result.getString(6));
                    A.setRegion(result.getString(7));
                    A.setVille(result.getString(8));
                    A.setPaye(result.getString(9));
                    A.setPrix(result.getFloat(10));
                    A.setCategory(result.getString(11));
                    User u = new User();
                    u.setId(result.getInt(14));
                    A.setUser(u);
                    Annonces.add(A);
                  
                }             
                System.out.println("search done dude");
      } catch (SQLException e) {
                System.out.println(e);
      }
             return Annonces;
    }
   
   
   
   
   
   
   
   
   
   
    
    
    
    
    
    
    
    
    
    
}
