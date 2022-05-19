/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mayy
 */
public class Room {
protected String roomID;
protected Apartment apartment;
protected String noStudent;
protected String status;
protected String type;
protected int rentingPrice;



    public Room() {
        this.roomID = "";
        this.apartment = new Apartment();
        this.noStudent = "";
        this.status = "";
        this.type = "";
        this.rentingPrice = 0;
        
    }

    public Room(String roomID, Apartment apartment, String noStudent, String status, String type, int rentingPrice) {
        this.roomID = roomID;
        this.apartment = apartment;
        this.noStudent = noStudent;
        this.status = status;
        this.type = type;
        this.rentingPrice = rentingPrice;
       
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getNoStudent() {
        return noStudent;
    }

    public void setNoStudent(String noStudent) {
        this.noStudent = noStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(int rentingPrice) {
        this.rentingPrice = rentingPrice;
    }
    
    public ObservableList<Map<String, Object>> getRoom(String apartment, int option){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from room where IDApartment ='"+ apartment+"'";
                    break;
                case 2:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Còn chỗ'";
                    break;
                case 3:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Đầy'";
                    break;
                default:
                    query = "Select * from room where IDApartment ='"+ apartment+"'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("idroom", resultSet.getString(1));
            item.put("nostudent", resultSet.getString(3));
            item.put("status", resultSet.getString(4));
            item.put("type", "Phòng " + resultSet.getString(5));
            items.add(item);
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
        return items;
    }
public ObservableList<Map<String, Object>> getSearchRoom(String apartment, int option, String keyWord){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and IDRoom LIKE '%" + keyWord +"%'";
                    break;
                case 2:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Còn chỗ' and IDRoom LIKE '%" + keyWord +"%' ";
                    break;
                case 3:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Đầy' and IDRoom LIKE '%" + keyWord +"%' ";
                    break;
                default:
                    query = "Select * from room where IDApartment ='"+ apartment+"'and IDRoom LIKE '%" + keyWord +"%'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("idroom", resultSet.getString(1));
            item.put("nostudent", resultSet.getString(3));
            item.put("status", resultSet.getString(4));
            item.put("type","Phòng " + resultSet.getString(5));
            items.add(item);
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
        return items;
    }
public void getInfo(String room){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from room where IDRoom ='"+room+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.roomID = resultSet.getString(1);
              this.apartment.getInfo(resultSet.getString(2));
              this.noStudent = resultSet.getString(3);
              this.status = resultSet.getString(4);
              this.type = resultSet.getString(5);
              this.rentingPrice = resultSet.getInt(6);
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
public void insertdatatoType(String room,String type, int rentingprice ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update room set Type = '"+type+"', RentingPrice = '"+rentingprice+"' where IDRoom ='"+room+"'" ;
            statement = con.prepareStatement(query);
            statement.execute();
            
        } catch (SQLException ex) {
           
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
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
public void insertRoomdata(){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into room(IDRoom,IDApartment,NoStudent,Status,Type,RentingPrice)values(?,?,?,?,?,?)" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.roomID);
            statement.setString(2, this.apartment.getIDApartment());
            statement.setString(3, this.noStudent);
            statement.setString(4, this.status);
            statement.setString(5, this.type);
            statement.setInt(6, this.rentingPrice);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
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
public int getLastRoomIndex(String apartment ){
        String lastRoom = "";
        int index = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDRoom from quanlyktx.room where IDApartment ='"+apartment+"'" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastRoom=resultSet.getString(1);             
                int tempindex = Integer.parseInt(lastRoom.substring(1));
                if(index<=tempindex){
                index = tempindex;
                }
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
        System.out.println(index);
        return index;
    }
public void deleteData(String room ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="delete from quanlyktx.room where IDRoom ='"+room+"'" ;
            statement = con.prepareStatement(query);
            statement.execute();   
        } catch (SQLException ex) {
           
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
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

public double getLastRentingPiceRoom(String room){
        double retingpice = 0.0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="Select RentingPrice from quanlyktx.room where  room.IDRoom = '"+room+"' " ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                retingpice = resultSet.getDouble(1);
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
        return retingpice;
    }
}
