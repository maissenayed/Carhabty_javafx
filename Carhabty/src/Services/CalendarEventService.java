/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import DataBase.DataSource;
import DataBase.Session;
import Entities.CalendarEvent;
import Entities.Voiture;
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
public class CalendarEventService implements Interfaces.iCalander{
      private Connection conn;

    public CalendarEventService() {
        this.conn = DataSource.getInstance().getConnection();
    }

    @Override
    public void addCalander(CalendarEvent c) {
       try {   
                 String req ="INSERT INTO `calendar_event`(`start_date`, `title`, `end_date`, `all_day`, `idVoiture`) VALUES (?,?,?,?,?)";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1, c.getStartDate());
        pst.setString(2, c.getTitle());
        pst.setString(3, c.getEndDate());
        pst.setString(4, c.getAllDay());
        pst.setInt(5, c.getVoiture().getId());
        pst.executeUpdate();
        System.out.println("insert done dude ");      
      } catch (SQLException e) {
          Logger.getLogger(Carhabty.Carhabty.class.getName()).log(Level.SEVERE,null,e);
      }
    }

    @Override
    public void UpdateTitle(CalendarEvent c) {
             try {
                System.out.println(c);
            
        String req =" UPDATE `calendar_event` SET `start_date`=?,`title`=?,`end_date`=?,`all_day`=? WHERE id=?";
        PreparedStatement pst =conn.prepareStatement(req);
        pst.setString(1,c.getStartDate() );
        pst.setString(2,c.getTitle() );
        pst.setString(3,c.getEndDate() );
        pst.setString(4,c.getAllDay());
        pst.setInt(5, c.getId());
        pst.executeUpdate();
                System.out.println("update done dude ");
      } catch (SQLException e) {
                System.out.println(e);
      }
      
    
    }
    

    @Override
    public void UpdateTime(CalendarEvent c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void DeleteCalander(int id) {       
      
        
        try {
        
            String req = "DELETE FROM `calendar_event` WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("to do list supprimé");

        } catch (SQLException ex) {System.out.println(ex);}
    
    }

    @Override
    public  ObservableList<CalendarEvent> ShowAllCalanders(int id) {
        ObservableList<CalendarEvent> Calanders = FXCollections.observableArrayList();
      
            try {
            
                   PreparedStatement pst =conn.prepareStatement("SELECT * FROM `calendar_event` WHERE idVoiture= ?");
                   pst.setInt(1, id);
                
                ResultSet result= pst.executeQuery();
                while(result.next()){
                    CalendarEvent A= new CalendarEvent();
                   
                    A.setId(result.getInt(1));
                    A.setStartDate(result.getString(2));
                    A.setTitle(result.getString(3));
                    A.setEndDate(result.getString(4));
                    A.setAllDay(result.getString(5));
                    Voiture V = new Voiture();
                    V.setId(id);
                    A.setVoiture(V);
                    Calanders.add(A);
                  
                }             
                System.out.println("search done dude");
      } catch (SQLException e) {
                System.out.println("dude here it is"+e);
      }
             return Calanders;
    }
      public int countAnnonce(){
          int count=0;
            try {
                String req = "select count(*) from calendar_event WHERE `start_date` BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY) ";
                Statement pst =conn.createStatement();
                ResultSet result= pst.executeQuery(req);
                 while (result.next()) {
               count=result.getInt(1);
 }
                
                System.out.println("search done dude");
      } catch (SQLException e) {
                System.out.println(e);
      }
            
            return count;
        
            
    }
    
    
}
