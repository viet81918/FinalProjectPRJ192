/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import static Controller.Java_JDBC.getConnectionWithSqlJdbc;
import Dao.Login;
import Model.Admin;
import Model.Customer;

import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            Connection con = getConnectionWithSqlJdbc();
            try {
                String u = request.getParameter("name");
                String e = request.getParameter("email");/**/
                String p = request.getParameter("pass");
                String r = request.getParameter("remember");
                Login d =new Login();
                Users a;
                try {
                    a = d.check(u,e, p);
                    if( a == null){
                        request.setAttribute("error", "An Account not Exist!!!");
                        request.setAttribute("red", true);
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                    else {
                        HttpSession session = request.getSession();
                        if ("Customer".equals(a.getRole())){
                            
                            session.setAttribute("account", Java_JDBC.getCustomerByUserId(con, a.getUserId()));
                        }
                        if ("Admin".equals(a.getRole())){
                            
                            session.setAttribute("account", Java_JDBC.getAdminByUserId(con, a.getUserId()));
                        }
                        Cookie cu = new Cookie("name",u);
                        Cookie ce = new Cookie("email",e);
                        Cookie cp = new Cookie("pass",p);
                        Cookie cr = new Cookie("remember",r);
                        if (r == null){
                            cu.setMaxAge(0);
                            ce.setMaxAge(0);
                            cp.setMaxAge(0);
                            cr.setMaxAge(0);
                        }
                        else {
                            cu.setMaxAge(60*60);
                            ce.setMaxAge(60*60);
                            cp.setMaxAge(60*60);
                            cr.setMaxAge(60*60);
                            
                        }
                        response.addCookie(cu);
                        response.addCookie(ce);
                        response.addCookie(cp);
                        response.addCookie(cr);
                        // response.sendRedirect("Home.jsp");
                        response.sendRedirect("Home.jsp");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
