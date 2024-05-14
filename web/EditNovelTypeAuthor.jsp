<%-- 
    Document   : EditNovelTypeAuthor
    Created on : Mar 14, 2024, 8:22:15 AM
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
        <title>Edit Novel Type Author</title>
    </head>
      <style>
            body {
                display: flex;
                flex-direction:column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                padding: 0;
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  background-repeat: no-repeat;
  align-items: center; /* Thêm thuộc tính này để căn giữa nội dung theo chiều dọc */
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
            }
        </style>
    <body>
          <% 
         java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
         ArrayList<Novel> novels = Java_JDBC.getAllNovels(con);
               ArrayList<Format> inclusiveFormats = ( ArrayList<Format>)request.getAttribute("inclusiveFormats");
            ArrayList<Genre> inclusiveGenres = ( ArrayList<Genre>)request.getAttribute("inclusiveGenres");
            ArrayList<Theme> inclusiveThemes =(ArrayList<Theme>) request.getAttribute("inclusiveThemes");
            ArrayList<Author> inclusiveAuthors = (ArrayList<Author>)request.getAttribute("inclusiveAuthors");
              ArrayList<Genre> exclusiveGenres =( ArrayList<Genre>) request.getAttribute("exclusiveGenres");
            ArrayList<Theme> exclusiveThemes = (ArrayList<Theme>)request.getAttribute("exclusiveThemes");
            ArrayList<Format> exclusiveFormats =( ArrayList<Format>) request.getAttribute("exclusiveFormats");
            ArrayList<Author> exclusiveAuthors =(ArrayList<Author>) request.getAttribute("exclusiveAuthors");
          %>
          <h1>Edit Type and Author of Novel</h1>
        <form action="EditTypeAuthorServlet" method="get">
    <div style="margin-left: 100px">
        <!-- Novel selection -->
        <label for="novelId">Select a Novel:</label>
        <select style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="novelId" name="novelId">
            <% for (Novel novel : novels) { %>
                <option value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
            <% } %>
        </select><br>
        <input style="height:40px;border-radius: 10px;padding:10px;background-image:linear-gradient(to bottom right, #FF3300,pink);" type="submit" value="Submit">
    </div>
</form>

    <!-- Format (multi-choice) -->
 <form action="EditTypeAuthorServlet" method="post">
     
    <h1>Edit Type and Author of Novel</h1>
    <div style="margin-left: 100px">
        <!-- Format (multi-choice) -->
          <%  Novel novel = (Novel) request.getAttribute("novel");
if (novel != null) { %>
            <input type="hidden" id="novelId" name="novelId" value="<%= novel.getNovelId() %>">
        <% } else { %>
            <input type="hidden" id="novelId" name="novelId" value="">
        <% } %>
        <% if (inclusiveFormats != null && inclusiveFormats.size() > 0) { %>
        <label for="inclusiveFormat">Inclusive Format:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="inclusiveFormat" name="inclusiveFormat" multiple>
            <% for (Format format : inclusiveFormats) { %>
            <option value="<%= format.getTypeId() %>"><%= format.getTypeOfFormat() %></option>
            <% } %>
        </select><br>
        <% } %>

        <% if (exclusiveFormats != null && exclusiveFormats.size() > 0) { %>
        <label for="exclusiveFormat">Exclusive Format:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="exclusiveFormat" name="exclusiveFormat" multiple>
            <% for (Format format : exclusiveFormats) { %>
            <option value="<%= format.getTypeId() %>"><%= format.getTypeOfFormat() %></option>
            <% } %>
        </select><br>
        <% } %>

        <!-- Theme (multi-choice) -->
        <% if (inclusiveThemes != null && inclusiveThemes.size() > 0) { %>
        <label for="inclusiveTheme">Inclusive Theme:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="inclusiveTheme" name="inclusiveTheme" multiple>
            <% for (Theme theme : inclusiveThemes) { %>
            <option value="<%= theme.getTypeId() %>"><%= theme.getTypeOfTheme() %></option>
            <% } %>
        </select><br>
        <% } %>

        <% if (exclusiveThemes != null && exclusiveThemes.size() > 0) { %>
        <label for="exclusiveTheme">Exclusive Theme:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="exclusiveTheme" name="exclusiveTheme" multiple>
            <% for (Theme theme : exclusiveThemes) { %>
            <option value="<%= theme.getTypeId() %>"><%= theme.getTypeOfTheme() %></option>
            <% } %>
        </select><br>
        <% } %>

        <!-- Genre (multi-choice) -->
        <% if (inclusiveGenres != null && inclusiveGenres.size() > 0) { %>
        <label for="inclusiveGenre">Inclusive Genre:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="inclusiveGenre" name="inclusiveGenre" multiple>
            <% for (Genre genre : inclusiveGenres) { %>
            <option value="<%= genre.getTypeId() %>"><%= genre.getTypeOfGenre() %></option>
            <% } %>
        </select><br>
        <% } %>

        <% if (exclusiveGenres != null && exclusiveGenres.size() > 0) { %>
        <label for="exclusiveGenre">Exclusive Genre:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="exclusiveGenre" name="exclusiveGenre" multiple>
            <% for (Genre genre : exclusiveGenres) { %>
            <option value="<%= genre.getTypeId() %>"><%= genre.getTypeOfGenre() %></option>
            <% } %>
        </select><br>
        <% } %>

        <!-- Author (multi-choice) -->
        <% if (inclusiveAuthors != null && inclusiveAuthors.size() > 0) { %>
        <label for="inclusiveAuthor">Inclusive Author:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="inclusiveAuthor" name="inclusiveAuthor" multiple>
            <% for (Author author : inclusiveAuthors) { %>
            <option value="<%= author.getAuthorId() %>"><%= author.getAuthorName() %></option>
            <% } %>
        </select><br>
        <% } %>

        <% if (exclusiveAuthors != null && exclusiveAuthors.size() > 0) { %>
        <label for="exclusiveAuthor">Exclusive Author:</label><br>
        <select style="width: 200px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="exclusiveAuthor" name="exclusiveAuthor" multiple>
            <% for (Author author : exclusiveAuthors) { %>
            <option value="<%= author.getAuthorId() %>"><%= author.getAuthorName() %></option>
            <% } %>
        </select><br>
        <% } %>

        <div style="padding-left:40px">
            <input style="height:40px;border-radius: 10px;padding:10px;background-image:linear-gradient(to bottom right, #FF3300,pink);" type="submit" value="Change">
        </div>
    </div>
</form>


    </body>
</html>
