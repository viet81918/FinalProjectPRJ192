/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Chapter;
import Model.Novel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.ChapterList;
import Model.Illustration;
import Model.IllustrationList;
import java.util.List;

/**
 *
 * @author tuanh
 */
@WebServlet(name = "DeleteNovelServlet", urlPatterns = {"/DeleteNovelServlet"})
public class DeleteNovelServlet extends HttpServlet {

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
        try {

            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Retrieve author details based on author ID
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);

            // Set author and novels as request attributes
            request.setAttribute("novels", novels);

            // Forward the request to the authorDetail.jsp
            request.getRequestDispatcher("deleteNovel.jsp").forward(request, response);
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
     *
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
            Logger.getLogger(DeleteNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String novelId = request.getParameter("novelId");

        if (novelId == null || novelId.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Form Submission Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: Novel ID is null</h1>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
        }

        try {
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Create a dummy Novel object with only the ID set
            Novel novel = new Novel();
            novel.setNovelId(novelId);

            // Delete chapters associated with the novel
            ChapterList chapters = new ChapterList();
            List<Chapter> novelChapters = chapters.getChaptersForNovel(novel);
            if (novelChapters != null) {
                for (Chapter chapter : novelChapters) {
                    Java_JDBC.deleteChapterFromDatabase(chapter.getChapterId());
                }
            }

            // Delete illustrations associated with the novel
            IllustrationList illustrations = new IllustrationList();
            List<Illustration> novelIllustrations = illustrations.getIllustrationsForNovel(novel);
            if (novelIllustrations != null) {
            for (Illustration illustration : novelIllustrations) {
                Java_JDBC.deleteIllustrationFromDatabase(illustration.getIllustrationId());
            }
            }
            // Finally, delete the novel itself
            boolean success = Java_JDBC.deleteNovelAndAssociatedData(con, novelId);
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
                    out.println("<h1>Error occurred while deleting novel in database</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (SQLException ex) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Exception</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Exception occurred: " + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Exception</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Exception occurred: " + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
