package Controller;

import Model.Novel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name="UploadChapterServlet", urlPatterns={"/UploadChapterServlet"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10) // Setting a threshold for file size
public class UploadChapterServlet extends HttpServlet {

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
            request.getRequestDispatcher("updateChapter.jsp").forward(request, response);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadChapterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String novelId = request.getParameter("novelId");
            Part filePart = request.getPart("chapterFile");
            
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
                        boolean success = Java_JDBC.updateChapterInDatabase(novelId, n.getNovelName() ,generateChapterId(novelId),fileName, fileContent);
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

  
    private String generateChapterId(String novelId) throws Exception {
         Connection con = Java_JDBC.getConnectionWithSqlJdbc();  
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
            for (Novel n :novels ){
                if (n.getNovelId().equalsIgnoreCase(novelId)){
        return novelId+"_"+"C"+ (n.getNumberOfChapters()+1) ;
                }
            }
        return null;
    }
}
