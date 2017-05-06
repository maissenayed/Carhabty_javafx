/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Astuce;
import DataBase.DataSource;
import DataBase.Session;
import Entities.AstuceRate;
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
public class AstuceRateServices implements IService<AstuceRate> {
    
        private static IService iServ = null;
    private Stage stage;
    private Scene scene;
    
    public static IService getInstance() {
        if (iServ == null) {
            iServ = new AstuceRateServices();
        }
        return iServ;
    }
   

    private Connection conn;

    public AstuceRateServices() {
        this.conn = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean add(AstuceRate s) {

        try {

            String req = "INSERT into rate (user,astuce,rate) VALUES (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(req);

            ps.setInt(1, s.getUser().getId());
            ps.setInt(2, s.getAstuce().getId());
            ps.setFloat(3, s.getRate());
            

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
    public List<AstuceRate> findALL() {

        return null;
    }
    
    
    

   

   

    @Override
    public boolean update(AstuceRate t) {
        
       String req = "UPDATE rate SET rate = ?  WHERE astuce = ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setFloat(1, t.getRate());
            ps.setInt(2, t.getAstuce().getId());
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
    public boolean remove(AstuceRate t) {
        return false;

    }

    @Override
    public AstuceRate findById(int id) {
        
        return null;
    }
    public boolean exist(int id){
        boolean check = false;
        try {

            String req = "SELECT * FROM `rate` WHERE astuce = ? ";

            
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.out.println("Problème de retrouver l'objet astuce");
            ex.printStackTrace();

        }
        return check;
    }
    public float getAstuceRate(int id){
        float rating = 0;
        try {

            String req = "SELECT rate FROM `rate` WHERE astuce = ? ";

            
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                rating = resultat.getFloat("rate");
            }

        } catch (SQLException ex) {
            System.out.println("Problème de retrouver l'objet astuce");
            ex.printStackTrace();

        }
        return rating;
    }

  
    
    
    
    
    
    
    
    
    
    
    
}
