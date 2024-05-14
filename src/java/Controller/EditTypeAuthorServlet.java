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
import Model.Novel;
import Model.Theme;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class EditTypeAuthorServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {

            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Retrieve author details based on author ID
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);

            // Set author and novels as request attributes
            request.setAttribute("novels", novels);

            // Forward the request to the authorDetail.jsp
            request.getRequestDispatcher("EditNovelTypeAuthor.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        try {

            Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            String novelId = request.getParameter("novelId");
            Novel novel = Java_JDBC.getNovelByNovelId(con, novelId);
            ArrayList<Format> inclusiveFormats = Java_JDBC.getFormatsByNovelId(con, novel);
            ArrayList<Genre> inclusiveGenres = Java_JDBC.getGenresByNovelId(con, novel);
            ArrayList<Theme> inclusiveThemes = Java_JDBC.getThemesByNovelId(con, novel);
            ArrayList<Author> inclusiveAuthors = Java_JDBC.getAuthorByNovel(con, novel);
              ArrayList<Genre> exclusiveGenres = new ArrayList<>();
            ArrayList<Theme> exclusiveThemes = new ArrayList<>();
            ArrayList<Format> exclusiveFormats = new ArrayList<>();
            ArrayList<Author> exclusiveAuthors = new ArrayList<>();
            ArrayList<Genre> genres = Java_JDBC.getAllGenres(con);
            ArrayList<Theme> themes = Java_JDBC.getAllThemes(con);
            ArrayList<Format> formats = Java_JDBC.getAllFormats(con);
            ArrayList<Author> authors = Java_JDBC.getAllAuthors(con);
          

// Iterating through all genres, themes, formats, and authors
            for (Genre genre : genres) {
                
                if (!inclusiveGenres.contains(genre)) {
                    exclusiveGenres.add(genre);
                }
            }

            for (Theme theme : themes) {
                if (!inclusiveThemes.contains(theme)) {
                    exclusiveThemes.add(theme);
                }
            }

            for (Format format : formats) {
                if (!inclusiveFormats.contains(format)) {
                    exclusiveFormats.add(format);
                }
            }

            for (Author author : authors) {
                if (!inclusiveAuthors.contains(author)) {
                    exclusiveAuthors.add(author);
                }
            }

request.setAttribute("exclusiveGenres", inclusiveGenres);
request.setAttribute("exclusiveThemes", inclusiveThemes);
request.setAttribute("exclusiveFormats", inclusiveFormats);
request.setAttribute("exclusiveAuthors", inclusiveAuthors);
// Adding inclusive lists as attributes to the request
request.setAttribute("inclusiveGenres", exclusiveGenres);
request.setAttribute("inclusiveThemes", exclusiveThemes);
request.setAttribute("inclusiveFormats", exclusiveFormats);
request.setAttribute("inclusiveAuthors", exclusiveAuthors);

request.setAttribute("novel", novel);

            request.getRequestDispatcher("EditNovelTypeAuthor.jsp").forward(request, response);
        } catch (SQLException ex) {
            try {
                processRequest(request, response);
            } catch (Exception ex1) {
                Logger.getLogger(DeleteChapter.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            try {
                processRequest(request, response);
            } catch (Exception ex1) {
                Logger.getLogger(DeleteChapter.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
        try {
            java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            String novelId = request.getParameter("novelId");
            String[] inclusiveFormats = request.getParameterValues("inclusiveFormat");
            String[] inclusiveGenres = request.getParameterValues("inclusiveGenre");
            String[] inclusiveThemes = request.getParameterValues("inclusiveTheme");
            String[] inclusiveAuthors = request.getParameterValues("inclusiveAuthor");
            String[] exclusiveFormats = request.getParameterValues("exclusiveFormat");
            String[] exclusiveGenres = request.getParameterValues("exclusiveGenre");
            String[] exclusiveThemes = request.getParameterValues("exclusiveTheme");
            String[] exclusiveAuthors = request.getParameterValues("exclusiveAuthor");
            ArrayList<Genre> genres = Java_JDBC.getAllGenres(con);
            ArrayList<Theme> themes = Java_JDBC.getAllThemes(con);
            ArrayList<Format> formats = Java_JDBC.getAllFormats(con);
            ArrayList<Author> authors = Java_JDBC.getAllAuthors(con);
            ArrayList<Format> inclusiveFormatList = new ArrayList<>();
            ArrayList<Genre> inclusiveGenreList = new ArrayList<>();
            ArrayList<Theme> inclusiveThemeList = new ArrayList<>();
            ArrayList<Author> inclusiveAuthorList = new ArrayList<>();
            ArrayList<Format> exclusiveFormatList = new ArrayList<>();
            ArrayList<Genre> exclusiveGenreList = new ArrayList<>();
            ArrayList<Theme> exclusiveThemeList = new ArrayList<>();
            ArrayList<Author> exclusiveAuthorList = new ArrayList<>();
            if (inclusiveFormats != null) {
                for (String formatId : inclusiveFormats) {
                    for (Format format : formats) {
                        if (formatId.equalsIgnoreCase(format.getTypeId())) {
                            inclusiveFormatList.add(format);
                        }
                    }
                }
            }

            // Process inclusive genres
            if (inclusiveGenres != null) {
                for (String genreId : inclusiveGenres) {
                    for (Genre genre : genres) {
                        if (genreId.equalsIgnoreCase(genre.getTypeId())) {
                            inclusiveGenreList.add(genre);
                        }
                    }
                }

            }
            // Process inclusive themes
            if (inclusiveThemes != null) {
                for (String themeId : inclusiveThemes) {
                    for (Theme theme : themes) {
                        if (themeId.equalsIgnoreCase(theme.getTypeId())) {
                            inclusiveThemeList.add(theme);

                        }
                    }
                }
            }

            // Process inclusive authors
            if (inclusiveAuthors != null) {
                for (String authorId : inclusiveAuthors) {
                    for (Author author : authors) {
                        if (authorId.equalsIgnoreCase(author.getAuthorId())) {
                            inclusiveAuthorList.add(author);

                        }
                    }
                }
            }
            if (exclusiveFormats != null) {

                for (String formatId : exclusiveFormats) {
                    for (Format format : formats) {
                        if (formatId.equalsIgnoreCase(format.getTypeId())) {
                            exclusiveFormatList.add(format);
                        }
                    }
                }
            }
            if (exclusiveGenres != null) {

                for (String genreId : exclusiveGenres) {
                    for (Genre genre : genres) {
                        if (genreId.equalsIgnoreCase(genre.getTypeId())) {
                            exclusiveGenreList.add(genre);
                        }
                    }
                }
            }
            if (exclusiveThemes != null) {
                // Process exclusive themes

                for (String themeId : exclusiveThemes) {
                    for (Theme theme : themes) {
                        if (themeId.equalsIgnoreCase(theme.getTypeId())) {
                            exclusiveThemeList.add(theme);

                        }
                    }
                }
            }
            if (exclusiveAuthors != null) {

                // Process exclusive authors
                for (String authorId : exclusiveAuthors) {
                    for (Author author : authors) {
                        if (authorId.equalsIgnoreCase(author.getAuthorId())) {
                            exclusiveAuthorList.add(author);

                        }
                    }
                }
            }
            
            Java_JDBC.addThemeGenreFormatAuthor(con, Java_JDBC.getNovelByNovelId(con, novelId), inclusiveGenreList, inclusiveThemeList, inclusiveAuthorList, inclusiveFormatList);

            Java_JDBC.deleteThemeGenreFormatAuthor(con, Java_JDBC.getNovelByNovelId(con, novelId), exclusiveGenreList, exclusiveThemeList, exclusiveAuthorList, exclusiveFormatList);
            request.setAttribute("novelId", Java_JDBC.getNovelByNovelId(con, novelId));
            request.getRequestDispatcher("NovelDetailsServlet").forward(request, response);
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
            Logger.getLogger(EditTypeAuthorServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
