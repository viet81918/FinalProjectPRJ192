<%-- 
    Document   : listSave
    Created on : Mar 8, 2024, 6:40:53 PM
    Author     : ASUS
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Admin" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.ChapterList" %>
<%@ page import="Model.AddList" %>
<%@ page import="Model.Format" %>
<%@ page import="Model.Theme" %>
<%@ page import="Model.Genre" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>


 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Follow List</title>
</head>
 <style>
         body {
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  display: flex;
  justify-content: center;
      align-items: center; /* Thêm thuộc tính này để căn giữa nội dung theo chiều dọc */
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
}
    </style>
<body>
    <div>
    <h1 style="font-size: 50px;">Follow List</h1>
    <div>
    <% 
        AddList addList = (AddList) request.getAttribute("addList");
        Map<Admin, List<Novel>> adminNovelMap = addList.getAdminNovelMap();
        
        Set<Entry<Admin, List<Novel>>> entrySet = adminNovelMap.entrySet();
        for (Entry<Admin, List<Novel>> entry : entrySet) {
            Admin admin = entry.getKey();
            List<Novel> novels = entry.getValue();
            
            // Print admin details
            out.println("<h2>Admin: " + admin.getName() + "</h2>");
            
            // Iterate over the novels for the current admin
for (Novel novel : novels) {
    // Generate button to navigate to novelDetailServlet for each novel
    out.println("<form action='NovelDetailsServlet' method='get'>");
    out.println("<input type='hidden' name='novelId' value='" + novel.getNovelId() + "'>");
    out.println("<button type='submit'>" + novel.getNovelName() + "</button>");
    out.println("</form><br>");
}

        }
    %>
    </div>
    <button style="width: 150px;margin-left:60px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" onclick="window.location.href='Home.jsp';" class="no-underline">Back to Home</button>
    </div>
    </body>
</html>
