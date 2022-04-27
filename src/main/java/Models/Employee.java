/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mayy
 */
public class Employee {
    
   protected String employeeID;
   protected String accountID;
   protected String fullname;
   protected String address;
   protected String position;
   protected String phoneNumber;
   protected String birthday;

    public Employee(String EmployeeID, String AccountID, String fullname, String address, String position, String phoneNumber, String birthday) {
        this.employeeID = EmployeeID;
        this.accountID = AccountID;
        this.fullname = fullname;
        this.address = address;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
    public Employee() {
        this.employeeID = "";
        this.accountID = "";
        this.fullname = "";
        this.address = "";
        this.position = "";
        this.phoneNumber = "";
        this.birthday = "";
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
   
}
