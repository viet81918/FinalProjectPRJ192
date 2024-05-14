package Controller;

import Model.Admin;
import Model.Customer;
import Model.Users;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "InformationCus", urlPatterns = {"/InformationCus"})
public class InformationCus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet InformationCus</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet InformationCus at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            Logger.getLogger(InformationCus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = Java_JDBC.getConnectionWithSqlJdbc()) {           
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("account");
            if (user != null) {
                String role = user.getRole();
                if ("Customer".equals(role) || "Admin".equals(role)) {
                    if ("Customer".equals(role)) {
                        Customer customer = Java_JDBC.getCustomerByUserId(con, user.getUserId());
                        if (customer != null) {
                            request.setAttribute("customer", customer);
                            request.getRequestDispatcher("inforAcc.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("Login.jsp");
                        }
                    } else if ("Admin".equals(role)) {
                        Admin admin = Java_JDBC.getAdminByUserId(con, user.getUserId());
                        if (admin != null) {
                            request.setAttribute("admin", admin);
                            request.getRequestDispatcher("inforAcc.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("Login.jsp");
                        }
                    }
                } else {
                    // Unauthorized access
                    response.sendRedirect("Login.jsp");
                }
            } else {
                // User not logged in
                response.sendRedirect("Login.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(InformationCus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

