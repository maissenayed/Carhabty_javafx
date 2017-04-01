package Entities;

import java.time.LocalDate;

/**
 * Created by GARCII on 3/12/2017.
 */
public class Coupon {

    private int id;
    private LocalDate date;
    private String reference;
    private User acheteur;
    private Offre offreAchete;

    
    
    public Coupon(){}
    
    public Coupon(LocalDate date, String reference, User acheteur, Offre offreAchete) {
        this.date = date;
        this.reference = reference;
        this.acheteur = acheteur;
        this.offreAchete = offreAchete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public User getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(User acheteur) {
        this.acheteur = acheteur;
    }

    public Offre getOffreAchete() {
        return offreAchete;
    }

    public void setOffreAchete(Offre offreAchete) {
        this.offreAchete = offreAchete;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", date=" + date + ", reference=" + reference + ", acheteur=" + acheteur.getId() + ", offreAchete=" + offreAchete.getId() + '}';
    }

    







}
