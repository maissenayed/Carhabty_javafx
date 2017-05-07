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


/**
 * Created by GARCII on 3/12/2017.
 */
public class OffreServices implements IService<Offre> {

    private Connection conn;

    public OffreServices() {

        this.conn = DataSource.getInstance().getConnection();

    }

    @Override
    public boolean add(Offre t) {

        try {

            String req = "INSERT into offre (nom_offre,description_offre,prix,taux_reduction,image_name,date_expiration_offre,date,idUser) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(req);

            ps.setString(1, t.getNomOffre());
            ps.setString(2, t.getDescriptionOffre());
            ps.setFloat(3, t.getPrix());
            ps.setInt(4, t.getReduction());
            ps.setString(5, t.getImage());
            ps.setObject(6, t.getDateExp());
            ps.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            ps.setInt(8, Session.actualUser.getId());

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
    public boolean update(Offre t) {

        String req = "UPDATE offre SET nom_offre = ? ,description_offre = ? ,prix = ? ,taux_reduction = ? ,date_expiration_offre = ? WHERE id = ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getNomOffre());
            ps.setString(2, t.getDescriptionOffre());
            ps.setFloat(3, t.getPrix());
            ps.setInt(4, t.getReduction());
            ps.setObject(5, t.getDateExp());
            ps.setInt(6, t.getId());

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
    public boolean remove(Offre t) {
        try {
            String req = "DELETE FROM offre WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("offre supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Problème de suppression");
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Offre> findALL() {

        ArrayList<Offre> offres = new ArrayList<>();
        Offre o;
        User u;

        try {
            String req = "SELECT * FROM offre WHERE date_expiration_offre > CURRENT_DATE()";

            Statement stm = conn.createStatement();
            ResultSet ps = stm.executeQuery(req);

            while (ps.next()) {
                u = new User();
                o = new Offre();
                o.setId(ps.getInt(1));
                o.setNomOffre(ps.getString(2));
                o.setDescriptionOffre(ps.getString(3));
                o.setPrix(ps.getInt(4));
                o.setReduction(ps.getInt(5));
                o.setDateExp(ps.getDate(7));
                o.setImage(ps.getString("image_name"));
                
                u.setId(ps.getInt("idUser"));
                
                o.setUser(u);
                offres.add(o);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;

    }

    
    public List<Offre> findALLOld() {

        ArrayList<Offre> offres = new ArrayList<>();
        Offre o;
        User u;

        try {
            String req = "SELECT * FROM offre WHERE date_expiration_offre < CURRENT_DATE()";

            Statement stm = conn.createStatement();
            ResultSet ps = stm.executeQuery(req);

            while (ps.next()) {
                u = new User();
                o = new Offre();
                o.setId(ps.getInt(1));
                o.setNomOffre(ps.getString(2));
                o.setDescriptionOffre(ps.getString(3));
                o.setPrix(ps.getInt(4));
                o.setReduction(ps.getInt(5));
                o.setDateExp(ps.getDate(7));
                o.setImage(ps.getString("image_name"));
                
                u.setId(ps.getInt("idUser"));
                
                o.setUser(u);
                offres.add(o);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;

    }

    
    
    
    
    @Override
    public Offre findById(int id) {

        Offre o = null;
        User u;
        try {

            String req = "SELECT * FROM `offre` WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                u = new User();
                o = new Offre();

                o.setNomOffre(resultat.getString("nom_offre"));
                o.setDescriptionOffre(resultat.getString("description_offre"));
                o.setPrix(Float.parseFloat(resultat.getString("prix")));
                o.setReduction(Integer.parseInt(resultat.getString("taux_reduction")));
                o.setImage(resultat.getString("image_name"));
                o.setDateExp(java.sql.Date.valueOf(resultat.getString("date_expiration_offre")));
                u.setId(resultat.getInt("idUser"));               
                o.setUser(u);
               
             

            }

        } catch (SQLException ex) {
            System.out.println("Problème de retrouver l'objet offre");
            ex.printStackTrace();

        }
        return o;

    }

    
      public boolean updatePhoto(Offre t)  {
        try {
         
            String req = "UPDATE offre SET image_name = ? WHERE id = ? ";
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
    
    
   
    public ResultSet List() throws SQLException {
        
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM offre WHERE idUser = ? ");
         ps.setInt(1, Session.getActualUser().getId());
         return ps.executeQuery();
        
    }

    
    
                
    
    
     public List <Offre> SearchOffre (String entry) throws SQLException {
       
        List <Offre> liste = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM offre WHERE (nom_offre "
                + "LIKE ? OR date_expiration_offre LIKE ? OR prix LIKE ? )AND idUser= ?");
        ps.setString(1, "%" + entry + "%");
        ps.setString(2, "%" + entry + "%");
        ps.setString(3,"%" + entry + "%");
        ps.setInt(4,Session.getActualUser().getId());
        ResultSet set = ps.executeQuery();
        
        while (set.next())
        {
           
            Offre o = new Offre(set.getInt("id"),set.getString("nom_offre"),set.getString("description_offre")
                    ,set.getFloat("prix"),set.getInt("taux_reduction"),set.getDate("date_expiration_offre"));
            o.setId(set.getInt("id"));
            liste.add(o);
        }
        return liste;
    }

  
    
     
     public int NombreOffre() {
        int nb = 0;
        String req = "SELECT COUNT(*) FROM offre";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                nb = resultat.getInt(1);

            }

        } catch (SQLException ex) {
            System.out.println("Problème de comptage");
            ex.printStackTrace();

        }

        return nb;

    }
     
     
     
     
     
     
     
    

}
