/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Event {

    int id;
    String description;
    String title;
    String access;
    String adresse;
    String eventDate;
    String CreatedDate;
    String UpdatedDate;
    String photo;
    User user;

    public Event() {
      
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Titre :" + title + ", Description :" + description + ", adresse :" + adresse + ", Date de l'evenement :" + eventDate;
    }

    public Event(String description, String title, String access, String adresse, String eventDate, String CreatedDate, String UpdatedDate, String photo) {
       
        this.description = description;
        this.title = title;
        this.access = access;
        this.adresse = adresse;
        this.eventDate = eventDate;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
        this.photo = photo;
    }

    public Event(String description, String title, String adresse, String eventDate) {
        this.description = description;
        this.title = title;
        this.adresse = adresse;
        this.eventDate = eventDate;
    }

    public Event(int id, String description, String title, String adresse, String eventDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adresse = adresse;
        this.eventDate = eventDate;
    }

}
