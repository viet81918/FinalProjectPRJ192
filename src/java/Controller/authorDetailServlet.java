package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Author;
import Model.AuthorList;
import Model.Novel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "authorDetailServlet", urlPatterns = {"/authorDetailServlet"})
public class authorDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String authorId = request.getParameter("authorId");
            Connection con = Java_JDBC.getConnectionWithSqlJdbc();

            // Retrieve author details based on author ID
            Author author = Java_JDBC.getAuthorById(con, authorId);

            // Retrieve novels associated with the author
            AuthorList authorList = Java_JDBC.getAuthorListByAuthorId(con, author);
            
            // Set author and novels as request attributes
            request.setAttribute("author", author);
            request.setAttribute("novels", authorList.getAuthorNovelMap().get(author));

            // Forward the request to the authorDetail.jsp
            request.getRequestDispatcher("authorDetail.jsp").forward(request, response);
        } catch (SQLException ex) {
            response.sendRedirect("Home.jsp");
        
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(authorDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(authorDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
