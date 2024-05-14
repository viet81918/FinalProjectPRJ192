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
public class Login extends Java_JDBC{
    
    public Users check(String name, String Gmail, String password) throws Exception{
        String sql ="select UserID from Users where Name=? and Gmail=? and Password =?";
        try(Connection con = getConnectionWithSqlJdbc()){
            PreparedStatement st =con.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,Gmail);
            st.setString(3, password);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                
                return Java_JDBC.getUserById (con,(rs.getString("UserID")));
                        
            }
        }catch(SQLException E){
            
        }
        return null;
    }
    /*
    public Users check(String name, String gmail, String password) throws Exception {
    String sql = "SELECT * FROM Users WHERE Name = ? AND Gmail = ? AND Password = ?";
    try (Connection con = getConnectionWithSqlJdbc()) {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, gmail);
        st.setString(3, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Users(rs.getString("UserID"), rs.getString("Name"), rs.getInt("Age"),
                            rs.getString("Gmail"), rs.getString("Password"),
                            rs.getString("FirstName"), rs.getString("LastName"),
                            rs.getString("Role"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    public Account checkAccExist(String name) throws Exception{
        String sql = "select * from Accout where username =?";
        try(Connection con = getConnectionWithSqlJdbc()){
            PreparedStatement st =con.prepareStatement(sql);
            st.setString(1,name);          
            ResultSet rs =st.executeQuery();
        while(rs.next()){
                return new Account(rs.getString(1),rs.getString(2));
            }
        }catch(SQLException E){
            
        }
        return null;
    }-*/
}
