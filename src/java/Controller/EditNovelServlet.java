package Controller;

import Model.Author;
import Model.Format;
import Model.Genre;
import Model.Novel;
import Model.Theme;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/EditNovelServlet")
public class EditNovelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            String novelId = request.getParameter("novelId");
            String novelLanguage = request.getParameter("novelLanguage");
            String novelName = request.getParameter("novelName");
            float profit = Float.parseFloat(request.getParameter("profit"));
            float licenseProfit = Float.parseFloat(request.getParameter("licenseProfit"));
            String publicationYearStr = request.getParameter("publicationYear");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     

            Date publicationYear = null;
            try {
                publicationYear = sdf.parse(publicationYearStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String publicationStatus = request.getParameter("publicationStatus");
            String description = request.getParameter("description");

            Novel novel = new Novel(novelId, novelLanguage, novelName, 0, 0, profit, licenseProfit, 0, 0, 0, 0, publicationYear, publicationStatus, description);
            
            Java_JDBC j = new Java_JDBC();
            boolean success = false;
            try {
                success = j.editNovel(novel);
                
            } catch (Exception ex) {
                Logger.getLogger(EditNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (success) {
                response.sendRedirect("Home.jsp");
            } else {
                // Nếu thêm không thành công, chuyển hướng về trang thất bại

            }
        } catch (Exception ex) {
            Logger.getLogger(EditNovelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu GET nếu cần
    }

}
