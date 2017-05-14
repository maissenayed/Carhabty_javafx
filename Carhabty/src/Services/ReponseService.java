/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Reponse;
import Entities.User;
import Interfaces.IServiceQuiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azgard
 */
public class ReponseService implements IServiceQuiz<Reponse> {

    private Connection conn;

    public ReponseService() {
        conn =DataSource.getInstance().getConnection();
    }

    @Override
    public int add(Reponse t) {
        System.out.println("add test");
        int last_inserted_id = 0;
        int test;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "INSERT INTO reponse (reponseContent,ok,question_id,user_id,typeReponse,quizNumber) "
                + "VALUES (?,?,?,?,?,?)";
        try {

            PreparedStatement ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getReponseContent());
            ps.setBoolean(2, t.isOk());
            ps.setInt(3, t.getQuestion().getId());
            if (t.getUser() == null) {
                ps.setNull(4, java.sql.Types.NUMERIC);
            } else {
                ps.setInt(4, t.getUser().getId());
            }

            ps.setBoolean(5, t.getTypeReponse());
            ps.setInt(6, t.getQuizNumber());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            System.out.println("Insertion terminé");
        } catch (SQLException ex) {
            System.out.println("Problème d'insertion");
            ex.printStackTrace();

        }
        return last_inserted_id;
    }

    @Override
    public boolean update(Reponse t) {

        String req = "UPDATE reponse SET reponseContent = ? , ok = ? , question_id = ? ,user_id = ? ,typeReponse = ? , quizNumber = ? where id = "+t.getId();

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getReponseContent());
            ps.setBoolean(2, t.isOk());

            ps.setInt(3, t.getQuestion().getId());
            if (t.getUser() == null) {
                ps.setNull(4, java.sql.Types.NUMERIC);
            } else {
                ps.setInt(4, t.getUser().getId());
            }
            ps.setBoolean(5, t.getTypeReponse());
            ps.setInt(6, t.getQuizNumber());
            ps.executeUpdate();
            System.out.println("modification terminé");
        } catch (SQLException ex) {
            System.out.println("Problème de modification");
            ex.printStackTrace();
            return false;
        }
        return true;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Reponse t) {

        try {
            String req = "DELETE FROM reponse WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Reponse supprimé");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger("problem..").log(Level.SEVERE, null, ex);

            ex.printStackTrace();
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> findALL() {

        List<Reponse> ListReponse = new ArrayList<>();

        Reponse u;
        try {

            String req = " SELECT * FROM reponse";
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                u = new Reponse();

                u.setId(resultat.getInt("id"));
                u.setReponseContent(resultat.getString("reponseContent"));
                u.setOk(resultat.getBoolean("ok"));

                ListReponse.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Liste ne peut pas etre Affichée");
            ex.printStackTrace();
        }
        return ListReponse;
    }

    @Override
    public Reponse findById(int id) {

        Reponse r = new Reponse();
        String req = "SELECT * FROM reponse WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                r.setId(id);
                r.setReponseContent(resultat.getString("reponseContent"));
                r.setOk(resultat.getBoolean("ok"));
                r.setQuestion(new QuestionService().findById(resultat.getInt("question_id")));
                r.setUser(new UserServices().findById(resultat.getInt("user_id")));
                r.setTypeReponse(resultat.getBoolean("typeReponse"));
                r.setQuizNumber(resultat.getInt("quizNumber"));

            }

        } catch (SQLException ex) {
            Logger.getLogger("problém").log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return r;
    }

    public List<Reponse> findByQuestion(int idQuest, String s) {
        List<Reponse> ListReponse = new ArrayList<>();
        Boolean type = false;
        if (s.equals("ADMIN")) {
            type = true;
        }

        Reponse u;
        try {

            String req = " SELECT * FROM reponse WHERE question_id = ? AND typeReponse = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, idQuest);
            ps.setBoolean(2, type);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                u = new Reponse();

                u.setId(resultat.getInt("id"));
                u.setReponseContent(resultat.getString("reponseContent"));
                u.setOk(resultat.getBoolean("ok"));

                ListReponse.add(u);

            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Liste ne peut pas etre Affichée");
            ex.printStackTrace();
        }

        return ListReponse;
    }

    public int findLastQuizNumber(User u) {
        String req = "SELECT MAX(quizNumber) AS maxQuiz FROM reponse WHERE user_id = ?";
        int max = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, u.getId());
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                max = resultat.getInt("maxQuiz");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);

        }

        return max;
    }

    public Map<Integer, Integer> getStatistique(User u) throws SQLException {
        Map<Integer, Integer> statis = new LinkedHashMap<Integer, Integer>();
        Map<Integer, Integer> rstatis = new LinkedHashMap<Integer, Integer>();

        String req = "SELECT COUNT(*) AS nbrCorrect, quizNumber FROM reponse WHERE user_id = ? AND ok=1 GROUP BY quizNumber ORDER BY quizNumber DESC LIMIT 10";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, u.getId());
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            statis.put(resultat.getInt("quizNumber"), resultat.getInt("nbrCorrect"));
        }
        return statis;
    }

    public Map<String, Integer> getCountReponseByTypeQuestion(User u) throws SQLException {
        Map<String, Integer> mp = new HashMap<String, Integer>();
        String req = "SELECT question.questionCategory as cat, COUNT(reponse.id) as repNumber FROM question join reponse ON question.id = reponse.question_id WHERE reponse.user_id = ? GROUP BY questionCategory";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, u.getId());
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            mp.put(resultat.getString("cat"), resultat.getInt("repNumber"));
        }

        return mp;
    }
    
    
       public Map<String, Integer> getCountCorrectReponseByTypeQuestion(User u) throws SQLException {
        Map<String, Integer> mp = new HashMap<String, Integer>();
        String req = "SELECT question.questionCategory as cat, COUNT(reponse.id) as repNumber FROM question join reponse ON question.id = reponse.question_id WHERE reponse.user_id = ? AND reponse.ok=1 GROUP BY questionCategory";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, u.getId());
        ResultSet resultat = ps.executeQuery();
        while (resultat.next()) {
            mp.put(resultat.getString("cat"), resultat.getInt("repNumber"));
        }

        return mp;
    }
       
       public Map<String,ArrayList<Integer>> getAllInfoReponse(User u) throws SQLException
       {
            Map<String,ArrayList<Integer>> mp=new HashMap();
            Map<String,Integer> all=getCountReponseByTypeQuestion(u);
            Map<String,Integer>correct=getCountCorrectReponseByTypeQuestion(u);
            for(Map.Entry<String,Integer> entry: all.entrySet())
            { ArrayList<Integer> ar=new ArrayList<Integer>();
              ar.add(entry.getValue());
              ar.add(0);
              mp.put(entry.getKey(), ar);        
                }
            
            for(Map.Entry<String,Integer> entry : correct.entrySet())
            {
                mp.get(entry.getKey()).set(1, entry.getValue());
            }
            
            
            return mp;
       }

}
