/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.UserDAO;
import dto.UserDTO;
import java.util.List;

/**
 *
 * @author trong
 */
public class AuthUtils {

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";
    public static final UserDAO udao = new UserDAO();

    public static UserDTO getUser(String strUsername) {
        UserDTO user = udao.getUserByUsername(strUsername);
        return user;
    }

    public static boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        return user != null && (user.getPassword().equals(strPassword));
    }

    public static boolean isExist(String strUsername) {
        return udao.isExist(strUsername);//true => đã tồn tại
    }

}
