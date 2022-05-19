/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
   
    public void getInfoBaseAccountID(String IDAccount){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from employee where IDAccount ='"+IDAccount+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.employeeID = resultSet.getString(1);             
              this.fullname = resultSet.getString(2);
              this.birthday= resultSet.getString(3);
              this.address = resultSet.getString(4);
              this.position = resultSet.getString(5);
              this.phoneNumber = resultSet.getString(6);
              this.accountID = resultSet.getString(7);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
    }
    public void getInfoBaseID(String IDEmployee){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from employee where IDEmployee ='"+IDEmployee+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.employeeID = resultSet.getString(1);             
              this.fullname = resultSet.getString(2);
              this.birthday= resultSet.getString(3);
              this.address = resultSet.getString(4);
              this.position = resultSet.getString(5);
              this.phoneNumber = resultSet.getString(6);
              this.accountID = resultSet.getString(7);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
    }
    public ArrayList<String> getIDNameBaseEmployee(){
    ArrayList<String> listemployee = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDEmployee from quanlyktx.employee";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listemployee.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listemployee;
    }
}
