/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

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
    
    
}
