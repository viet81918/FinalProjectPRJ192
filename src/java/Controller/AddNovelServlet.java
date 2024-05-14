
package Controller;
import Model.Admin;
import Model.Author;
import Model.Customer;
import Model.Format;
import Model.Genre;
import Model.Novel;
import Model.Theme;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddNovelServlet")
public class AddNovelServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
             HttpSession session = request.getSession();
    Admin admin = (Admin) session.getAttribute("account");
            String novelLanguage = request.getParameter("novelLanguage");
            String novelName = request.getParameter("novelName");
          
            float licenseProfit = Float.parseFloat(request.getParameter("licenseProfit"));
            
            String publicationYearStr = request.getParameter("publicationYear");
            Date publicationYear = null;
            try {
                publicationYear = new SimpleDateFormat("yyyy").parse(publicationYearStr);
            } catch (ParseException e) {
            }
            String publicationStatus = request.getParameter("publicationStatus");
            String description = request.getParameter("description");
            ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
            Novel novel = new Novel( novelLanguage, novelName,  licenseProfit,
                  publicationYear, publicationStatus, description,novels);
        String[] selectedFormats = request.getParameterValues("format");
        String[] selectedGenres = request.getParameterValues("genre");
        String[] selectedThemes = request.getParameterValues("theme");
        String[] selectedAtuhors = request.getParameterValues("Author");
        ArrayList<Genre> genres = Java_JDBC.getAllGenres(con);
        ArrayList<Theme> themes = Java_JDBC.getAllThemes(con);
        ArrayList<Format> formats = Java_JDBC.getAllFormats(con);
        ArrayList<Author> authors =Java_JDBC.getAllAuthors(con);
        ArrayList<Format> selectedFormatList = new ArrayList<>();
        ArrayList<Genre> selectedGenreList = new ArrayList<>();
        ArrayList<Theme> selectedThemeList = new ArrayList<>();
        ArrayList<Author> selectedAuthorList = new ArrayList<>();
        // Loop through selected format names and find matching Format objects
        for (String formatName : selectedFormats) {
            for (Format format : formats) {
                if (formatName.equalsIgnoreCase(format.getTypeId())) {
                    selectedFormatList.add(format);

                }
            }
        }

        // Loop through selected genre names and find matching Genre objects
        for (String genreName : selectedGenres) {
            for (Genre genre : genres) {
                if (genreName.equalsIgnoreCase(genre.getTypeId())) {
                    selectedGenreList.add(genre);
                  
                }
            }
        }

        // Loop through selected theme names and find matching Theme objects
        for (String themeName : selectedThemes) {
            for (Theme theme : themes) {
                if (themeName.equalsIgnoreCase(theme.getTypeId())) {
                    selectedThemeList.add(theme);
                 
                }
            }
        }
        for (String authorName : selectedAtuhors) {
            for (Author a : authors) {
                if (authorName.equalsIgnoreCase(a.getAuthorId())) {
                    selectedAuthorList.add(a);
                  
                }
            }
        }
        if(selectedThemeList == null || selectedGenreList == null || selectedFormatList == null ||selectedAuthorList== null ){
              try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Just read</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + selectedThemeList.get(0) + "</h1>");
                out.println("<h1>" + selectedGenreList.get(0) + "</h1>");
                out.println("<h1>" + selectedFormatList.get(0) + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }else {
        
        try {
            Java_JDBC.addNovelToDatabase(con,novel);
            Java_JDBC.addRelatedData (  con,novel,selectedFormatList, selectedGenreList, selectedThemeList,selectedAuthorList);
            Java_JDBC.addToAddList(con, admin, novel);
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
            Logger.getLogger(AddNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
            response.sendRedirect("Home.jsp");}
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
            Logger.getLogger(AddNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
