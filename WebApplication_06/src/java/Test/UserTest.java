/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import dao.UserDAO;
import dto.UserDTO;

/**
 *
 * @author trong
 */
public class UserTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        UserDTO ud1 = new UserDTO("TLN01", "Le Nhat Tung", "AD", "khong_co_password");
//        userDAO.create(ud1);
        
//        for (int i = 0; i < 10; i++) {
//            UserDTO ud_i = new UserDTO("TLN0"+i, "Nguyen Van "+i, "US", "_"+i);
//            userDAO.create(ud_i);
//        }


//Test update
        UserDTO u = new UserDTO("TLN01", "Le Nhat Nam", "US", "Nothing");
        userDAO.update(u);
    }
}
