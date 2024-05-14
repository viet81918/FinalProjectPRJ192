<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Admin" %>
<%@ page import="Model.Customer" %>
<%@ page import="Model.Users" %>
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
<%@ page import="java.util.Random" %>

<%
    // Check if a valid user is logged in
    if (session.getAttribute("account") == null) {
        // Redirect to login page if not logged in
        response.sendRedirect("Login.jsp");
        return; // Exit the JSP execution
    }
    java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
    List<Novel> novels = Java_JDBC.getAllNovels(con);

    // Select a random novel from the list
    Random random = new Random();
    Novel randomNovel = novels.get(random.nextInt(novels.size()));

    // Retrieve illustration list for the random novel
   
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <style>
        body {
            background-image: linear-gradient(to bottom right, #B2DFEE, pink); /* Hình nền gradient */
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
        }
        .vertical-menu {
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-left: 10px;
            margin-right: 80px;
        }


        .vertical-menu button {
            margin-bottom: 15px;
            font-size: 18px;
            display: flex;
            justify-content: center;
            width: 160px;
            padding: 4px;
            border: 3px solid #87CEFA;
            border-radius: 10px;
            .no-underline {
                text-decoration: none;
            }
        }
        .container {
            display: flex;
            flex-direction: row;
        }
        .search-form {
            flex-direction: row-reverse;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .search-form label {
            font-size: 20px;
            margin-left:40px;
            font-weight: bold;
        }

        .search-form select,
        .search-form input[type="text"],
        .search-form button {
            border-radius: 10px;
            padding: 5px;
            font-size: 14px;
        }



        .account-link {
            margin-left: 10px;
            font-size: 14px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
    <body>

        <div class="search-form">

            <form action="SearchServlet" method="get">
                <label for="searchType">Search by:</label>
                <select id="searchType" name="searchType">
                    <option value="NovelName">Novel Name</option>
                    <option value="NovelFormat">Novel Format</option>
                    <option value="NovelTheme">Novel Theme</option>
                    <option value="NovelGenre">Novel Genre</option>
                </select>
                <label for="query">Search term:</label>
                <input type="text" id="query" name="query">
                <input style="background-color: #007bff;
                       font-size: 18px;
                       color: #fff;
                       cursor: pointer;
                       border-radius: 5px;" type="submit" value="Search">
                <button style="color:red;background-color: black;border-radius: 50%;width: 35px;height:35px;margin: 10px;" type="button" onclick="window.location.href = 'InformationCus'" class="account-link-button">A</button>

            </form>
        </div>

        <div class="container">


            <div class="vertical-menu">


<%
try {


    // Try to retrieve the "account" attribute from the session and cast it to Customer
    Customer customer = null;
    Admin admin = null;
    try {
        customer = (Customer) session.getAttribute("account");
    } catch (ClassCastException e) {
        try {
            admin = (Admin) session.getAttribute("account");
        } catch (ClassCastException ex) {
            ex.printStackTrace(); // Example: print the stack trace
        }
    }

    if (customer != null) {
%>
    <button onclick="window.location.href = 'logoutServ';" class="no-underline">LOG OUT</button>
    <button onclick="window.location.href = 'AllNovel.jsp';" class="no-underline">All Novel</button>
    
  <button onclick="window.location.href = 'LastestUpdateServlet';" class="no-underline">Latest Update</button>
    <button onclick="window.location.href = 'FollowListServlet';" class="no-underline">Bookshelf</button>
    
<%
    } else if (admin != null) {
%>
  <button onclick="window.location.href = 'logoutServ';" class="no-underline">LOG OUT</button>
                <button onclick="window.location.href = 'AllNovel.jsp';" class="no-underline">All Novel</button>
                     <button onclick="window.location.href = 'FollowListServlet';" class="no-underline">Bookshelf</button>
                <button onclick="window.location.href = 'AddTypeAuthor.jsp';" class="no-underline">Add category and author</button>
                <button onclick="window.location.href = 'addNovel.jsp';" class="no-underline">Add Novel</button>
                <button onclick="window.location.href = 'AddIllustrationServlet';" class="no-underline">Add Illustrations</button>
                <button onclick="window.location.href = 'UploadChapterServlet';" class="no-underline">Add Chapter</button>
                <button onclick="window.location.href = 'EditNovel.jsp';" class="no-underline">Edit Novel</button>
                <button onclick="window.location.href = 'EditNovelTypeAuthor.jsp';" class="no-underline">Edit category author</button>
                <button onclick="window.location.href = 'DeleteTypeAuthor.jsp';" class="no-underline">Delete category author</button>
                <button onclick="window.location.href = 'DeleteChapter';" class="no-underline">Delete Chapter</button>
                <button onclick="window.location.href = 'DeleteIllustration';" class="no-underline">Delete Illustration</button>
                <button onclick="window.location.href = 'DeleteNovelServlet';" class="no-underline">Delete Novel</button>
<%
    }
} catch (Exception e) {
    response.sendRedirect("Login.jsp");
}
%>


            </div> 
            <div style="display: flex; flex-direction: column;">
                <div style="display: flex;flex-direction: column;">
                    <span style="display: flex; justify-content: center;color: #CD853F;font-size: 70px">Hello ${sessionScope.account.name}</span>
                    <h2> Novel: <%= randomNovel.getNovelName() %></h2>
                    <h3>Illustrations:</h3>
                </div>
                <div>
                    <% IllustrationList illustrationList = Java_JDBC.getIllustrationListByNovel(con, randomNovel);
                        if (illustrationList != null) {
                            for (Map.Entry<Novel, List<Illustration>> entry : illustrationList.getIllustrationsForNovel().entrySet()) {
                                List<Illustration> illustrations = entry.getValue();
                                for (Illustration illustration : illustrations) {
                                    // Replace the local file system path with a relative path
                                    String relativeImagePath = illustration.getIllutrastionLink()
                                            .replace("E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\", "");
                    %>
                    <a href="NovelDetailsServlet?novelId=<%= randomNovel.getNovelId() %>">
                        <img style="  width: 1000px; /* Độ rộng mong muốn của hình ảnh */
                             height: auto; /* Chiều cao tự động điều chỉnh theo tỷ lệ */
                             border: 10px solid #8B795E; /* Viền xung quanh hình ảnh */
                             border-radius: 10px; /* Độ cong của góc hình ảnh */ " src="<%= relativeImagePath %>"></a>


                    <%              // Break after displaying the first illustration
                                    break;
                                }
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
