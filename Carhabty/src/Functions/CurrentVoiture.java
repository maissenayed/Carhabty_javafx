/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import Entities.Voiture;

/**
 *
 * @author Maissen
 */
public class CurrentVoiture {
      public static Voiture Currento;

    public static Voiture getCurrentVoiture() {
        return Currento;
    }

    public static void setCurrentVoiture(Voiture Currento) {
        CurrentVoiture.Currento = Currento;
    }
    
    
}
