<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.Author" %>
<%@ page import="Model.ChapterList" %>
<%@ page import="Model.Format" %>
<%@ page import="Model.Theme" %>
<%@ page import="Model.Genre" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Novel</title>
        <link rel="stylesheet" type="text/css" href="css/addNovel.css">
    </head>
    <style>
            body {
                display: flex;
                flex-direction:column;
                justify-content: center;
                align-items: center;
                margin: 0;
                padding: 0;
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  align-items: center; /* Thêm thuộc tính này để căn giữa nội dung theo chiều dọc */
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
            }
        </style>
    <body>
        <h1 style="font-size:50px">Add Novel</h1>
        <c:if test="${not empty successMessage}">
            <div style="color: green; font-weight: bold; text-align: center;">
                ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div style="color: red; font-weight: bold; text-align: center;">
                ${errorMessage}
            </div>
        </c:if>
        <%
            java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
            ArrayList<Genre> genres =Java_JDBC.getAllGenres(con) ;
            ArrayList<Theme> themes =Java_JDBC.getAllThemes(con);
            ArrayList<Format> formats =Java_JDBC.getAllFormats(con);
            ArrayList<Author> authors =Java_JDBC.getAllAuthors(con);
        %>
        <div>
            <form action="AddNovelServlet" method="post" onsubmit="return validateForm()">
                <label style="font-size: 20px" for="novelLanguage">Language:</label><br>
                <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" id="novelLanguage" name="novelLanguage" required><br>

                <label style="font-size: 20px" for="novelName">Name:</label><br>
                <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" id="novelName" name="novelName" required><br>

         
                <label style="font-size: 20px" for="licenseProfit">License fee :</label><br>
                <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" id="licenseProfit" name="licenseProfit" required><br>



                <label style="font-size: 20px" for="publicationYear">Publication Year (yyyy-MM-dd):</label><br>
                <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" id="publicationYear" name="publicationYear" placeholder="yyyy-MM-dd" required><br>

                <label style="font-size: 20px" for="publicationStatus">Publication Status:</label><br>
                <select style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="publicationStatus" name="publicationStatus" required>
                    <option value="">Choose status...</option>
                    <option value="Ongoing">Ongoing</option>
                    <option value="Completed">Completed</option>
                    <option value="Hiatus">Hiatus</option>
                    <option value="Cancelled">Cancelled</option>
                </select><br>

                <label style="font-size: 20px" for="description">Description:</label><br>
                <textarea style="height:30px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="description" name="description" required></textarea><br>
                <label style="font-size: 20px" for="genre">Genre:</label><br>
<select style="width:160px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="genre" name="genre" multiple required>
    <% for (Genre genre : genres) { %>
    <option value="<%= genre.getTypeId() %>"><%= genre.getTypeOfGenre() %></option>
    <% } %>
</select><br>

<label style="font-size: 20px" for="format">Format:</label><br>
<select style=" width:160px;height:60px;border-radius:10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="format" name="format" multiple required>
    <% for (Format format : formats) { %>
    <option value="<%= format.getTypeId() %>"><%= format.getTypeOfFormat() %></option>
    <% } %>
</select><br>

<label style="font-size: 20px" for="theme">Theme:</label><br>
<select style="width:160px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="theme" name="theme" multiple required>
    <% for (Theme theme : themes) { %>
    <option value="<%= theme.getTypeId() %>"><%= theme.getTypeOfTheme() %></option>
    <% } %>
</select><br>
<label style="font-size: 20px" for="Author">Author</label><br>
<select style="width:160px;height:60px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" id="Author" name="Author" multiple required>
    <% for (Author a : authors) { %>
    <option value="<%= a.getAuthorId() %>"><%= a.getAuthorName() %></option>
    <% } %>
</select><br>

                <input style="width:160px;height:40px;border-radius: 10px;padding-left:30px;background-image:linear-gradient(to bottom right, #FF3300,pink);"  type="submit" value="Submit">       
            </form>
        </div>
        <script>
            function validateForm() {
                var novelLanguage = document.getElementById("novelLanguage").value;
                var novelName = document.getElementById("novelName").value;
               
                var licenseProfit = document.getElementById("licenseProfit").value;

                var publicationYear = document.getElementById("publicationYear").value;
                var publicationStatus = document.getElementById("publicationStatus").value;
                var description = document.getElementById("description").value;

                var errorMessage = "";

                if (novelLanguage.trim() === "") {
                    errorMessage += "Please enter Language\n";
                }

                if (novelName.trim() === "") {
                    errorMessage += "Please enter Name\n";
                }


           

                if (isNaN(profit) || profit.trim() === "") {
                    errorMessage += "Profit must be a numeric value\n";
                }

                if (isNaN(licenseProfit) || licenseProfit.trim() === "") {
                    errorMessage += "License Profit must be a numeric value\n";
                }


                // Validate publication year format
                var yearRegex = /^\d{4}-\d{2}-\d{2}$/;
                if (!yearRegex.test(publicationYear)) {
                    errorMessage += "Invalid Publication Year format. Please use yyyy-MM-dd\n";
                }

                if (publicationStatus.trim() === "") {
                    errorMessage += "Please enter Publication Status\n";
                }

                if (description.trim() === "") {
                    errorMessage += "Please enter Description\n";
                }

                if (errorMessage !== "") {
                    alert(errorMessage);
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
