/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;
import Entities.Astuce;

/**
 *
 * @author Lenovo
 */
public class CurrentAstuce {
    
    
     public static Astuce Currenta;

    public static Astuce getCurrentAstuce() {
        return Currenta;
    }

    public static void setCurrentAstuce (Astuce Currenta) {
        CurrentAstuce.Currenta = Currenta;
    }
    
}
