package Controller; 
       
import Model.Author;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddAuthorNameServlet")
public class AddAuthorNameServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorId = request.getParameter("authorId");
        String authorName = request.getParameter("authorName");
        String authorDescription = request.getParameter("authorDescription");
        Author a = new Author(authorId, authorName, authorDescription);
        Java_JDBC j = new Java_JDBC();
        boolean check = false;
        try {
            check = j.addAuthor(a);
        } catch (Exception ex) {
            Logger.getLogger(AddAuthorNameServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (check){
            request.setAttribute("mes", "Successfully");
        } else {
            request.setAttribute("mes", "Failed");
        }
        
        // Forward to the JSP page
        request.getRequestDispatcher("addAuthorName.jsp").forward(request, response);
    }
}


