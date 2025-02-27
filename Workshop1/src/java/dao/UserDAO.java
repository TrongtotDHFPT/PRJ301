/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.util.List;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO , String> {

    @Override
    public boolean create(UserDTO entity) {
        return true;
    }

    @Override
    public List<UserDTO> readAll() {
        return null;
    }

    @Override
    public UserDTO readByID(String id) {
        return null;
    }

    @Override
    public boolean update(UserDTO entity) {
        return true;
    }

    @Override
    public boolean delete(String id) {
        return true;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        return null;
    }
    
}
