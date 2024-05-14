<%-- 
    Document   : Login
    Created on : Feb 26, 2024, 7:03:34 PM
    Author     : ASUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        html, body {
        background-image: linear-gradient(to bottom right, #B2DFEE, pink); /* Hình nền gradient */
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
            height: 100%;
            margin: 0;
            padding: 0;
        }
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            width: 100%;
            max-width: 400px;
            padding: 20px;
        }
        .form {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
            width: 100%;
        }
        .form input[type="text"],
        .form input[type="password"] {
            width: 100%;
            height: 40px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 16px;
        }
        .form input[tye="checkbox"],
        .form input[type="submit"] {
            width: 100%;
            height: 40px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            font-size: 20px;
            transition: background-color 0.3s ease, color 0.3s ease;
            margin: 10px 0px;
        }
        .form input[type="submit"]:hover {
            background-color: #ffffff;
            color: #4CAF50;
        }
        h1 {
            font-size: 40px;
            margin-bottom: 20px;
        }   
        
        
        .red {
            padding: 17px 0px 0px 5px;
            display: flex;           
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
            background-color: #f0b3bc;
           
            width:100%;
            height: 40px;
            border-radius: 5px;
        }
        
        /* Thiết lập kiểu cho nút Sign Up */
        .sign-up-button {          
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            font-size: 20px;
            transition: background-color 0.3s ease, color 0.3s ease;
            text-decoration: none;
            display: flex;
            justify-content: center;
            height: 40px;
            align-items: center;
        }

        /* Thiết lập kiểu khi di chuột qua nút */
        .sign-up-button:hover {
            background-color: #ffffff;
color: #4CAF50;
        }

        /* Thiết lập kiểu khi nút được nhấn */
        .sign-up-button:active {
            background-color: #3e8e41;
        }
        
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Login !!!</h1>
            <%--<h3 style="color:red">${requestScope.error}</h3> --%>
            
            

            <div class="form">
                <form action="LoginServlet" method="post">
                    <div class="alert">
                    <c:if test="${not empty error}">
                        <div class="${red ? 'red' : ''}">
                            ${error}
                        </div>
                    </c:if>
                    </div>
                    <div>
                    <input type="text" name="name" value="${cookie.name.value}" placeholder="Name or phone number"/><br/>
                    <input type="text" name="email" value="${cookie.email.value}" placeholder="Your...@gmail.com"><br/>
                    <input type="password" name="pass" value="${cookie.pass.value}" placeholder="Password"/><br/>
                    <input style="" type="checkbox" ${(cookie.remember.value eq 'ON')? "checked":""} name="remember" value="ON"/>Remember account<br/>
                    <input type="submit" value="LOGIN"/>        
                    </div>
                    
                    
                
                    <c:choose>
                        <c:when test="${empty param.signUp}">
                            <a href="?signUp=true" class="sign-up-button">SIGN UP</a>
                        </c:when>
                        <c:otherwise>
                            <c:redirect url="SignUp.jsp"/>
                        </c:otherwise>
                    </c:choose>
                <%--<a href="SignUpController">Sign Up</a><br/> --%>

                    
                </form>
            </div>
            
        </div>
    </body>
</html>