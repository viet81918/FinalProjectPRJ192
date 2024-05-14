/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Controller.Java_JDBC;
import static Controller.Java_JDBC.getConnectionWithSqlJdbc;
import Model.Customer;
import Model.Users;



import java.sql.*;
/**
 *
 * @author ASUS
 */
public class SignUpController {
   public static void CreateAccountCustomer(Connection con, String name, int age, String Gmail, String pass, String firstName, String lastName, String role, int numberUser,int numberCus) throws SQLException {
    String sqlforU = "INSERT INTO Users(UserID, Name, Age, Gmail, Password, FirstName, LastName, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String sqlforC = "INSERT INTO Customers(CustomerID, UserID) VALUES (?, ?)";
    
    try (PreparedStatement pstmtU = con.prepareStatement(sqlforU);
         PreparedStatement pstmtC = con.prepareStatement(sqlforC)) {
        Customer c = new Customer(name, age, Gmail, pass, firstName, lastName, role, numberUser, numberCus); // Assuming numberCus is not used in this method
        
        pstmtU.setString(1, c.getUserId());
        pstmtU.setString(2, c.getName());
        pstmtU.setInt(3, c.getAge());
        pstmtU.setString(4, c.getGmail());
        pstmtU.setString(5, c.getPassword());
        pstmtU.setString(6, c.getFirstName());
        pstmtU.setString(7, c.getLastName());
        pstmtU.setString(8, c.getRole());
        
        pstmtC.setString(1, c.getCustomerId());
        pstmtC.setString(2, c.getUserId()); // Assuming the user ID generated for the customer is used here
        
        pstmtU.executeUpdate();
        pstmtC.executeUpdate();
    } catch (SQLException e) {
        // Handle any SQL exceptions
        e.printStackTrace();
    }
}
}
