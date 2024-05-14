/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Users implements Serializable {
    private String userId;
    private String name;
    private int age;
    private String gmail;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public Users() {
    }

    public Users(String userId, String name, int age, String gmail, String password, String firstName, String lastName, String role) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gmail = gmail;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
public Users( String name, int age, String gmail, String password, String firstName, String lastName, String role, int number) {
        this.userId = 'U'+ Integer.toString(number+1);
        this.name = name;
        this.age = age;
        this.gmail = gmail;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", name=" + name + ", age=" + age + ", gmail=" + gmail + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + '}';
    }
    
  
}
