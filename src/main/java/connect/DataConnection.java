/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */

//phần này dùng để xài trong chương trình sẽ có video hướng dẫn cách code với cách xài connect này nha
public class DataConnection extends Config {
    
    Connection dbconnection;
    
    public Connection getConnection() {
        String connectionString = "jdbc:mysql://"+ Config.dbhost+ ":"+ Config.dbport+ "/"+ Config.dbname;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dbconnection = DriverManager.getConnection(connectionString, Config.dbuser, Config.dbpass);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbconnection;
    }
    
    public static void main(String[] args) {
       
       try {
           String user="root";
           String password="000";
           String url="jdbc:mysql://localhost:3306/quanlyktx";
           Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c=DriverManager.getConnection(url, user, password);
            if (c != null) {
                System.out.print("Success");
            } else {
                System.out.print("fail");
            }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
