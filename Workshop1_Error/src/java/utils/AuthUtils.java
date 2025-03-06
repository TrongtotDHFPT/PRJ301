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
 * @author trong
 */
public class AuthUtils {
    public static final String ROLE_FOUNDER  ="Founder";
    public static final String ROLE_MEMBER  ="Team Member";
    public static UserDTO getUser(String strUsername) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByUsername(strUsername);
        return user;
    }
    public static UserDTO getUser(HttpSession session) {
        Object obj = session.getAttribute("user");
        return (obj!=null)?(UserDTO)obj:null;
    }

    public static boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        return user != null && (user.getPassword().equals(strPassword));
    }

    public static  boolean isValidStatus(String status) {
        return status != null && (status.trim().equalsIgnoreCase("Ideation") || status.trim().equalsIgnoreCase("Development")
                || status.trim().equalsIgnoreCase("Launch") || status.trim().equalsIgnoreCase("Scaling"));
    }
    public static boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user")!= null;
    }
    
    public static boolean isFounder(HttpSession session){
        if(!isLoggedIn(session)){
            return false;
        }
        UserDTO user = (UserDTO)session.getAttribute("user");
        return user.getRole().equals(ROLE_FOUNDER);
    }
}
