/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.lang.Runtime.Version;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.StatementEvent;
/**
 *
 * @author Mayy
 */
public class Account {
    protected String IDAccount;
    protected String Username;
    protected String Password;
    protected String Permission;

    public Account(String IDAccount, String Username, String Password, String Permission) {
        this.IDAccount = IDAccount;
        this.Username = Username;
        this.Password = Password;
        this.Permission = Permission;
    }
    
    public Account() {
        this.IDAccount = "";
        this.Password = "";
        this.Permission = "";
        this.Username = "";
    }

    public String getIDAccount() {
        return IDAccount;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getPermission() {
        return Permission;
    }

    public void setIDAccount(String IDAccount) {
        this.IDAccount = IDAccount;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setPermission(String Permission) {
        this.Permission = Permission;
    }
    public void GetDataByUsername(String username) {
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from account where Username = '" + username + "'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                setIDAccount(resultSet.getString(1));
                setUsername(resultSet.getString(2));
                setPassword(resultSet.getString(3));
                setPermission(resultSet.getString(4));
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
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
    
    }       
}