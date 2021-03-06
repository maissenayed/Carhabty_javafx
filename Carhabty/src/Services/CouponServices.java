/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import DataBase.Session;
import Entities.Coupon;
import Entities.Offre;
import Entities.User;
import Functions.CurrentOffre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GARCII
 */
public class CouponServices {

    private Connection conn;

    public CouponServices() {

        this.conn = DataSource.getInstance().getConnection();

    }

    public boolean add(Coupon t) {

        try {

            String req = "INSERT into coupon (idUser,idOffre,date,reference) VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(req);

            ps.setInt(1, Session.actualUser.getId());
            ps.setInt(2, CurrentOffre.Currento.getId());
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(4, t.getReference());

            User u = new User();
            u.setId(Session.actualUser.getId());

            Offre o = new Offre();
            o.setId(CurrentOffre.Currento.getId());

            t.setAcheteur(u);
            t.setOffreAchete(o);
            ps.executeUpdate();
            System.out.println("Insertion terminé");
            return true;

        } catch (SQLException ex) {
            System.out.println("Problème d'insertion");
            ex.printStackTrace();
            return false;
        }

    }

    public int NombreCoupon() {
        int nb = 0;
        String req = "SELECT COUNT(*) FROM coupon";
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

    public Coupon getLastCoupon() {

        Coupon c = new Coupon();

        String req = "SELECT * FROM coupon ORDER BY id DESC LIMIT 1 ";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                c.setId(resultat.getInt("id"));

                c.setReference(resultat.getString("reference"));

                Offre o = new Offre();
                User u = new User();
                u.setId(resultat.getInt("idUser"));
                o.setId(resultat.getInt("idOffre"));

                c.setAcheteur(u);
                c.setOffreAchete(o);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;

    }

    public ResultSet MyListCoupon() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM coupon WHERE idUser = ? ");
        ps.setInt(1, Session.getActualUser().getId());
        return ps.executeQuery();

    }

    public Map<Integer, Integer> StatistiqueVente() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS NbCoupon, MONTH(coupon.date) AS Mois "
                + "FROM `coupon` INNER JOIN offre ON offre.id=coupon.idOffre WHERE"
                + " offre.idUser=" + Session.actualUser.getId() + " GROUP BY MONTH(coupon.date)");

        ResultSet result = ps.executeQuery();

        Map<Integer, Integer> map = new HashMap<>();

        while (result.next()) {

            map.put(result.getInt("Mois"), result.getInt("NbCoupon"));

        }

        return map;

    }

    public int NumberOfBuyer(int id) {

        int nb = 0;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM coupon WHERE idOffre = ? ");
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                nb = resultat.getInt(1);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nb;
    }

    public List<Coupon> FindALL() throws SQLException {

        List<Coupon> allcoupn = new ArrayList<>();
        Coupon c = null;
        User u = null;
        Offre o = null;

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM coupon WHERE idUser = ? ");
            ps.setInt(1, Session.getActualUser().getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                u = new User();
                o = new Offre();
                c = new Coupon();
                u.setId(resultSet.getInt("idUser"));
                o.setId(resultSet.getInt("idOffre"));
                c.setId(resultSet.getInt("id"));
                
                c.setDatee(((Date)(resultSet.getDate("date"))).toLocalDate());
                
                c.setReference(resultSet.getString("reference"));
                c.setAcheteur(u);
                c.setOffreAchete(o);
                allcoupn.add(c);
             
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    return allcoupn;}

}
