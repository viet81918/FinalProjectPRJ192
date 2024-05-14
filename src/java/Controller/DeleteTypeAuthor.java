/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;

/**
 *
 * @author LENOVO
 */
public class DeleteTypeAuthor extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
        java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
        String[] deleteFormats = request.getParameterValues("deleteFormat");
        String[] deleteThemes = request.getParameterValues("deleteTheme");
        String[] deleteGenres = request.getParameterValues("deleteGenre");
        String[] deleteAuthors = request.getParameterValues("deleteAuthor");

        // Delete selected formats
        if (deleteFormats != null) {
            for (String formatId : deleteFormats) {
                Java_JDBC.deleteFormat(con, formatId);
            }
        }

        // Delete selected themes
        if (deleteThemes != null) {
            for (String themeId : deleteThemes) {
                Java_JDBC.deleteTheme(con, themeId);
            }
        }

        // Delete selected genres
        if (deleteGenres != null) {
            for (String genreId : deleteGenres) {
                Java_JDBC.deleteGenre(con, genreId);
            }
        }

        // Delete selected authors
        if (deleteAuthors != null) {
            for (String authorId : deleteAuthors) {
                Java_JDBC.deleteAuthor(con, authorId);
            }
        }

        // Redirect to a success page
        response.sendRedirect("Home.jsp");
    } catch (Exception ex) {
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthorDetailsServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorDetailsServlet at " + ex.getMessage() + "</h1>");
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
          try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthorDetailsServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorDetailsServlet at "+ "JustNothing" + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
