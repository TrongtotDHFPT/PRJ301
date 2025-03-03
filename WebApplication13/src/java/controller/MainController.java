        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDAO;
import dao.UserDAO;
import dto.BookDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tungi
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public static final String LOGIN_PAGE = "login.jsp";
    BookDAO BookDAO = new BookDAO();
    public UserDTO getUser (String strUserID){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUserID);
        return user;
    }
    
    public boolean isValidLogin(String strUserID ,String strPassword){
        UserDTO user = getUser(strUserID);
        return user != null && (user.getPassword().equals(strPassword));
    }
    public void search(HttpServletRequest request, HttpServletResponse response){
        String searchTerm = request.getParameter("searchTerm");
        if(searchTerm == null){
            searchTerm = "";
        }
        List<BookDTO> books = BookDAO.searchByTitle2(searchTerm);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("books", books);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");
        try {
            if(action == null){
                url = LOGIN_PAGE;
            }else{
                if(action.equals("login")){
                    //lấy userID  và Password ra
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if(isValidLogin(strUserID, strPassword)){
                        url = "search.jsp";
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);
                        search(request,response);
                    }else {
                        request.setAttribute("message", "Error UserID or Password");
                        url = "login.jsp";
                    }
                }else if(action.equals("logout")){
                    request.getSession().invalidate();// Hủy Session => toàn bộ biến trong đó mất hết
                    url="login.jsp";
                }else if(action.equals("search")){
                    search(request,response);
                    url="search.jsp";
                }else if(action.equals("delete")){
                    String id = request.getParameter("id");
                    BookDAO.updateQuantityToZero(id);
                    //search
                    search(request,response);
                    url="search.jsp";
                }else if(action.equals("add")){
                    try {
                        String bookID = request.getParameter("txtBookID");
                        String title = request.getParameter("txtTitle");
                        String author = request.getParameter("txtAuthor");
                        int publishYear = Integer.parseInt(request.getParameter("txtPublishYear"));
                        double price = Double.parseDouble(request.getParameter("txtPrice"));
                        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                        boolean errorCheck = false;
                        if(bookID == null || bookID.trim().isEmpty()){
                            errorCheck = true;
                            request.setAttribute("txtBookID_error","Book ID can't be empty!");
                        }
                        if(title == null || title.trim().isEmpty()){
                            errorCheck = true;
                            request.setAttribute("txtTitle_error", "Title can't be empty!");
                        }
                        if(publishYear <= 0 && publishYear > 2025 ){
                            errorCheck = true;
                            request.setAttribute("txtPublishYear_error", "PublishYear is invalid!");
                        }
                        if(price <= 0){
                            errorCheck = true;
                            request.setAttribute("txtPrice_error", "Price must be greater than 0!");
                        }
                        if(quantity <0){
                            errorCheck = true;
                            request.setAttribute("txtQuantity_error","Quantity must be greater than 0!");
                        }
                        
                        
                        BookDTO book = new BookDTO(bookID, title, author, publishYear, price, quantity);

                        if(!errorCheck){//Nếu không có lỗi => check vẫn là : false
                            BookDAO.create(book);
                            search(request, response);
                            url ="search.jsp";
                        }else{
                            url = "bookForm.jsp";
                            request.setAttribute("book", book);
                        }
                    } catch (Exception e) {
                    }
                    
                }
            }
        } catch (Exception e) {
            log("Error at MainController :" +e.toString());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}