/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import Entities.CalendarEvent;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Maissen
 */
public interface iCalander {
    
    void addCalander(CalendarEvent c);
    void UpdateTitle(CalendarEvent c);
    void UpdateTime(CalendarEvent c);
    void DeleteCalander(int id);
     ObservableList<CalendarEvent> ShowAllCalanders(int id);
     
    
    
    
    
    
    
}
