/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;


/**
 * Created by GARCII on 3/20/2017.
 */
public class Statistique {
    private int x;
    private int y;
    private Date date;
    private String nomX;

    public Statistique() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomX() {
        return nomX;
    }

    public void setNomX(String nomX) {
        this.nomX = nomX;
    }

    @Override
    public String toString() {
        return "Statistique{" + "x=" + x + ", y=" + y + ", date=" + date + ", nomX=" + nomX + '}';
    }

    

    
}
