package Controller;

import Model.Format;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddFormatServlet")
public class AddFormatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String typeOfFormat = request.getParameter("typeOfFormat");
        Format format = new Format(typeId, typeOfFormat);

        Java_JDBC jdbc = new Java_JDBC();
        boolean isSuccess = false;
        try {
            isSuccess = jdbc.addFormat(format);
        } catch (Exception ex) {
            Logger.getLogger(AddFormatServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isSuccess) {
            request.setAttribute("message", "Format added successfully");
        } else {
            request.setAttribute("message", "Failed to add format");
        }

        request.getRequestDispatcher("addFormat.jsp").forward(request, response);
    }
}
