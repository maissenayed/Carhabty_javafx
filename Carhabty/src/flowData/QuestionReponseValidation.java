/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowData;

import Entities.Question;
import Entities.Reponse;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Azgard
 */
public class QuestionReponseValidation {
    
    private Question q;
    private Object[] reps;
    private File file;

    public QuestionReponseValidation(Question q, Object[] reps, File file) {
        this.q = q;
        this.reps = reps;
        this.file=file;
    }

    public QuestionReponseValidation(Question q, Object[] reps) {
        this.q = q;
        this.reps = reps;
    }



   
    
    public boolean isValid()
    {
       boolean ok;
       boolean okRep=false;
       try
       {
       ok=!q.getQuestionContent().trim().isEmpty() && !q.getExplanation().trim().isEmpty() && !q.getQuestionCategory().trim().isEmpty();
          
       for(Object r : reps)
       {
           Reponse c = (Reponse)r;
          
           if(c.getReponseContent().trim().isEmpty())
           {ok=false;}
           okRep=(okRep||c.isOk());
       }
       }catch(Exception e)
       {   e.printStackTrace();
           return false;}
              
       return ok && okRep && (file!=null || !q.getMedia().getImageName().isEmpty());
    }
    
}
