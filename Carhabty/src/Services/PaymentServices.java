/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Account;
import Entities.Card;
import Entities.History;
import Functions.CurrentCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author GARCII
 */
public class PaymentServices {

    private Connection conn;

    public PaymentServices() {

        this.conn = DataSource.getInstance().getConnection();

    }

    public boolean VerifyCredentialsPayment(String card, String cvc, String dateExp) {

        String req = "SELECT * FROM card WHERE num_card = ? AND cvc = ? AND expdate = ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, card);
            ps.setString(2, cvc);
            ps.setString(3, dateExp);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                System.out.println("Credentials Verified successfully");

             //   System.out.println(resultat.getInt("id"));
                Card c = new Card();
                c.setId(resultat.getInt("id"));
                CurrentCard.setCCard(c);

                return true;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        System.out.println("Erreur wrong Credentials");
        return false;

    }

    public Card getCardPayment() {

        Card c = null;
        String req = "SELECT * FROM card WHERE id = ? ";

        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, CurrentCard.CCard.getId());

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                Account ac = new Account();
                ac.setId(resultat.getInt("account_id"));

                c = new Card();
                c.setIdAccount(ac);
                c.setId(resultat.getInt("id"));
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

       

        return c;

    }

    public boolean Withdraw(float price,int id) {

        
        
         float solde = 0;
        
        String req1 = "SELECT * FROM account WHERE id = ? ";
         try {

            PreparedStatement ps = conn.prepareStatement(req1);
            ps.setInt(1,id);
            ResultSet result = ps.executeQuery();
           
           while(result.next()){
           
            solde =  result.getFloat("solde");
           
           }
            
          
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        
        
        String req = "UPDATE account SET solde = ? WHERE id = ? ";

        
             solde = solde - price ;
           
        try {

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setFloat(1,solde);
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("withdraw success");
            return true;
            
            
            
        } catch (SQLException ex) {
             System.out.println("withdraw noooooooo success");
            ex.printStackTrace();

        }

        return false;

    }

    public List<History> CheckHistroy() {

        List<History> history = null;

        return history;

    }

}
