<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Author</title>
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
    <h2 style="font-size: 40px">Add Author</h2>
    <form style="display: flex;flex-direction: column;align-items: center" action="AddAuthorNameServlet" method="post">
        
       Author ID: <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" name="authorId"><br>
        Author Name: <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" name="authorName"><br>
        Author Description: <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" name="authorDescription"><br>
        <input style="height:40px;border-radius: 10px;padding-left:30px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" type="submit" value="Add Author">
    </form>
    <p>${requestScope.mes}</p>
</body>
</html>
