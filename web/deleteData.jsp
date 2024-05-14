<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.Illustration" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Controller.Java_JDBC" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<form action="DeleteChapter" method="post">
    <label for="novelId">Select a Novel:</label>
    <select name="novelId">
        <%
            ArrayList<Novel> novels = (ArrayList<Novel>)request.getAttribute("novels");
            for (Novel novel : novels) {
        %>
        <option value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
        <%
            }
        %>
    </select>
    <br><br>
     <%
                    List<Chapter > chapters = (List<Chapter > )request.getAttribute("chapters"); 
                if (chapters != null){
                %>
                <form action="DeleteChapter" method="post" >
        <label for="ChapterId">Select a Chapter</label>
        <select name="ChapterId">
            <%
              
               
                    for (Chapter chapter : chapters) {
            %>
            <option value="<%= chapter.getChapterId() %>"><%= chapter.getChapterName() %></option>
            <% }
                
            %>
        </select>
    <br><br>
     <label for="IllustrationId">Select a Illustration</label>
        <select name="IllustrationId">
            <%
              
               
                    for (Illustration illu : illuList) {
            %>
            <option value="<%= illu.getIllustrationId() %>"><%= illu.getIlluName() %></option>
            <% }
                
            %>
        </select>
      
    <br><br>
    <input type="submit" value="Delete">
</form>
</body>
</html>
