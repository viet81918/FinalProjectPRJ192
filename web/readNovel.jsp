<%-- 
    Document   : readNovel
    Created on : Mar 8, 2024, 5:15:16 PM
    Author     : LENOVO
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Chapter" %>
<%@ page import="Model.ChapterList" %>
<%@ page import="Model.Format" %>
<%@ page import="Model.Theme" %>
<%@ page import="Model.Genre" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>

<%@ page language="java"
contentType="text/html;
charset=utf-8"
pageEncoding="utf-8"
import="java.sql.*"
%><!DOCTYPE html>
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novel</title>
    </head>
     <style>
         body {
            
    font-family: "Times New Roman", Times, serif;
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}
    </style>
    <body>
        
        
<% 
    Novel novel = (Novel) request.getAttribute("novel"); 
    int flag = ((int) request.getAttribute("flag") + 1); 
    int numberOfChapter = ((int) request.getAttribute("numberOfChapter")); 
    
    Chapter chapter = (Chapter) request.getAttribute("chapter"); 
    String content = chapter.getContent(); 
    content = content.replaceAll("\n", "<br>");
%>

<%= content %>

<form id="readChapterForm" action="readNovelServlet" method="get">
    <input type="hidden" name="novelId" value="<%= novel.getNovelId() %>">
    <input type="hidden" id="flagInput" name="flag" value="<%= flag %>">
    
   <% if (flag < numberOfChapter) { %>
    <input style="width: 190px;height:40px;border-radius: 10px;background-image:linear-gradient(to bottom right, bisque                                             ,#FF99CC);" type="button" value="Read the next Chapter" onclick="increaseFlagAndSubmit()">
<% } else if (flag == numberOfChapter) { %>
    <h5>No more Chapter</h5>
<% } else { %>
    <h5>Invalid Chapter</h5>
<% } %>

<% if (flag > 1) { %>
    <input style=" width: 190px;height:40px;border-radius: 10px;background-image:linear-gradient(to bottom right, #FF3300,#00EE00);" type="button" value="Read the previous Chapter" onclick="decreaseFlagAndSubmit()">
<% } %>

</form>

<button style="margin-top:20px;width: 190px;height:40px;border-radius: 10px;background-image:linear-gradient(to bottom right, #33CCFF,#FF99CC);" onclick="window.location.href='NovelDetailsServlet?novelId=<%= novel.getNovelId() %>'">novelDetail</button>
<script>
    function increaseFlagAndSubmit() {
        var flagInput = document.getElementById('flagInput');
        flagInput.value = parseInt(flagInput.value) + 1;
        document.getElementById('readChapterForm').submit();
    }
    
    function decreaseFlagAndSubmit() {
        var flagInput = document.getElementById('flagInput');
        flagInput.value = parseInt(flagInput.value) - 1;
        document.getElementById('readChapterForm').submit();
    }
</script>

    </body>
</html>