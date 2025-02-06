/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trong
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public int GCD (int a , int b){
        int min = Math.min(a, b);// lấy thg nào nhỏ hơn
        for(int i = min ; i >= 1; i--){
            if(a%i==0 && b%i==0){
                return i;
            }
        }
        return 1;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                PrintWriter out = response.getWriter();
                //bỏ trống 
                //chỉ nhập khoảng trắng
                String a = request.getParameter("txtA");
                String b = request.getParameter("txtB");
                if(a.trim().length() == 0){
                    out.println("Please enter a value!");
                    return;
                }
                if(b.trim().length() == 0){
                    out.println("Please enter b value!");
                    return;
                }
                
                //là số nguyên
                //số nguyên > 0
                int number_a = 0;
                int number_b = 0;
                try {
                   number_a =  Integer.parseInt(a);
                   if(number_a <= 0){
                       out.println("a must be greater than zero!");
                       return;
                   }
                } catch (Exception e) {
                    out.print("a must be interger!");
                }
                
                try {
                   number_b=  Integer.parseInt(b);
                   if(number_b <= 0){
                       out.println("b must be greater than zero!");
                       return;
                   }
                } catch (Exception e) {
                    out.print("b must be interger!");
                }
                int result = GCD(number_a,number_b);
                out.println("Greatest Common Divisor "+number_a+
                        " and "+number_b+" is :"
                        +result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
