<%-- 
    Document   : addIllustration
    Created on : Mar 15, 2024, 12:00:42 AM
    Author     : tuanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="Model.Novel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Illustration</title>
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
        <form action="AddIllustrationServlet" method="post" enctype="multipart/form-data">
            <label style="font-size:30px;padding-right: 200px; " for="novelId" for="novelId">Select a Novel:</label>
            <select style="width: 120px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink); " name="novelId">
                <% 
                ArrayList<Novel> novels = (ArrayList<Novel>) request.getAttribute("novels");
                if (novels !=null){
                for (Novel novel : novels) {
                %>
                <option value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
                <% }
                }%>
            </select>
            <br>
            <label style="font-size:30px;padding-right: 20px;" for="file">Choose file (JPG or PNG only):</label>
    <input type="file" id="file" name="file" accept=".jpg, .png" required><br><br>

            <input style="width: 150px;margin-left:170px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Upload">
        </form>
            </div>

    </body>
</html>
