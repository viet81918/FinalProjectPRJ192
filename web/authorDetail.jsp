<%@ page import="Model.Author" %>
<%@ page import="Model.Novel" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.AuthorList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Author Detail</title>
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
    <div style="display: flex;flex-direction: column;align-items: center; ">
    <h1>Author Detail</h1>
    <%
        Author author = (Author) request.getAttribute("author");
        List<Novel> novels = (List<Novel>) request.getAttribute("novels");

        if (author != null && novels != null) {
    %>
    <p><strong>Author Name:</strong> <%= author.getAuthorName() %></p>
    <p><strong>Author Description </strong> <%= author.getAuthorDescription() %></p>

    <h2>Novels by <%= author.getAuthorName() %></h2>
    <div>
        <% for (Novel novel : novels) { %>
            <div>
                
                <a href="NovelDetailsServlet?novelId=<%= novel.getNovelId() %>"><%= novel.getNovelName() %></a>
            </div>
        <% } %>
    </div>
    <% } else { %>
        <p>No author or novels found.</p>
    <% } %>
       <button style="width: 150px;margin: 20px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" onclick="window.location.href='Home.jsp'">Back to Home</button>
       </div>
</body>
</html>
