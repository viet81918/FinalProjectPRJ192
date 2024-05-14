/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Chapter;
import Model.ChapterList;
import Model.IllustrationList;
import Model.Novel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "readNovelServlet", urlPatterns = {"/readNovelServlet"})
public class readNovelServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {

    java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();

    // Get the novelId parameter from the request
    String novelId = request.getParameter("novelId");

    // Retrieve the novel based on the novelId
    Novel novel = null;
    ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
    for (Novel n : novels) {
        if (n.getNovelId().equals(novelId)) {
            novel = n;
            break; // Exit loop once the novel is found
        }
    }

    // Check if the novel is found
    if (novel != null) {
        // Retrieve chapter information
        ChapterList chapterList = Java_JDBC.getChapterFromNovel(con, novel);
        String flagString = request.getParameter("flag");
        int flag = Integer.parseInt(flagString) - 1;
        Chapter chapter = chapterList.getChapter(novel, flag);

        // Set attributes for forwarding to JSP
        request.setAttribute("novel", novel);
        request.setAttribute("flag", flag);
        request.setAttribute("chapter", chapter);
        request.setAttribute("numberOfChapter", novel.getNumberOfChapters());

        // Forward request to readNovel.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("readNovel.jsp");
        dispatcher.forward(request, response);
    } else {
        // Novel not found, handle error or redirect
        response.sendRedirect("error.jsp"); // Redirect to error page
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
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
            Logger.getLogger(readNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
                out.println("<title>Just read</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            Logger.getLogger(readNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void main(String[] args) throws Exception {
        java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
        String novelId = "LC1";
        ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
        for (Novel n : novels) {
            if (n.getNovelId().equals(novelId)) {
                ChapterList chapterList = Java_JDBC.getChapterFromNovel(con, n);
                Chapter chapter = chapterList.getChapter(n, 0);
                System.out.println(chapter.getContent());

            }
        }

    }
}
