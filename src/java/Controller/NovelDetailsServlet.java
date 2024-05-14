/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Author;
import Model.Chapter;
import Model.ChapterList;
import Model.Format;
import Model.Genre;
import Model.Illustration;
import Model.IllustrationList;
import Model.Novel;
import Model.Theme;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="NovelDetailsServlet", urlPatterns={"/NovelDetailsServlet"})
public class NovelDetailsServlet extends HttpServlet {
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException, Exception {
    response.setContentType("text/html;charset=UTF-8");
    java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
    String novelId = request.getParameter("novelId");
    ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
    for (Novel n : novels) {
        if (n.getNovelId().equals(novelId)) {
            String name = n.getNovelName();
            IllustrationList illustration = Java_JDBC.getIllustrationListByNovel(con, n);
            ArrayList<Format> formats = Java_JDBC.getFormatsByNovelId(con, n);
            ArrayList<Theme> themes = Java_JDBC.getThemesByNovelId(con, n);
            ArrayList<Genre> genres = Java_JDBC.getGenresByNovelId(con, n);
            String description = n.getDescription();
            ArrayList<Author> authors = (Java_JDBC.getAuthorByNovel(con, n));
            
            ChapterList chapterList = Java_JDBC.getChapterFromNovel(con, n);
             request.setAttribute("novel", n);
            request.setAttribute("name", name);
            request.setAttribute("illustration", illustration);
            request.setAttribute("description", description);
            request.setAttribute("authors", authors);
            request.setAttribute("formats", formats);
            request.setAttribute("themes", themes);
            request.setAttribute("genres", genres);
            request.setAttribute("chapterList", chapterList); // Remove extra space after "chapterList"
        }
    }

    request.getRequestDispatcher("novelDetail.jsp").forward(request, response);
}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        try {
            processRequest(request, response);
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
            java.util.logging.Logger.getLogger(NovelDetailsServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             processRequest(request, response);
         } catch (SQLException ex) {
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
             java.util.logging.Logger.getLogger(NovelDetailsServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(NovelDetailsServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public String getServletInfo() {
        return "Novel Details Servlet";
    }
 public static void main(String[] args) throws Exception {
        java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
        String novelId = "LC1";
   ArrayList<Author> authors = (Java_JDBC.getAuthorByNovel(con, Java_JDBC.getNovelByNovelId(con, novelId)));    
         for (Author a : authors){
             System.out.println(a);
         }

    }

}
