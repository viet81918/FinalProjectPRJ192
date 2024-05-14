<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Results</title>
    <style>
        body {
            background-image: linear-gradient(to bottom right, #B2DFEE, pink);
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }
        
        .card {
            background-color: #fff;
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            width: 40%;
            margin-bottom: 20px;
        }
        
        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
        }
        
        .container {
            padding: 16px;
        }
        
        h4 {
            margin: 0;
            color: #333;
        }
        
        p {
            margin: 0;
            color: #666;
        }
        
        a {
            text-decoration: none;
            color: #007bff;
        }
        
        a:hover {
            text-decoration: underline;
        }
        
        .no-results {
            color: #333;
            text-align: center;
        }
    </style>
</head>
<body>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("resultSet");
        if (rs != null && rs.next()) {
            do {
    %>
                <div class="card">
                    <div class="container">
                        <h4><b><%= rs.getString("NovelName") %></b></h4>
                        <p><a href="NovelDetailsServlet?novelId=<%= rs.getString("NovelID") %>">View Details</a></p>
                    </div>
                </div>
    <%
            } while (rs.next());
        } else {
    %>
            <p class="no-results">No results found.</p>
    <%
        }
    %>
</body>
</html>