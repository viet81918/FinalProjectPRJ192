package Controller;

import Model.Novel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(name="SearchServlet", urlPatterns={"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String query = request.getParameter("query");
            String searchType = request.getParameter("searchType");
            try (Connection con = Java_JDBC.getConnectionWithSqlJdbc(); Statement st = con.createStatement()) {
                String sql;
                if ("NovelName".equals(searchType)) {
                    sql = "SELECT * FROM Novel WHERE NovelName LIKE '%" + query + "%'";
                } else if ("NovelFormat".equals(searchType)) {
                    sql = "SELECT Novel.* FROM Novel JOIN Novel_Format ON Novel.NovelID = Novel_Format.NovelID JOIN NovelFormat ON Novel_Format.FormatTypeID = NovelFormat.TypeID WHERE NovelFormat.TypeOfFormat LIKE '%" + query + "%'";

                } else if ("NovelTheme".equals(searchType)) {
                    sql = "SELECT Novel.* FROM Novel JOIN Novel_Theme ON Novel.NovelID = Novel_Theme.NovelID JOIN NovelTheme ON Novel_Theme.ThemeTypeID = NovelTheme.TypeID WHERE NovelTheme.TypeOfTheme LIKE '%" + query + "%'";
                }else if ("NovelGenre".equals(searchType)) {
                    sql = "SELECT Novel.* FROM Novel JOIN Novel_Genre ON Novel.NovelID = Novel_Genre.NovelID JOIN NovelGenre ON Novel_Genre.GenreTypeID = NovelGenre.TypeID WHERE NovelGenre.TypeOfGenre LIKE '%" + query + "%'";
                } else {
                    throw new IllegalArgumentException("Invalid search type: " + searchType);
                }
                ResultSet rs = st.executeQuery(sql);
                request.setAttribute("resultSet", rs);
                request.getRequestDispatcher("/searchBox.jsp").forward(request, response);
            } catch (Exception ex) {
                out.println("<p>Error: " + ex.getMessage() + "</p>");
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
