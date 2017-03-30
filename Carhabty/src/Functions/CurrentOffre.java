/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Entities.Offre;

/**
 *
 * @author GARCII
 */
public class CurrentOffre {
    
    
     public static Offre Currento;

    public static Offre getCurrentOffre() {
        return Currento;
    }

    public static void setCurrentOffre(Offre Currento) {
        CurrentOffre.Currento = Currento;
    }
    
    
    
    
}
