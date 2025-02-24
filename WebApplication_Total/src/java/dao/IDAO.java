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
public interface IDAO<T , K> {
    public List<T> readByTitle(K id);
    public T readByID(K id);
}
