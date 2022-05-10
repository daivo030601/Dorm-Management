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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mayy
 */
public class Apartment {
    protected String IDApartment;
    protected String NoRoom;
    protected String Gender;
    protected String IDEmployee;

    public Apartment(String IDApartment, String NoRoom, String Gender, String IDEmployee) {
        this.IDApartment = IDApartment;
        this.NoRoom = NoRoom;
        this.Gender = Gender;
        this.IDEmployee = IDEmployee;
    }
    
    public Apartment() {
        this.IDApartment = "";
        this.NoRoom = "";
        this.Gender = "";
        this.IDEmployee = "";
    }

    public String getIDApartment() {
        return IDApartment;
    }

    public void setIDApartment(String IDApartment) {
        this.IDApartment = IDApartment;
    }

    public String getNoRoom() {
        return NoRoom;
    }

    public void setNoRoom(String NoRoom) {
        this.NoRoom = NoRoom;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getIDEmployee() {
        return IDEmployee;
    }

    public void setIDEmployee(String IDEmployee) {
        this.IDEmployee = IDEmployee;
    }
    
    public ArrayList<String> getAllApartment(){
    
        ArrayList<String> listApartment = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDApartment from apartment";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listApartment.add(resultSet.getString(1));
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
        return listApartment;
    }
    
    public ArrayList<String> getRoomNameBaseApartment(String apartment){
    ArrayList<String> listRoom = new ArrayList<>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDRoom from apartment,room where apartment.IDApartment = room.IDApartment and room.IDApartment = '"+apartment+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listRoom.add(resultSet.getString(1));
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
        return listRoom;
    }
    
    public void getInfo(String apartment){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from apartment where IDApartment ='"+apartment+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.IDApartment = resultSet.getString(1);
              this.NoRoom = resultSet.getString(2);
              this.Gender = resultSet.getString(3);
              this.IDEmployee = resultSet.getString(4);
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
    
}
