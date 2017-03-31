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
    private double solde;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", solde=" + solde + '}';
    }
    
    
    
    
    
    
    
}
