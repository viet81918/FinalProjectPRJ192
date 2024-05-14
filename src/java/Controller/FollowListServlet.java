/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import static Controller.Java_JDBC.getConnectionWithSqlJdbc;
import Model.AddList;
import Model.Admin;
import Model.Customer;
import Model.FollowList;
import Model.Users;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class FollowListServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            HttpSession session = request.getSession();

            // Try to retrieve the "account" attribute from the session and cast it to Customer
            Customer customer = null;
            Admin admin = null;
            try {
                customer = (Customer) session.getAttribute("account");
            } catch (ClassCastException e) {
                try {
                    admin = (Admin) session.getAttribute("account");
                } catch (ClassCastException ex) {
                    // Handle the case where the attribute cannot be cast to Admin
                    // Log the exception or perform other error handling
                    ex.printStackTrace(); // Example: print the stack trace
                }
            }

            if (customer != null) {
                FollowList followList = Java_JDBC.getFollowListByCustomerId(con, customer);
                request.setAttribute("followList", followList);
                request.getRequestDispatcher("listSave.jsp").forward(request, response);
            } else if (admin != null) {
                AddList addList = Java_JDBC.getAddListByAdminId(con, admin);
                request.setAttribute("addList", addList);
                request.getRequestDispatcher("listAddForAdmin.jsp").forward(request, response);
            } else {
                // Account object is null, handle accordingly
                // For example, redirect to login page
                response.sendRedirect("Login.jsp");
            }
        } catch (Exception ex) {
              try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Just read</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
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
       try {
           processRequest(request, response);
       } catch (Exception ex) {
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " +  ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
           Logger.getLogger(FollowListServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
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
           processRequest(request, response);
       } catch (Exception ex) {
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " +   ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
           Logger.getLogger(FollowListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
