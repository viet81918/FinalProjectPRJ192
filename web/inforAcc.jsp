<%@ page import="Model.Customer" %>
<%@ page import="Model.Users" %>
<%@ page import="Model.Admin" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*, java.util.ArrayList, Controller.Java_JDBC" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Information</title>
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
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
}
    </style>

    <body>
        <div>
        <header class="header">
            <button style="width:150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #CDCDB4,#EE6363);" onclick="window.location.href='Home.jsp';" class="no-underline">Home</button>
        </header>
            <h1 style="font-size:30px;padding-left: 120px; ">Account Information</h1>
            <table class="tb" border="1">
                <%--
                <% Customer customer = (Customer) request.getAttribute("customer"); 

                 if(customer != null) { 
                %>
                --%>
                <tr>
                    <th>Name Acc</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Fist Name</th>
                    <th>Last Name</th>
                </tr>
                    <%--Customer--%>
                    <c:if test="${not empty customer}">
                        <tr>
                        <td>${customer.getName()}</td>
                        <td>${customer.getAge()}</td>
                        <td>${customer.getGmail()}</td>
                        <td>${customer.getPassword()}</td>
                        <td>${customer.getFirstName()}</td>
                        <td>${customer.getLastName()}</td>
                    </tr>
                    </c:if>
                    <%--Admin--%>
                    <c:if test="${not empty admin}">
                        <tr>
                        <td>${admin.getName()}</td>
                        <td>${admin.getAge()}</td>
                        <td>${admin.getGmail()}</td>
                        <td>${admin.getPassword()}</td>
                        <td>${admin.getFirstName()}</td>
                        <td>${admin.getLastName()}</td>
                    </tr>
                    </c:if>


            <%--
                <p>Name: <%= customer.getName() %></p>
                <p>Age: <%= customer.getAge() %></p>
                <p>Email: <%= customer.getGmail() %></p>
                <p>Password: <%= customer.getPassword() %></p>
                <p>First Name: <%= customer.getFirstName() %></p>
                <p>Last Name: <%= customer.getLastName() %></p>




            <% } %>
                --%>
            </table>
            <div>
                <button style="margin-top: 20px;margin-left:180px;width:150px;height:40px;border-radius: 20px;background-image:linear-gradient(to bottom right, #B2DFEE, pink);" onclick="window.location.href='Update.jsp';" class="no-underline">UPDATE</button>
            </div>
    </div>
    </body>
</html>