/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.FollowList;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "UnFollowNovelServlet", urlPatterns = {"/UnFollowNovelServlet"})
public class UnFollowNovelServlet extends HttpServlet {

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
            out.println("<title>Servlet UnFollowListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnFollowListServlet at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
    HttpSession session = request.getSession();
    Customer customer = (Customer) session.getAttribute("account");

    if (customer != null) {
        FollowList followList = (FollowList) session.getAttribute("followList");
        if (followList != null) {
            // Retrieve novelId from request parameter
            String novelId = request.getParameter("novelId");
            java.sql.Connection con = null;
            try {
                con = Java_JDBC.getConnectionWithSqlJdbc();
                // Remove novel from followList for the current customer
                followList.removeNovel(customer, Java_JDBC.getNovelByNovelId(con, novelId));
                Java_JDBC.deleteFollowListByNovelIdAndCusId(con, customer.getCustomerId(), novelId);
            } catch (Exception ex) {
                // Forward to an error page or display a more user-friendly error message
                try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UnFollowListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnFollowListServlet at " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                     try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UnFollowListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnFollowListServlet at " +  ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
                    Logger.getLogger(UnFollowNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Redirect back to the page where the request originated
            response.sendRedirect("Home.jsp");
        }
    } else {
        // Handle case where customer is not logged in
        response.sendRedirect("Login.jsp"); // Redirect to login page
    }
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
