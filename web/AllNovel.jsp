<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Author" %>
<%@ page import="Model.Illustration" %>
<%@ page import="Model.IllustrationList" %>
<%@ page import="Controller.Java_JDBC" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Novels</title>
</head>
<style>
     body {
        background-image: linear-gradient(to bottom right, #B2DFEE, pink); /* Hình nền gradient */
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
    }
    .button-details {
        background-image: linear-gradient(to bottom right, #B2DFEE, #FFCCFF);
        margin-bottom: 20px;
 margin-left: 650px;
  border-radius: 10px;
  border: none;
  color: #990000;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  cursor: pointer;
}
</style>
<body>
    <h1 style="display: flex; justify-content: center">List of Novels</h1>
    
    <% 
        java.sql.Connection con = Java_JDBC.getConnectionWithSqlJdbc();
        List<Novel> novels = Java_JDBC.getAllNovels(con); // Replace with your actual method to retrieve novels
        novels.sort((novel1, novel2) -> novel1.getPublicationYear().compareTo(novel2.getPublicationYear()));
        
        // Iterate over the list of novels and print each novel's information
        for (Novel novel : novels) {
    %>
    <div >
       <div style="display: flex; flex-direction: row; justify-content: center; ">
           <div style="padding: 30px;">
               <h2 style="font-size: 20px;"><%= novel.getNovelName() %></h2>
               <p style="font-size: 17px;margin-top: 2px;">Description: <%= novel.getDescription() %></p>
            </div>
          
            <div style="padding: 30px;">
            <% 
                ArrayList<Author> authors = (ArrayList<Author>) Java_JDBC.getAuthorByNovel(con, novel);
                if (authors != null && !authors.isEmpty()) {
                    out.println("<h3>Authors</h3>");
                    for (Author author : authors) {
                        out.println("<p>" + author.getAuthorName() + "</p>");
                    }
                }
            %>
            </div>
            </div>
            <button class="button-details" onclick="window.location.href='NovelDetailsServlet?novelId=<%= novel.getNovelId() %>'">View Details</button>
            <div style="display: flex;
                 justify-content: center;
                 ">
                <!-- hinh -->
            <!-- Display illustrations for the current novel -->
            <% 
                IllustrationList illustrationList = Java_JDBC.getIllustrationListByNovel(con, novel);
                if (illustrationList != null) {
                    for (Map.Entry<Novel, List<Illustration>> entry : illustrationList.getIllustrationsForNovel().entrySet()) {
                        List<Illustration> illustrations = entry.getValue();
                        for (Illustration illustration : illustrations) {
                            // Replace the local file system path with a relative path
                            String relativeImagePath = illustration.getIllutrastionLink()
                                    .replace("E:\\FPT UNI\\HoaiNTT40-SP24-PRJ301-Web-based Java Applications\\Final_Project_PRJ301\\web\\", "");
            %>
            
            <!-- Add hyperlink to each illustration -->
            <a href="NovelDetailsServlet?novelId=<%= novel.getNovelId() %>">
                <img style="width: 900px;
                     display: flex;
                     justify-content: center;
        height: auto; 
        border: 10px solid #8B795E;
        border-radius: 10px;" src="<%= relativeImagePath %>">
            </a>
            <%                  
                            // Break after displaying the first illustration
                            break;
                        }
                    }
                }
            %>
            
            <!-- Add a hyperlink to novelDetail.jsp with novelId as a parameter -->
            </div>
          
        </div>
    <%
        }
    %>
    <button style="
            border-radius: 10px;
            background-image:linear-gradient(to bottom right, #FF99FF,#660000);
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  font-size: 19px;
  cursor: pointer;" onclick="window.location.href='Home.jsp'">Back to Home</button>
</body>
</html>
