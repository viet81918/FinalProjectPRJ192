/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import static Controller.Java_JDBC.deleteChapterFromDatabase;
import Model.Chapter;
import Model.ChapterList;
import Model.Novel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class DeleteChapter extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
          response.setContentType("text/html;charset=UTF-8");
        try {
          
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Retrieve author details based on author ID
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);

            // Set author and novels as request attributes
            request.setAttribute("novels", novels);
         

            // Forward the request to the authorDetail.jsp
            request.getRequestDispatcher("deleteChapter.jsp").forward(request, response);
        } catch (SQLException ex) {
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
          response.setContentType("text/html;charset=UTF-8");
        try {
          
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();
   String novelId = request.getParameter("novelId");
   Novel novel = Java_JDBC.getNovelByNovelId(con, novelId);
            ChapterList chapterList = Java_JDBC.getChapterFromNovel(con, novel);
            List<Chapter > chapters =chapterList.getChaptersForNovel(novel);
            request.setAttribute("chapters", chapters);
         
            request.getRequestDispatcher("deleteChapter.jsp").forward(request, response);
        } catch (SQLException ex) {
              try {
                  processRequest(request,response);
              } catch (Exception ex1) {
                  Logger.getLogger(DeleteChapter.class.getName()).log(Level.SEVERE, null, ex1);
              }
        } catch (Exception ex) {
              try {
                  processRequest(request,response);
              } catch (Exception ex1) {
                  Logger.getLogger(DeleteChapter.class.getName()).log(Level.SEVERE, null, ex1);
              }
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
    String chapterId = request.getParameter("ChapterId");
    
    try {
        boolean chapterDeleted = deleteChapterFromDatabase(chapterId);
        
        if (chapterDeleted) {
            // Chapter successfully deleted, redirect to a success page or perform any other action
            response.sendRedirect("Home.jsp");
        } else {
            // Chapter deletion failed, handle the error (e.g., show an error message)
            // You can forward the request back to the same page or redirect to an error page
            response.sendRedirect("Login.jsp");
        }
    } catch (SQLException ex) {
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
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
