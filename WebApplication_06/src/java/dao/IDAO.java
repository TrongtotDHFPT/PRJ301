/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author trong
 */
public interface IDAO<T, K> {
    //T là kiểu dữ liệu 
    //K là...... 10h45
    
    public boolean create (T entity);
    public List<T> readAll();
    public T readById(K id);
    public boolean update (T entity);
    public boolean delete (K id);
    public List<T> search(String seachTerm);
}
