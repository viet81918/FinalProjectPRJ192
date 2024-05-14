import Controller.Java_JDBC;
import Model.Theme;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddThemeServlet")
public class AddThemeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String typeOfTheme = request.getParameter("typeOfTheme");
        Theme theme = new Theme(typeId, typeOfTheme);

        Java_JDBC jdbc = new Java_JDBC();
        boolean isSuccess = false;
        try {
            isSuccess = jdbc.addTheme(theme);
        } catch (Exception ex) {
            Logger.getLogger(AddThemeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isSuccess) {
            request.setAttribute("message", "Theme added successfully");
        } else {
            request.setAttribute("message", "Failed to add theme");
        }

        request.getRequestDispatcher("addTheme.jsp").forward(request, response);
    }
}
