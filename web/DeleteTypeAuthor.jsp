<%-- 
    Document   : DeleteTypeAuthor
    Created on : Mar 14, 2024, 7:08:39 PM
    Author     : LENOVO
--%>
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
<%@ page import="Model.Novel" %>

<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Type Author</title>
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
         ArrayList<Genre> genres =Java_JDBC.getAllGenres(con) ;
         ArrayList<Theme> themes =Java_JDBC.getAllThemes(con);
         ArrayList<Format> formats =Java_JDBC.getAllFormats(con);
            ArrayList<Author> authors =Java_JDBC.getAllAuthors(con);
%><form action="DeleteTypeAuthor" method="post">
    <div>
    <h1>Delete Type and Author of Novel</h1>
    <div style="padding-left:120px">
    <label style="font-size:30px;padding-right:50px; " for="novelId" for="deleteFormat">Delete Format:</label><br>
    <select style="padding-top:0; font-size:17px;width: 180px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="deleteFormat" name="deleteFormat" multiple >
        <% for (Format format : formats) { %>
            <option value="<%= format.getTypeId() %>"><%= format.getTypeOfFormat() %></option>
        <% } %>
    </select><br>

   

   
    <label style="font-size:30px;padding-right:50px; " for="novelId" for="deleteTheme">Delete Theme:</label><br>
    <select style=" padding-top:0;font-size:17px;width: 180px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="deleteTheme" name="deleteTheme" multiple >
        <% for (Theme theme : themes) { %>
            <option value="<%= theme.getTypeId() %>"><%= theme.getTypeOfTheme() %></option>
        <% } %>
    </select><br>

   

    <label style="font-size:30px;padding-right:50px; " for="novelId" for="deleteGenre">Delete Genre:</label><br>
    <select style="font-size:17px;width: 180px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="deleteGenre" name="deleteGenre" multiple >
        <% for (Genre genre : genres) { %>
            <option value="<%= genre.getTypeId() %>"><%= genre.getTypeOfGenre() %></option>
        <% } %>
    </select><br>

    <label style="font-size:30px;padding-right:50px; " for="novelId" for="deleteAuthor">Delete Author:</label><br>
    <select style="font-size:17px;width: 180px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="deleteAuthor" name="deleteAuthor" multiple >
        <% for (Author author : authors) { %>
            <option value="<%= author.getAuthorId() %>"><%= author.getAuthorName() %></option>
        <% } %>
    </select><br>

   

    <!-- Submit Button -->
    <input style="width: 150px;margin: 20px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Delete">
 </div>
    </div>
    </form>
    </body>
</html>
