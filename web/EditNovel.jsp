<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Novel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.Author" %>
<%@ page import="Model.ChapterList" %>
<%@ page import="Model.Format" %>
<%@ page import="Model.Theme" %>
<%@ page import="Model.Genre" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Novel</title>
    <link rel="stylesheet" type="text/css" href="css/addNovel.css">
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
    
     <% 
         java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
         ArrayList<Novel> novels = Java_JDBC.getAllNovels(con); 
         ArrayList<Genre> genres =Java_JDBC.getAllGenres(con) ;
         ArrayList<Theme> themes =Java_JDBC.getAllThemes(con);
         ArrayList<Format> formats =Java_JDBC.getAllFormats(con);
            ArrayList<Author> authors =Java_JDBC.getAllAuthors(con);
%>
 <form action="EditNovelServlet" method="post">
    <h1>Edit Novel</h1>

    <!-- Novel selection -->
    <label style="font-size: 20px;" for="novelId">Select a Novel:</label>
    <select  style="width: 80px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" id="novelId" name="novelId">
        <% for (Novel novel : novels) { %>
            <option value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
        <% } %>
    </select><br>

    <!-- Language -->
    <label style="font-size: 20px;" for="novelLanguage">Language:</label><br>
    <input  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" type="text" id="novelLanguage" name="novelLanguage" required><br>

    <!-- Name -->
    <label style="font-size: 20px;" for="novelName">Name:</label><br>
    <input  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" type="text" id="novelName" name="novelName" required><br>

    <!-- Profit -->
    <label style="font-size: 20px;" for="profit">Profit:</label><br>
    <input  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" type="text" id="profit" name="profit" required><br>

    <!-- License fee -->
    <label style="font-size: 20px;" for="licenseProfit">License fee:</label><br>
    <input  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" type="text" id="licenseProfit" name="licenseProfit" required><br>

    <!-- Publication Year -->
    <label style="font-size:15px;" for="publicationYear">Publication Year (yyyy-MM-dd):</label><br>
    <input  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" type="text" id="publicationYear" name="publicationYear" placeholder="yyyy-MM-dd" required><br>

    <!-- Publication Status -->
    <label style="font-size: 20px;" for="publicationStatus">Publication Status:</label><br>
    <select  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" id="publicationStatus" name="publicationStatus" required>
        <option value="Ongoing">Ongoing</option>
        <option value="Completed">Completed</option>
        <option value="Hiatus">Hiatus</option>
        <option value="Cancelled">Cancelled</option>
    </select><br>

    <!-- Description -->
    <label style="font-size: 20px;"  for="description">Description:</label><br>
    <textarea  style="width: 200px;height:30px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#FFC0CB);" id="description" name="description" required></textarea><br>

    <!-- Submit Button -->
    <input  style="width: 150px;margin-left:20px;height:50px;border-radius: 10px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Upload Chapter">
</form>
 

    
</body>
</html>
