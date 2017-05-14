/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowData;





/**
 *
 * @author Azgard
 */



public class QuestionFlowData {
    
    private int idQuest;

    public QuestionFlowData(int idQuest) {
        this.idQuest = idQuest;
    }
    
    public void setIdQuest(int id){
        this.idQuest=id;
    }
    
    public int getIdQuest()
    {return idQuest;}
    
    
    @Override
    public String toString()
    {return Integer.toString(idQuest);}
    
}
