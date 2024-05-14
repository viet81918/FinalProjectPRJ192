/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Customer extends Users {
      private String customerId;

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public Customer(String customerId, String userId, String name, int age, String gmail, String password, String firstName, String lastName, String role) {
        super(userId, name, age, gmail, password, firstName, lastName, role);
        this.customerId = customerId;
    }

    public Customer(String name, int age, String gmail, String password, String firstName, String lastName, String role, int numberUser, int numberCus) {
        super(name, age, gmail, password, firstName, lastName, role, numberUser);
        this.customerId = "C"+Integer.toString(numberCus+1);
    }
    public Customer(Users u,int numberUser ,int numberCus) {
        super(u.getName(), u.getAge(), u.getGmail(),u.getPassword()  ,u.getFirstName(), u.getLastName(), u.getRole(), numberUser);
        this.customerId = "C"+Integer.toString(numberCus+1);
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId  + super.toString()+'}';
    }
   
}
