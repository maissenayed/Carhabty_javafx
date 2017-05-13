/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Annonce;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Maissen
 */
public interface IAnnonce {
     void addAnnonce(Annonce A);
     void UpdateAnnonce(Annonce A);
     void DeleteAnnonce(int id);
     ObservableList<Annonce> ShowAllAnnonces();
     Annonce showAnnonces(int id);

}
