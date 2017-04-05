package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by GARCII on 3/12/2017.
 */
public class Coupon {

    private int id;
    private LocalDateTime date;
    private String reference;

    public String getFakeDate() {
        return fakeDate;
    }

    public void setFakeDate(String fakeDate) {
        this.fakeDate = fakeDate;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }

    public String getNomsociete() {
        return nomsociete;
    }

    public void setNomsociete(String nomsociete) {
        this.nomsociete = nomsociete;
    }
    private User acheteur;
    private Offre offreAchete;
    private String fakeDate,adresse,offre,nomsociete;

    public Coupon(String reference,LocalDateTime date) {
        this.date = date;
        this.reference = reference;
    }

    public Coupon(String reference,String fakeDate) {
        this.fakeDate = fakeDate;
        this.reference = reference;
    }

    public Coupon(String reference, String fakeDate, String adresse, String offre, String nomsociete) {
        this.reference = reference;
        this.fakeDate = fakeDate;
        this.adresse = adresse;
        this.offre = offre;
        this.nomsociete = nomsociete;
    }
    
    
    
    
   
    
    public Coupon(){}
    
    public Coupon(LocalDateTime date, String reference, User acheteur, Offre offreAchete) {
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

    
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
