package Controller;

import Model.Genre;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddGenreServlet")
public class AddGenreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String typeOfGenre = request.getParameter("typeOfGenre");
        Genre genre = new Genre(typeId, typeOfGenre);

        Java_JDBC jdbc = new Java_JDBC();
        boolean isSuccess = false;
        try {
            isSuccess = jdbc.addGenre(genre);
        } catch (Exception ex) {
            Logger.getLogger(AddGenreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isSuccess) {
            request.setAttribute("message", "Genre added successfully");
        } else {
            request.setAttribute("message", "Failed to add genre");
        }

        request.getRequestDispatcher("addGenre.jsp").forward(request, response);
    }
}

