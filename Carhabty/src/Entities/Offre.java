package Entities;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by GARCII on 3/12/2017.
 */
public class Offre {


    private int id ;
    private String nomOffre;
    private String descriptionOffre;
    private float prix;
    private int reduction;
    private LocalDate date;
    private Date dateExp;
    private String image;
    private User user;

    public Offre(){}

     public Offre(int id,String nomOffre, String descriptionOffre, float prix, int reduction) {
       
        this.id=id;
        this.nomOffre = nomOffre;
        this.descriptionOffre = descriptionOffre;
        this.prix = prix;
        this.reduction = reduction;
     }
    
    
    public Offre(int id,String nomOffre, String descriptionOffre, float prix, int reduction, Date dateExp) {
       
        this.id=id;
        this.nomOffre = nomOffre;
        this.descriptionOffre = descriptionOffre;
        this.prix = prix;
        this.reduction = reduction;
        this.dateExp = dateExp;
    }
    
    
    
    public Offre(String nom, String description, float prix, int reduction, LocalDate date, Date dateExp, String image) {
        this.nomOffre = nom;
        this.descriptionOffre = description;
        this.prix = prix;
        this.reduction = reduction;
        this.date = date;
        this.dateExp = dateExp;
        this.image = image;

    }

    public Offre(String nomOffre, String descriptionOffre, float prix, int reduction, LocalDate date) {
        this.nomOffre = nomOffre;
        this.descriptionOffre = descriptionOffre;
        this.prix = prix;
        this.reduction = reduction;
        this.date = date;
    }

    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nom) {
        this.nomOffre = nom;
    }

    public String getDescriptionOffre() {
        return descriptionOffre;
    }

    public void setDescriptionOffre(String description) {
        this.descriptionOffre = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Offre{" + "nom=" + nomOffre + ", description=" + descriptionOffre + ", prix=" + prix + ", reduction=" + reduction + ", date=" + date + ", dateExp=" + dateExp + ", image=" + image + ", user=" + user + '}';
    }





}
