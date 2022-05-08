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
private String roomID;
private Apartment apartment;
private String noStudent;
private String status;
private String type;
private int rentingPrice;
protected Button btnDetail, btnEdit, btnDelete;


    public Room() {
        this.roomID = "";
        this.apartment = new Apartment();
        this.noStudent = "";
        this.status = "";
        this.type = "";
        this.rentingPrice = 0;
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
    }

    public Room(String roomID, Apartment apartment, String noStudent, String status, String type, int rentingPrice) {
        this.roomID = roomID;
        this.apartment = apartment;
        this.noStudent = noStudent;
        this.status = status;
        this.type = type;
        this.rentingPrice = rentingPrice;
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
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
}
