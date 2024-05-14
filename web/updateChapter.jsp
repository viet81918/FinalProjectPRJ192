<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Novel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="Controller.Java_JDBC" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Chapter</title>
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
    <form action="UploadChapterServlet" method="post" enctype="multipart/form-data">
        <label style="font-size:30px;padding-right: 20px; " for="novelId" for="novelId">Select a Novel:</label>
        <select style="width: 120px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink); " name="novelId">
            <%
              

        ArrayList<Novel> novels = ( ArrayList<Novel>)request.getAttribute("novels");
               
                    for (Novel novel : novels) {
            %>
            <option  value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
            <% }
                
            %>
        </select>
        <br>
        <input type="file" name="chapterFile">
        <input style="width:150px;margin-left:60px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Upload Chapter">
    </form>
        </div>
</body>
</html>
