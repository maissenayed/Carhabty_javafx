/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author Maissen
 */
public class CalendarEvent {
    
   
    protected int id;
   

    protected String startDate;
   
    protected String title;
    
    protected String endDate;


    protected String allDay;
  
    private Voiture Voiture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" + "id=" + id + ", startDate=" + startDate + ", title=" + title + ", endDate=" + endDate + ", allDay=" + allDay + ", Voiture=" + Voiture + '}';
    }
    

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public Voiture getVoiture() {
        return Voiture;
    }

    public void setVoiture(Voiture Voiture) {
        this.Voiture = Voiture;
    }

    public CalendarEvent() {
    }

    public CalendarEvent(int id, String startDate, String title, String endDate, String allDay, Voiture Voiture) {
        this.id = id;
        this.startDate = startDate;
        this.title = title;
        this.endDate = endDate;
        this.allDay = allDay;
        this.Voiture = Voiture;
    }
    
    
    
    
    
    
    
    
    
}
