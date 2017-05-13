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
public class Annonce {

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //attribut
    private int id;
    private String title;
    private Date AnneeDeProduit;
    private Date AnneePub;    
    private String Model;   
    private String Marque;   
    private String Region;   
    private String Ville;   
    private String Paye;  
    private float Prix;   
    private String Category;  
    private String Descreption;
    private String imageName;
    private Date updatedAt;
    private User user;
    private String photo;
   private String image;
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    
    
    
    
    
    
    
    
    //getter and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAnneeDeProduit() {
        return AnneeDeProduit;
    }

    public void setAnneeDeProduit(Date AnneeDeProduit) {
        this.AnneeDeProduit = AnneeDeProduit;
    }

    public Date getAnneePub() {
        return AnneePub;
    }

    public void setAnneePub(Date AnneePub) {
        this.AnneePub = AnneePub;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public String getPaye() {
        return Paye;
    }

    public void setPaye(String Paye) {
        this.Paye = Paye;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDescreption() {
        return Descreption;
    }

    public void setDescreption(String Descreption) {
        this.Descreption = Descreption;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annonce() {
    }

    public Annonce(int id, String title, Date AnneeDeProduit, Date AnneePub, String Model, String Marque, String Region, String Ville, String Paye, float Prix, String Category, String Descreption, String imageName, Date updatedAt, User user) {
        this.id = id;
        this.title = title;
        this.AnneeDeProduit = AnneeDeProduit;
        this.AnneePub = AnneePub;
        this.Model = Model;
        this.Marque = Marque;
        this.Region = Region;
        this.Ville = Ville;
        this.Paye = Paye;
        this.Prix = Prix;
        this.Category = Category;
        this.Descreption = Descreption;
        this.imageName = imageName;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", title=" + title + ", AnneeDeProduit=" + AnneeDeProduit + ", AnneePub=" + AnneePub + ", Model=" + Model + ", Marque=" + Marque + ", Region=" + Region + ", Ville=" + Ville + ", Paye=" + Paye + ", Prix=" + Prix + ", Category=" + Category + ", Descreption=" + Descreption + ", imageName=" + imageName + ", updatedAt=" + updatedAt + ", user=" + user + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
