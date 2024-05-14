/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Admin extends Users {
    private String adminId;
   
    // Constructor, getters, setters, etc.

    public Admin() {
    }

    public Admin(String adminId, String userId, String name, int age, String gmail, String password, String firstName, String lastName, String role) {
        super(userId, name, age, gmail, password, firstName, lastName, role);
        this.adminId = adminId;
        
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        
        return "Admin{" + "adminId=" + adminId + super.toString()+'}';
    }
    
    
}
