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
            
            String req = "INSERT into event (description,title,address,event_date,user_id) VALUES (?,?,?,?,?)";
           
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
                       rs.getString("description"),
                       rs.getString("title"),
                       rs.getString("address"),
               rs.getString("event_date"));
               
               
               lst.add(e); 
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, ex);
        }
      return lst ; 
    }
       public void Modifier(int id , String title,String description,String adress , String event_date)
     {
         try {
             
            String req = "UPDATE event SET   description= ? ,title=? , address=? ,event_date=? where id="+id ;
           
             st = conn.prepareStatement(req);
             
            st.setString(1, description);
            st.setString(2, title);
            st.setString(3, adress);
            st.setString(4, event_date);
            
            
            
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
       
       

    
      
    
}
