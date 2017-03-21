package Services;

import DataBase.DataSource;
import DataBase.Session;
import Entities.Offre;
import Entities.User;
import Interfaces.IService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GARCII on 3/12/2017.
 */
public class OffreServices implements IService<Offre>{

   private Connection conn;
    
   public OffreServices(){
   
         this.conn = DataSource.getInstance().getConnection();
       
   }

    @Override
    public boolean add(Offre t) {
      
         try {
            
            String req = "INSERT into offre (nom_offre,description_offre,prix,taux_reduction,date_expiration_offre,date,idUser) VALUES (?,?,?,?,?,?,?)";
           
              PreparedStatement ps = conn.prepareStatement(req);
            
            ps.setString(1, t.getNomOffre()); 
            ps.setString(2, t.getDescriptionOffre()); 
            ps.setFloat(3, t.getPrix());
            ps.setInt(4, t.getReduction()); 
            ps.setObject(5, t.getDateExp());
            ps.setDate(6,java.sql.Date.valueOf(LocalDate.now()));
            ps.setObject(7, t.getUser().getId());
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreServices.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
        
        
        
    }

    @Override
    public boolean update(Offre t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Offre t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Offre> findALL() {
       
         ArrayList<Offre> offres = new ArrayList<>();
        try {
            String req = "select * from offre";

            Statement stm = conn.createStatement();
            ResultSet ps = stm.executeQuery(req);


            while (ps.next()) {
                Offre o = new Offre();
                o.setId(ps.getInt(1));
                o.setNomOffre(ps.getString(2));
                o.setDescriptionOffre(ps.getString(3));
                o.setPrix(ps.getInt(4));
                o.setReduction(ps.getInt(5));
            }
         } catch (SQLException ex) {
            Logger.getLogger(OffreServices.class.getName()).log(Level.SEVERE, null, ex); }
           return offres;
        
        
    }

    @Override
    public Offre findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   

   

}