/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dao.updateController;
import Model.Admin;
import Model.Customer;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
@WebServlet(name="UpdateServlet", urlPatterns={"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath () + "</h1>");
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
        //processRequest(request, response);
        String newFN = request.getParameter("newFirstname");
        String newLN = request.getParameter("newLastname");
        String newEmail = request.getParameter("newEmail");
        String newPass  = request.getParameter("newPassword");
        String newDate = request.getParameter("newBirthday");
        String conpasword = request.getParameter("cpass");
        
        //String customerId = request.getSession().getAttribute("customerId").toString();
        //updateController updatecontroller = new updateController();
        //Customer updatedCustomer = updateController.updateCustomer(con, customerId, newAge, newEmail,newPass, newFN, newLN);       
                           // request.setAttribute("customer", updatedCustomer);
                            //request.getRequestDispatcher("inforAcc.jsp").forward(request, response);
                      
        String emailCheck = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$";
        if(!newEmail.matches(emailCheck) || !newPass.equals(conpasword)){
            request.setAttribute("mess", "Invalid email or password!!!!");
            //response.sendRedirect("SignUp.jsp");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            
        }else{
            
                try {
                    int newAge = calculateAge(newDate);
                    
                        try(Connection con = Java_JDBC.getConnectionWithSqlJdbc()){
                            HttpSession session = request.getSession();
                            Users u = (Users) session.getAttribute("account");
                            /*
                            updateController.updateCustomer(con,newAge, newEmail, newPass , newFN, newLN,u.getUserId());
                            Customer customer = Java_JDBC.getCustomerByUserId(con, u.getUserId());
                            //updateController.updateCustomer(con,newAge, newEmail, newPass , newFN, newLN);
                            request.setAttribute("customer", customer);
                            request.getRequestDispatcher("inforAcc.jsp").forward(request, response);*/
                            String role = u.getRole();
                            if ("Customer".equals(role)) {
                                updateController.updateCustomer(con,newAge, newEmail, newPass , newFN, newLN,u.getUserId());
                                Customer customer = Java_JDBC.getCustomerByUserId(con, u.getUserId());
                                if (customer != null) {
                                    request.setAttribute("customer", customer);
                                    request.getRequestDispatcher("inforAcc.jsp").forward(request, response);
                                } else {
                                    response.sendRedirect("Login.jsp");
                                }
                            } else if ("Admin".equals(role)) {
                                updateController.updateAdmin(con,newAge, newEmail, newPass , newFN, newLN,u.getUserId());
                                Admin admin = Java_JDBC.getAdminByUserId(con, u.getUserId());
                                if (admin != null) {
                                    request.setAttribute("admin", admin);
                                    request.getRequestDispatcher("inforAcc.jsp").forward(request, response);
                                } else {
                                    response.sendRedirect("Login.jsp");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            }   
                } catch (ParseException ex) {
                    Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        }
    }
    private int calculateAge(String newDate) throws ParseException {
        //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //Date dob = dateFormat.parse(dobString);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dob = null;
        try {
            dob = inputFormat.parse(newDate);
            newDate = outputFormat.format(dob);
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}