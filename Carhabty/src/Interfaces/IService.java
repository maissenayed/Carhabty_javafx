/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author skin
 */
public interface IService<T> {

    
    boolean add(T t);

    boolean update(T t);

    boolean remove(T t);

    List<T> findALL();

    T findById(int id);

   
    
}
    

