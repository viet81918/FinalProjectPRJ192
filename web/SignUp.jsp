<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign UP</title>
    <style>
        .text-danger {
            
            font-size: 40px;
            color: red;
            margin: 20px;
        }

        html,
        body {
        background-image: linear-gradient(to bottom right, #B2DFEE, pink); /* Hình nền gradient */
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            padding: 20px;
            box-sizing: border-box;
        }

        .form {
            justify-content: space-between;
            flex-direction: column;
            align-items: center;
        }

        .form-row {
            display: flex;
           justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .form-row label {
             font-size: 20px;
            margin-right: 15px;
            flex: 0 0 120px;
        }

        .form-row input[type="text"],
        .form-row input[type="password"],
        .form-row input[type="date"] {
            border-width:thick;
            flex: 1;
            padding: 8px;
            border-radius: 10px;
            border: 1px solid #ccc;
        }

        .form-row input[type="submit"],
        .form-row a {
                        font-size: 20px;

            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form-row a {
            text-decoration: none;
            margin-top: 15px;
            display: inline-block;
        }
   .sign-up-button {          
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
</head>

<body>
    <form class="container" action="SignUpServlet" method="post">
        <h1>Sign Up</h1>
        <p class="text-danger">${mess}</p>
          <div class="form">
            <div class="form-row">
                <label for="firstname">First Name:</label>
                <input type="text" name="firstname" id="firstname" placeholder="First Name" />
            </div>
            <div class="form-row">
                <label for="lastname">Last Name:</label>
                <input type="text" name="lastname" id="lastname" placeholder="Last Name" />
            </div>
<div class="form-row">
                <label for="birthday">Date of Birth:</label>
                <input type="date" name="birthday" id="birthday" pattern="\d{1,2}/\d{1,2}/\d{4}" placeholder="MM/DD/YYYY" required />
            </div>
            <div class="form-row">
                <label for="email">Email:</label>
                <input type="text" name="email" id="email" placeholder="Email" />
            </div>
            <div class="form-row">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" placeholder="Username" />
            </div>
            <div class="form-row">
                <label for="pass">Password:</label>
                <input type="password" name="pass" id="pass" placeholder="Password" />
            </div>
            <div class="form-row">
                <label for="cpass">Confirm Password:</label>
                <input type="password" name="cpass" id="cpass" placeholder="Confirm Password" />
            </div>
              <div style="display: flex; justify-content: space-between; " >
                <input class="sign-up-button" type="submit" value="Register" />
            
            <c:choose>
                <c:when test="${empty param.login}">
                    <a class="sign-up-button" href="?login=true" class="form-row">Back</a>
                </c:when>
                <c:otherwise>
                    <c:redirect url="Login.jsp" />
                </c:otherwise>
            </c:choose>
                    </div>
        </div>
    </form>
</body>

</html>