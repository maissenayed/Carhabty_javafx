/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowData;

import DataBase.Session;
import Entities.Question;
import Entities.Reponse;
import Services.ReponseService;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Azgard
 */
public class QuizAdapter {

    private Question q;
    private Map<Reponse, Boolean> rep;

    public QuizAdapter(Question q) {
        this.q = q;
        rep = new LinkedHashMap<Reponse, Boolean>();
        ReponseService rs = new ReponseService();
        for (Reponse r : rs.findByQuestion(q.getId(), "ADMIN")) {
            rep.put(r, Boolean.FALSE);
        }
    }

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public Map<Reponse, Boolean> getRep() {
        return rep;
    }

    public void setRep(Map<Reponse, Boolean> rep) {
        this.rep = rep;
    }

    public void setChoice(int i) {
        int x = 0;
        for (Map.Entry<Reponse, Boolean> tmp : rep.entrySet()) {
            x++;
            if (i == x) {
                tmp.setValue(Boolean.TRUE);
            } else {
                tmp.setValue(Boolean.FALSE);
            }
        }
    }

    public boolean choiceIsSet() {

        boolean ok = false;
        Iterator it = rep.entrySet().iterator();

        while (!ok && it.hasNext()) {
            Entry<Reponse, Boolean> thisRep = (Entry) it.next();
            ok = thisRep.getValue();
        }

        return ok;
    }

    public boolean isCorrect() {

        Boolean ok = false;
        Iterator it = rep.entrySet().iterator();

        while (!ok && it.hasNext()) {
            Entry<Reponse, Boolean> thisRep = (Entry) it.next();
            if(thisRep.getKey().isOk()&&thisRep.getValue())
            ok = true;
        }

        return ok;
    }
    
    
    public boolean save(int quiznum)
    {
        Reponse r=new Reponse();
        r.setQuestion(q);
        r.setTypeReponse(Boolean.FALSE);
        r.setOk(isCorrect());
        r.setUser(Session.actualUser);
        ReponseService rs=new ReponseService();
        r.setQuizNumber(quiznum);
        rs.add(r);
        
        
        return true;
    }

}
