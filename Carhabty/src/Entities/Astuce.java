/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Lenovo
 */
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class Astuce {
    
    private int id ;
    private String theme;
    private String titre;
    private String description;
    private String image_name;
    private User user_id ;
    private LocalDate date;

    public Astuce(){}
    
    public Astuce(String theme, String titre, String description, String image_name, User user_id, LocalDate date) {
        this.theme = theme;
        this.titre = titre;
        this.description = description;
        this.image_name = image_name;
        this.user_id = user_id;
        this.date = date;
    }

    public Astuce(int id, String theme, String titre, String description) {
        this.id = id;
        this.theme = theme;
        this.titre = titre;
        this.description = description;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
}


