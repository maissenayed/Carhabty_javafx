/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class AstuceRate {
    private int id;

   
    private User user;
    private Astuce astuce;
    private float rate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Astuce getAstuce() {
        return astuce;
    }

    public void setAstuce(Astuce astuce) {
        this.astuce = astuce;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
