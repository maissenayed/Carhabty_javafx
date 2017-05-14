/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import BackBone.Database.Annotation.Interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Azgard
 */



public class Reponse {
    
    private int id;
    private String reponseContent;
    private boolean ok;
    private Boolean typeReponse;
    private int quizNumber;
    private Question question;
    private User user;

    
    
   private StringProperty reponseContentProperty;
    
    
    public Reponse() {
      
     reponseContentProperty = new SimpleStringProperty();
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getReponseContent() {
        return reponseContent;
    }

    public void setReponseContent(String reponseContent) {
        this.reponseContent = reponseContent;
        this.reponseContentProperty.setValue(reponseContent);
    }

    public StringProperty getContentProperty()
    {return this.reponseContentProperty;}
    
    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Boolean getTypeReponse() {
        return typeReponse;
    }

    public void setTypeReponse(Boolean typeReponse) {
        this.typeReponse = typeReponse;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString()
    {
        return id+" "+reponseContent+"\n";
    }
    
    
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Reponse)
        {
            if(((Reponse)o).getId()==this.getId())
            return true;
        }
    return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        return hash;
    }
    
    
    
    
}
