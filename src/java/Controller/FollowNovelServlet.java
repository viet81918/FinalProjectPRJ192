/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Customer;
import Model.FollowList;
import Model.Novel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class FollowNovelServlet extends HttpServlet {
   
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
            out.println("<title>Servlet FollowNovelServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FollowNovelServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
    HttpSession session = request.getSession();
    Customer customer = (Customer) session.getAttribute("account");
    
    if (customer != null) {
        FollowList followList = (FollowList) session.getAttribute("followList");
        if (followList == null) {
            followList = new FollowList();
            session.setAttribute("followList", followList);
        }

        // Retrieve novelId from request parameter
        String novelId = request.getParameter("novelId");
        java.sql.Connection con = null;
        try {
            con = Java_JDBC.getConnectionWithSqlJdbc();
            // Add novel to followList for the current customer
            followList.addNovel(customer, Java_JDBC.getNovelByNovelId(con, novelId));
            Java_JDBC.addFollowListByNovelIdAndCusId(con, customer.getCustomerId(), novelId);
        } catch (Exception e) {
            // Handle any potential exceptions (e.g., log the error)
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // Handle SQL exception if closing connection fails
                e.printStackTrace();
            }
        }
        
        // Redirect back to the page where the request originated
        response.sendRedirect("Home.jsp");
    } else {
        // Handle case where the customer is not logged in
        response.sendRedirect("Login.jsp"); // Redirect to the login page
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
