/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DataSource;
import Entities.Question;
import Interfaces.IServiceQuiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azgard
 */
public class QuestionService implements IServiceQuiz<Question> {

    private Connection conn;

    public QuestionService() {
        conn = DataSource.getInstance().getConnection();
    }

    @Override
    public int add(Question t) {

        int last_inserted_id = 0;
        String req = "INSERT INTO question (questionContent,questionCategory,media_id,explanation,enable) "
                + "VALUES (?,?,?,?,?)";

        Integer idmed;

        try {
            PreparedStatement ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getQuestionContent());
            ps.setString(2, t.getQuestionCategory());
            System.out.println("here");

            if (t.getMedia() != null) {
                ps.setInt(3, t.getMedia().getId());
            } else {
                ps.setNull(3, java.sql.Types.NUMERIC);
            }

            ps.setString(4, t.getExplanation());
            ps.setBoolean(5, true);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("problem");
            ex.printStackTrace();
        }
        return last_inserted_id;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Question t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String req = "UPDATE question SET questionContent = ?, questionCategory = ?, media_id = ?, explanation = ?, enable = ? where id = "+t.getId();
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, t.getQuestionContent());
            ps.setString(2, t.getQuestionCategory());
            if (t.getMedia() != null) {
                ps.setInt(3, t.getMedia().getId());
            } else {
                ps.setNull(3, java.sql.Types.NUMERIC);
            }
            ps.setString(4, t.getExplanation());
            ps.setBoolean(5, t.isEnable());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Question t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String req = "DELETE FROM question WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("problem..").log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Question> findALL() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String req = "SELECT * FROM question WHERE enable = 1";
        List<Question> ListQuestion = new ArrayList<Question>();
        // Question q = new Question();

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Question q = new Question();
                q.setId(resultat.getInt("id"));
                q.setQuestionContent(resultat.getString("questionContent"));
                q.setQuestionCategory(resultat.getString("questionCategory"));
                q.setMedia(new MediaService().findById(resultat.getInt("media_id")));
                q.setExplanation(resultat.getString("explanation"));
                q.setEnable(resultat.getBoolean("enable"));
                ListQuestion.add(q);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return ListQuestion;
    }

    @Override
    public Question findById(int id) {

        Question q = new Question();
        String req = "SELECT * FROM question WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                q.setId(resultat.getInt("id"));
                q.setQuestionContent(resultat.getString("questionContent"));
                q.setQuestionCategory(resultat.getString("questionCategory"));
                q.setMedia(new MediaService().findById(resultat.getInt("media_id")));
                q.setExplanation(resultat.getString("explanation"));
                q.setEnable(resultat.getBoolean("enable"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return q;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
