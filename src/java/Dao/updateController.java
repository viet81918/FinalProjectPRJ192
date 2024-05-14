/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Controller.Java_JDBC;
import Model.Customer;
import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author ASUS
 */
public class updateController {
    public static Customer updateCustomer(Connection con, int newAge, String newEmail, String newPassword, String newFN, String newLN,String userId)throws SQLException{
        
        String sql = "UPDATE Users SET  Age = ?, Gmail = ?, Password = ?, Lastname = ?, Firstname = ?  WHERE UserID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            
            pstmt.setInt(1, newAge);
            pstmt.setString(2, newEmail);
            pstmt.setString(3, newPassword);
            pstmt.setString(4, newLN);//lastname
            pstmt.setString(5,newFN);//firstname
            
            pstmt.setString(6, userId); 
            int rowsAffected = pstmt.executeUpdate();
            
            // Kiểm tra xem cập nhật có thành công hay không và trả về đối tượng Customer đã được cập nhật
            if (rowsAffected > 0) {
                return Java_JDBC.getCustomerByUserId(con, userId);
            }
        }
      return null; 
    }
    
    
    
    public static Admin updateAdmin(Connection con, int newAge, String newEmail, String newPassword, String newFN, String newLN,String userId)throws SQLException{
        String sql = "UPDATE Users SET  Age = ?, Gmail = ?, Password = ?, Lastname = ?, Firstname = ?  WHERE UserID = ?";
        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, newAge);
            pstmt.setString(2, newEmail);
            pstmt.setString(3, newPassword);
            pstmt.setString(4, newLN);//lastname
            pstmt.setString(5,newFN);//firstname
            
            pstmt.setString(6, userId);
            pstmt.executeUpdate();
        }
        return null;
    }
}