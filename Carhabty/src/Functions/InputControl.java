/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;



/**
 *
 * @author GARCII
 */
public class InputControl {
    
    
    
    
       
    
    
    public boolean InputControlPayment(String card,String cvc){
    
            if(card.trim().length()  == 18  && !card.isEmpty() 
              && cvc.trim().length() == 3 && !cvc.isEmpty() 
              && cvc.matches("[0-9]+")    && card.matches("[0-9]+")
                   
                    ){
           
                return true;
                
            }
            
    return false;
    
    }
    
    
    
    
    
    
    
}
