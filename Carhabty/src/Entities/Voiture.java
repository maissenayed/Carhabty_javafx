/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Maissen
 */
public class Voiture {
    private int id;   
    private String Marque;   
    private Date Annee;
    private String Model;
    private String imageName;
    private Date updatedAt;
    private User user;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getMarque() {
        return Marque;
    }

    public Date getAnnee() {
        return Annee;
    }

    public String getModel() {
        return Model;
    }

    public String getImageName() {
        return imageName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setAnnee(Date Annee) {
        this.Annee = Annee;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", Marque=" + Marque + ", Annee=" + Annee + ", Model=" + Model + ", imageName=" + imageName + ", updatedAt=" + updatedAt + ", user=" + user + '}';
    }

    public Voiture(int id, String Marque, Date Annee, String Model, String imageName, Date updatedAt, User user) {
        this.id = id;
        this.Marque = Marque;
        this.Annee = Annee;
        this.Model = Model;
        this.imageName = imageName;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Voiture() {
    }
   public boolean isEmpty() {
        if (id==0) 
            return true;
        else 
            return false;
    }





    
    
    
    
    
    
    
}
