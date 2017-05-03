/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Astuce;
import DataBase.DataSource;
import DataBase.Session;
import Entities.User;
import Interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class AstuceServices implements IService<Astuce> {
    
        private static IService iServ = null;
    private Stage stage;
    private Scene scene;
    
    public static IService getInstance() {
        if (iServ == null) {
            iServ = new AstuceServices();
        }
        return iServ;
    }
   

    private Connection conn;

    public AstuceServices() {
        this.conn = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(Astuce s) {

        try {

            String req = "INSERT into astuce (theme,titre,description,date,image_name,user_id) VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(req);

            ps.setString(1, s.getTheme());
            ps.setString(2, s.getTitre());
            ps.setString(3, s.getDescription());
            ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(5, s.getImage_name());
            ps.setInt(6, Session.actualUser.getId());

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
    public List<Astuce> findALL() {

        ArrayList<Astuce> astuces = new ArrayList<>();

        User u;
        try {
            String req = "select * from astuce";

            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                u = new User();
                Astuce a = new Astuce();
                a.setId(result.getInt(1));
                a.setTheme(result.getString(2));
                a.setTitre(result.getString(3));
                a.setDescription(result.getString(4));

                astuces.add(a);
            }

        } catch (SQLException ex) {
        }

        return astuces;
    }
    
    
       
    public ResultSet List() throws SQLException {
        
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM astuce WHERE user_id = ? ");
         ps.setInt(1, Session.getActualUser().getId());
         return ps.executeQuery();
        
    }
   
    public ResultSet consulter() {

        ResultSet result = null;
        String requete = "select * from astuce ;";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            result = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(AstuceServices.class.getName()).log(Level.SEVERE, null, ex);

        }

        return result;

    }

   

   

    @Override
    public boolean update(Astuce t) {
        
       String req = "UPDATE astuce SET titre = ? ,theme = ? ,description = ?  WHERE id = ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getTheme());
            ps.setString(3, t.getDescription());
          
            ps.setInt(4, t.getId());

            ps.executeUpdate();
            System.out.println("Modification terminé");
            return true;

        } catch (SQLException ex) {
            System.out.println("Problème de Modification");
            ex.printStackTrace();
            return false;
        }

        
    }
    
         public List <Astuce> SearchAstuce (String entry) throws SQLException {
       
        List <Astuce> liste = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM astuce WHERE theme "
                + "LIKE ? OR titre LIKE ? OR description LIKE ?");
        ps.setString(1, "%" + entry + "%");
        ps.setString(2, "%" + entry + "%");
        ps.setString(3,"%" + entry + "%");
       // ps.setInt(4,Session.getActualUser().getId());
        ResultSet set = ps.executeQuery();
        
        while (set.next())
        {
           
            Astuce a = new Astuce (set.getInt("id"),set.getString("titre"),set.getString("theme"),set.getString("description"));
                 
            a.setId(set.getInt("id"));
            liste.add(a);
        }
        return liste;
    }


         
         
    @Override
    public boolean remove(Astuce t) {
        try {
            String req = "DELETE FROM astuce WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("astuce supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Problème de suppression");
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Astuce findById(int id) {
        
        Astuce a = null;
        User u;
        try {

            String req = "SELECT * FROM `astuce` WHERE id = ? ";

            
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                u = new User();
                a = new Astuce();
                a.setId(resultat.getInt("id"));
                a.setTitre(resultat.getString("titre"));
                a.setTheme(resultat.getString("theme"));
                a.setDescription(resultat.getString("description"));
                u.setId(resultat.getInt("user_id"));  
                a.setImage_name(resultat.getString("image_name"));
                a.setUser_id(u);
               
             

            }

        } catch (SQLException ex) {
            System.out.println("Problème de retrouver l'objet astuce");
            ex.printStackTrace();

        }
        return a;
    }

  
      public boolean updatePhoto(Astuce t)  {
        try {
         
            String req = "UPDATE astuce SET image_name = ? WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(req);
           
            
            ps.setString(1, t.getImage_name());
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
    
    
    
    
    
    
    
    
    
    
}
