/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DataBase.DataSource;
import DataBase.Session;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class EventServices {
    
     private Connection conn;
     PreparedStatement st ; 
  
    
    public EventServices() {
       this.conn = DataSource.getInstance().getConnection();
       
    }
     public void AjouterEvent(Event e)
    {
         try {
            
            String req = "INSERT into event (title,description,address,event_date,user_id) VALUES (?,?,?,?,?)";
           
            st = conn.prepareStatement(req);
            
            st.setString(1, e.getDescription()); 
            st.setString(2, e.getTitle()); 
            st.setString(3, e.getAdresse());
            st.setString(4, e.getEventDate()); 
            st.setInt(5, Session.actualUser.getId());
            
            st.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ObservableList<Event> RecupererEvent()
    {
       
        ObservableList<Event> lst = FXCollections.observableArrayList();
        try {
             
            String req = "SELECT * FROM `event`" ;
            
             st = conn.prepareStatement(req);
             ResultSet rs = st.executeQuery();
            while(rs.next())
            {
               Event e = new Event(rs.getInt("id"),
                       rs.getString("title"),
                       rs.getString("description"),
                       rs.getString("address"),
               rs.getString("event_date"));
               
               
               lst.add(e); 
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
      return lst ; 
    }
      
      
       public void Modifier(Event e)
     {
         try {
             
            String req = "UPDATE  event SET    title = ? , description = ? , address = ? ,event_date = ? where id= ?";
           
             st = conn.prepareStatement(req);
             
            st.setString(2, e.getDescription());
            st.setString(1, e.getTitle());
            st.setString(3, e.getAdresse());
            st.setString(4, e.getEventDate());
            st.setInt(5, e.getId());
            
            
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       public void Supprimer(int id)
     {
         
     try {
            
            String req ="DELETE FROM `event` WHERE id="+id;
           
            st = conn.prepareStatement(req);
            
             
            st.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
       public int getUserId(int id)
       {
           try {
            
            String req ="SELECT * FROM `event` WHERE id="+id;
           
            st = conn.prepareStatement(req);
            
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                return rs.getInt("user_id");
                
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           return 0 ;
       }
       
       
       
       public List <Event> SearchEvent (String entry) throws SQLException {
       
        List <Event> liste = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM event WHERE title "
                + "LIKE ? ");
        ps.setString(1, "%" + entry + "%");
     
       // ps.setInt(4,Session.getActualUser().getId());
        ResultSet set = ps.executeQuery();
        
        while (set.next())
        {
           
            Event  e = new Event(set.getString("title"),set.getString("description")
                    ,set.getString("address"),set.getString("event_date"));
            e.setId(set.getInt("id"));
            liste.add(e);
        }
        return liste;
    }

    
      
    
}
