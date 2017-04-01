/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author GARCII
 */
public class Card {
    
    private int id ;
    private String numCard;
    private String cvc;
    private String expDate;
    private Account idAccount;

    
    
    
    public Card(){}
    
    public Card(String numCard, String cvc, String expDate) {
        this.numCard = numCard;
        this.cvc = cvc;
        this.expDate = expDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }


    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getExpDate() {
        return expDate;
    }

   

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

  
     @Override
    public String toString() {
        return "Card{" + "id=" + id + ", numCard=" + numCard + ", cvc=" + cvc + ", expDate=" + expDate + ", idAccount=" + idAccount.getId() + '}';
    }
    
    
    
    
    
    
}
