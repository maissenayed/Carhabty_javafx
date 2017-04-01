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
public class Account {
    
    private int id ;
    private float solde;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", solde=" + solde + '}';
    }
    
    
    
    
    
    
    
}
