<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Genre</title>
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
    <h2 style="font-size: 40px">Add Genre</h2>
    <form style="display: flex;flex-direction: column;align-items: center;" action="AddGenreServlet" method="post">
        Type ID: <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" name="typeId"><br>
        Type of Genre: <input style="height:30px;border-radius: 20px;background-image: linear-gradient(to bottom right, #B2DFEE, pink);" type="text" name="typeOfGenre"><br>
        <input style="margin-top:72px;height:40px;border-radius: 10px;padding-left:30px;background-image:linear-gradient(to bottom right, #FF99FF,#660000);" type="submit" value="Add Genre">
    </form>
    <p>${message}</p>
</body>
</html>
