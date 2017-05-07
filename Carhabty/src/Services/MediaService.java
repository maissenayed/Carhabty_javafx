/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Media;
import Interfaces.IServiceQuiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azgard
 */
public class MediaService implements IServiceQuiz<Media> {

    private Connection conn;

    public MediaService() {
        conn = DataSource.getInstance().getConnection();
    }

    @Override
    public int add(Media t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String req = "INSERT INTO media (typeMedia , description , image_name ) "
                + "VALUES (?,?,?)";
        int last_inserted_id=0;
        try {
            PreparedStatement ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getTypeMedia());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getImageName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return last_inserted_id ;
    }

    @Override
    public boolean update(Media t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String req = "UPDATE media SET typeMedia = ? , description = ? , image_name = ? where id = "+t.getId();
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getTypeMedia());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getImageName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Media t) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String req = "DELETE FROM media WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MediaService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Media> findALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Media findById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Media m = new Media();
        String req = "SELECT * FROM media WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                m.setId(resultat.getInt("id"));
                m.setDescription(resultat.getString("description"));
                m.setTypeMedia(resultat.getString("typeMedia"));
                m.setImageName(resultat.getString("image_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MediaService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return m;
    }

}
