/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.IllustrationList;
import Model.Novel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tuanh
 */
@WebServlet(name="AddIllustrationServlet", urlPatterns={"/AddIllustrationServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10)
public class AddIllustrationServlet extends HttpServlet {
   
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
         response.setContentType("text/html;charset=UTF-8");
        try {
          
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Retrieve author details based on author ID
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);

            // Set author and novels as request attributes
            request.setAttribute("novels", novels);
         

            // Forward the request to the authorDetail.jsp
            request.getRequestDispatcher("addIllustration.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Handle SQLException
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
            Logger.getLogger(AddIllustrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            String novelId = request.getParameter("novelId");
            Part filePart = request.getPart("file");
            
            if (novelId == null || filePart == null) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Form Submission Error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error: Novel ID or Chapter File is null</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            }
            
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            
            // Retrieve author details based on author ID
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
            for (Novel  n :novels ){
                if(n.getNovelId().equalsIgnoreCase(novelId)){
                    try {
                        boolean success = Java_JDBC.updateIlluStrationInDatabase(novelId, n.getNovelName() ,generateIllustrationId(novelId),fileName, fileContent);
                        if (success) {
                            response.sendRedirect("NovelDetailsServlet?novelId=" + novelId);
                        } else {
                            try (PrintWriter out = response.getWriter()) {
                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Database Error</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>Error occurred while updating chapter in the database</h1>");
                                out.println("</body>");
                                out.println("</html>");
                            }
                        }
                    } catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Exception</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>Exception occurred: " + e.getMessage() + "</h1>");
                            out.println("</body>");
                            out.println("</html>");
                        }
                    } finally {
                        if (fileContent != null) {
                            fileContent.close();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UploadChapterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  
    private String generateIllustrationId(String novelId) throws Exception {
         Connection con = Java_JDBC.getConnectionWithSqlJdbc();  
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
            for (Novel n :novels ){
                if (n.getNovelId().equalsIgnoreCase(novelId)){
                    IllustrationList illuList =Java_JDBC.getIllustrationListByNovel(con,n);
        return novelId+"_"+"I"+ (illuList.getNumberOfIllustrationsForNovel(n)+1) ;
                }
            }
        return null;
    }

}
