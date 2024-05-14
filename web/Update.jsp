<%-- 
    Document   : Update
    Created on : Mar 10, 2024, 7:26:43 PM
    Author     : ASUS
--%>
<%@ page import="Model.Customer" %>
<%@ page import="Model.Users" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        html, body {
            background-color: #f2f2f2;
    height: 100%;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

p {
    color: red;
    margin: 10px;
}

.body {
    width: 300px;
    display: flex;
    flex-direction: column;
}

.form-group {
    display: flex;
    margin-bottom: 10px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
input[type="password"],
input[type="date"] {
    width: 100%;
    padding: 5px;
    border-radius: 20px;
    border: 1px solid #ccc;
}
   .update-button {          
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 20px;
            transition: background-color 0.3s ease, color 0.3s ease;
            text-decoration: none;
            display: flex;
            justify-content: center;
            height: 40px;
            width: 150px;
            align-items: center;
        }
    </style>
    <body>
        <form class="body" action="UpdateServlet" method="get">
    <p class="text-danger">${mess}</p> 
    <div class="form-group">
        <label for="newFirstname">First Name:</label>
        <input type="text" name="newFirstname" id="newFirstname" placeholder="New First Name" required/>
    </div>          
    <div class="form-group">
        <label for="newLastname">Last Name:</label>
        <input type="text" name="newLastname" id="newLastname" placeholder="New Last Name" required/>
    </div>
    <div class="form-group">
        <label for="newBirthday">New Date:</label>
        <input type="date" name="newBirthday" id="newBirthday" pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="MM/DD/YYYY" required/>
    </div>
    <div class="form-group">
        <label for="newEmail">New Email:</label>
        <input type="email" name="newEmail" id="newEmail" placeholder="New Email" required/>
    </div>
    <div class="form-group">
        <label for="newPassword">New Password:</label>
        <input type="password" name="newPassword" id="newPassword" placeholder="New Password" required/>
    </div>
    <div class="form-group">
        <label for="cpass">Confirm Password:</label>
        <input type="password" name="cpass" id="cpass" placeholder="Confirm Password" required/>
    </div>
    <button style="margin-left: 60px;margin-top: 10px;" class="update-button" type="submit">UPDATE</button>
</form>
            
    </body>
</html>