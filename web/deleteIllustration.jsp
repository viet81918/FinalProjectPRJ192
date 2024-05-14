<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Novel" %>
<%@ page import="Model.Illustration" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="Controller.Java_JDBC" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Illustration</title>
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
    }
    </style>
    <body>
        <div>
         <form action="DeleteIllustration" method="get" >
        <label  style="font-size:30px;padding-right:50px; " for="novelId">Select a Novel:</label>
        <select  style="width: 120px;height:50px;border-radius: 10px;padding-left:30px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" name="novelId">
            <%
              

        ArrayList<Novel> novels = ( ArrayList<Novel>)request.getAttribute("novels");
               
                    for (Novel novel : novels) {
            %>
            <option value="<%= novel.getNovelId() %>"><%= novel.getNovelName() %></option>
            <% }
                
            %>
        </select>
        <br>
        <input  style="width: 150px;margin-left:60px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Choose Novel ">

    </form>
                <%
                    List<Illustration> illuList = (List<Illustration> )request.getAttribute("illuList"); 
                if (illuList != null){
                %>
                <form action="DeleteIllustration" method="post" >
        <label  style="font-size:30px; " for="IllustrationId">Select a Illustration</label>
        <select   style="width: 120px;height:50px;border-radius: 10px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" name="IllustrationId">
            <%
              
               
                    for (Illustration illu : illuList) {
            %>
            <option value="<%= illu.getIllustrationId() %>"><%= illu.getIlluName() %></option>
            <% }
                
            %>
        </select>
        <br>
        
        <input  style="width: 150px;margin-left:60px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Choose Illu ">

    </form>
                
                
                
                <%
                    } 
               
                %>
                </div>
    </body>
</html>
