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
    public static final String INSTRUCTOR_ROLE ="Instructor";
    public static final String STUDENT_ROLE ="Student";
    
    public static boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user")!= null;
    }
    
    public static UserDTO getUser(String strUsername){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(strUsername);
        return user;
    } 
    
    public static UserDTO getUser(HttpSession session){
        Object obj = session.getAttribute("user");
        return (obj!= null)?(UserDTO)obj : null;
    } 
    
    public static boolean isValidLogin(String strUsername , String strPassword){
        UserDTO user = getUser(strUsername);
        return user != null &&(user.getPassword().equals(strPassword));
    }
    
    public static boolean isStudent(HttpSession session){
        if(!isLoggedIn(session)){
            return false;
        }
        UserDTO user = (UserDTO)session.getAttribute("user");
        return  user.getRole().equals(STUDENT_ROLE);
    }
    public static boolean isInstructor(HttpSession session){
        if(!isLoggedIn(session)){
            return false;
        }
        UserDTO user = (UserDTO)session.getAttribute("user");
        return  user.getRole().equals(INSTRUCTOR_ROLE);
    }

}
