/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Login;
import Dao.SignUpController;
import Model.Customer;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
       request.getRequestDispatcher("SignUp.jsp").forward(request, response);
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
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String dobString = request.getParameter("birthday");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String conpass = request.getParameter("cpass");

        String role = "Customer";

        Login l = new Login();
        Users acc;
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$";
        if (firstName.isEmpty() || lastName.isEmpty() || pass.isEmpty() || !email.matches(emailPattern) || !pass.equals(conpass)) {
            request.setAttribute("mess", "Invalid information!!!!");
            //response.sendRedirect("SignUp.jsp");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
        } else {
            try {
                int age = calculateAge(dobString);
                acc = l.check(username, email, pass);
                if (acc == null) {
                    try (java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc()) {
                        int numberUser = Java_JDBC.getAllUsers(con).size();
                        int numberCus = Java_JDBC.getAllCustomers(con).size();
                        SignUpController.CreateAccountCustomer(con, username, age, email, pass, firstName, lastName, role, numberUser, numberCus);
                        //response.sendRedirect("Home.jsp");
                        
                        request.getRequestDispatcher("Home.jsp").forward(request, response);
                    } catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Servlet SignUpServlet</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>Servlet SignUpServlet at " + e.getMessage() + "</h1>");
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }
                } else {
                    request.setAttribute("mess", "An Account is Exist!!!");
                    //response.sendRedirect("SignUp.jsp");
                    request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                }

                /*
            try (java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc()) {
            String name = request.getParameter("name");  
            String pass = request.getParameter("pass");
            Java_JDBC.CreateAccount(con, name, pass);
            } catch (Exception e) {
            e.printStackTrace();
            }
            response.sendRedirect("index.jsp");
                 */
            } catch (Exception ex) {
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet SignUpServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet SignUpServlet at " + ex.getMessage() + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private int calculateAge(String dobString) throws ParseException {
        //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //Date dob = dateFormat.parse(dobString);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dob = null;
        try {
            dob = inputFormat.parse(dobString);
            dobString = outputFormat.format(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dob);

        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
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
