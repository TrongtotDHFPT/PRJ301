/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.UserDAO;
import dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tungi
 */
public class AuthUtils {

    public static final String ADMIN_ROLE = "AD";
    public static final String USER_TOLE = "US";

    public static UserDTO getUser(String strUserID) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(strUserID);
        return user;
    }

    public static boolean isValidLogin(String strUserID, String strPassword) {
        UserDTO user = getUser(strUserID);
        return user != null &&  utils.PasswordUtils.checkPassword(strPassword, user.getPassword());
    }


    public static UserDTO getUser(HttpSession session) {
        if (!isLoggedIn(session)) {
            return null;
        }
        return (UserDTO) session.getAttribute("user");
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
     public static boolean isAdmin(HttpSession session) {
        if (!isLoggedIn(session)) {
            return false;
        }
        UserDTO user = getUser(session);
        return user.getRoleID().equals(ADMIN_ROLE);
    }
}