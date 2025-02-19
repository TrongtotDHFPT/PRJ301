/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public static final String LOGIN_PAGE = "login.jsp" ;
    
    public UserDTO getUser(String strUserID){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUserID);
        return user;
    }
    public boolean isValidLogin(String strUserID , String strPassWord){
        UserDTO user  = getUser(strUserID);
//        if(strUserID!= null &&  user.getPassword().equals(strPassWord)){
//            return true;
//        }else
//            return false;
        //  or
        return  strUserID!= null &&  user.getPassword().equals(strPassWord);
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action  = request.getParameter("action");
            if(action == null){
                url = LOGIN_PAGE;
            }else{
                if(action.equals("login")){
                    String  strUserID = request.getParameter("txtUserID");
                    String  strPassword = request.getParameter("txtPassword");
                    //đem đi so
                   if(isValidLogin(strUserID, strPassword)){
                       url = "search.jsp";
                       UserDTO user = getUser(strUserID);
                       request.setAttribute("user", user);
                   }else{
                    url = "invalid.jsp";
                }
                    
                }
            }
        } catch (Exception e) {
            log("Error at MainController "+e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset = UTF-8");
        
        
        String url  = LOGIN_PAGE;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
