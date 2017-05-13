/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import Entities.Voiture;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maissen
 */
public interface IVoiture {
     void addVoiture(Voiture V);
     void UpdateVoiture(Voiture V);
     void DeleteVoiture(int id);
     ObservableList<Voiture> ShowAllVoiture();
     Voiture showVoiture(int id);
    
}
