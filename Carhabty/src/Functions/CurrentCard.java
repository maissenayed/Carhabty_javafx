/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Entities.Card;

/**
 *
 * @author GARCII
 */
public class CurrentCard {
    
    
    public static Card CCard;

    public static Card getCCard() {
        return CCard;
    }

    public static void setCCard(Card CCard) {
        CurrentCard.CCard = CCard;
    }
    
    
    
    
    
    
}
