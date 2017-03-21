package Entities;

import java.sql.Blob;
import java.util.Objects;

/**
 * Created by GARCII on 3/12/2017.
 */
public class User {


    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String nomSociete;
    private String activite;
    private String siret;
    private String image;

    
    public User(){}
    
    public User(int id, String username,String email, String password,String nom, String prenom, String tel, String adresse) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.email=email;
        this.password= password;
    }

    
   
    public User(int id, String username, String email, String password, String nom, String prenom, String tel, String adresse, String nomSociete, String activite, String siret) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.nomSociete = nomSociete;
        this.activite = activite;
        this.siret = siret;
    }

    
    
    
    
    
    
    public User(String username, String email, String password, String nom, String prenom, String tel, String adresse) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
    }

   
    
    public User(String username, String password,String email,String nom, String prenom, String tel, String adresse, String nomSociete, String activite, String siret) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.nomSociete = nomSociete;
        this.activite = activite;
        this.siret = siret;
    }

   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getSiret() {
        return siret;
    }

   

    public void setSiret(String siret) {
        this.siret = siret;
    }

     public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }
    
     public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }   
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse + ", nomSociete=" + nomSociete + ", activite=" + activite + ", siret=" + siret + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    
   

}




