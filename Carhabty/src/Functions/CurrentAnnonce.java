/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Entities.Annonce;

/**
 *
 * @author Maissen
 */
public class CurrentAnnonce {
     public static Annonce Currento;

    public static Annonce getCurrentVoiture() {
        return Currento;
    }

    public static void setCurrentAnnonce(Annonce Currento) {
        CurrentAnnonce.Currento = Currento;
    }
    
}
