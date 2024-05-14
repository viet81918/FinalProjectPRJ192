<%-- 
    Document   : novelDetail
    Created on : Mar 8, 2024, 10:18:11 AM
    Author     : LENOVO
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.Author" %>
<%@ page import="Model.ChapterList" %>
<%@ page import="Model.Admin" %>
<%@ page import="Model.Customer" %>

<%@ page import="Model.Format" %>
<%@ page import="Model.Theme" %>
<%@ page import="Model.Genre" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Novel Details</title>
</head>
 <style>
         body {
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
}
    </style>    
<body>
    <div style="display: flex;flex-direction: column;align-items: center;">
        <h1>List of Authors</h1>
        <div style="display: flex;flex-direction: row;padding: 20px;">
 
 
 <div style="padding: 20px">
   <h2>Name </h2>
    <% String name = (String) request.getAttribute("name"); %>
    <%= name %>
</div>
<div style="padding: 20px">
    <h2>Description</h2>
    <% String description = (String) request.getAttribute("description"); %>
    <%= description %>
    </div>
    <div style="padding: 20px">
      <%
        ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
        if (authors != null && !authors.isEmpty()) {
    %>
    </div>
        <div style="padding: 20px">
            <h2>Authors</h2>
            <%
                for (Author author : authors) {
            %>
                    <a href='authorDetailServlet?authorId=<%= author.getAuthorId() %>'><%= author.getAuthorName() %></a><br>
            <%
                }
            %>
    <%
        }
    %>
</div>

 </div>  
        <% 
            IllustrationList illustrationList = (IllustrationList) request.getAttribute("illustration");
            if (illustrationList != null) {
                for (Map.Entry<Novel, List<Illustration>> entry : illustrationList.getIllustrationsForNovel().entrySet()) {
                    List<Illustration> illustrations = entry.getValue();
                    for (Illustration illustration : illustrations) {
                        // Replace the local file system path with a relative path
                        String relativeImagePath = illustration.getIllutrastionLink().replace("E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\", "");
        %>
                        <div>
                            <img style="  width: 1000px; /* Độ rộng mong muốn của hình ảnh */
        height: auto; /* Chiều cao tự động điều chỉnh theo tỷ lệ */
        border: 10px solid #8B795E; /* Viền xung quanh hình ảnh */
        border-radius: 10px;" src="<%= relativeImagePath %>">
                        </div>
        <%              }
                }
            }
        %>
    
    <%
ArrayList<Format> formats = (ArrayList<Format>) request.getAttribute("formats");
        if (formats != null && !formats.isEmpty()) {
            out.println("<h3>Formats</h3>");
            for (Format format : formats) {
                out.println(format.getTypeOfFormat() + "<br>");
            }
        }

        // Print themes
        ArrayList<Theme> themes = (ArrayList<Theme>) request.getAttribute("themes");
        if (themes != null && !themes.isEmpty()) {
            out.println("<h3>Themes</h3>");
            for (Theme theme : themes) {
                out.println(theme.getTypeOfTheme() + "<br>");
            }
        }

        // Print genres
        ArrayList<Genre> genres = (ArrayList<Genre>) request.getAttribute("genres");
        if (genres != null && !genres.isEmpty()) {
            out.println("<h3>Genres</h3>");
            for (Genre genre : genres) {
                out.println(genre.getTypeOfGenre() + "<br>");
            }
        
    }
 Novel novel = (Novel) request.getAttribute("novel");
%>
<%
        ChapterList chapterList = (ChapterList) request.getAttribute("chapterList");
    if (chapterList != null) {
       
        List<Chapter> chapters = chapterList.getChaptersForNovel(novel);

        if (chapters != null && !chapters.isEmpty()) {
            int flag = 1; // Assuming initial flag value is 1

            %>
            <h3>Chapters for <%= novel.getNovelName() %></h3>
          
                <% for (Chapter chapter : chapters) {
                    String relativeChapterPath = chapter.getLinkContent()
                            .replace("E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\", "");
                %>
                <div><%= relativeChapterPath %></div>
              <form action="readNovelServlet" method="get">
    <input type="hidden" name="novelId" value="<%= novel.getNovelId() %>">
    <input type="hidden" name="flag" value="<%= flag %>">
    <input style="width: 150px;margin: 20px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #FF66CC,#66FFFF);" type="submit" value="Read Chapter">
</form>
      
                

                <% 
                    flag++;
                } %>
                
                
                
             <%
    // Retrieve HttpSession object from the request
    HttpSession session1 = request.getSession();

    // Try to retrieve the "account" attribute from the session and cast it to Customer
    Customer customer = null;
    Admin admin = null;
    try {
        customer = (Customer) session1.getAttribute("account");
    } catch (ClassCastException e) {
        try {
            admin = (Admin) session1.getAttribute("account");
        } catch (ClassCastException ex) {
            // Handle the case where the attribute cannot be cast to Admin
            // Log the exception or perform other error handling
            ex.printStackTrace(); // Example: print the stack trace
        }
    }

    if (customer != null) {
%>
        <form action="FollowNovelServlet" method="post">
            <input style="width: 150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="hidden" name="novelId" value="<%= novel.getNovelId() %>">
            <input style="width: 150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Follow" >
        </form>
        <form action="UnFollowNovelServlet" method="post">
            <input style="width: 150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="hidden" name="novelId" value="<%= novel.getNovelId() %>">
            <input style="width: 150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="UnFollow" >
        </form>
        <a href="FollowListServlet">Back to Follow List</a>
<%
    } else if (admin != null) {
%>
        <!-- Add admin-specific functionality here if needed -->
<% 
        } 
}
}

%>
<button style="width: 150px;margin: 20px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" onclick="window.location.href='Home.jsp'">Back to Home</button>
  </div>
</body>
</html>