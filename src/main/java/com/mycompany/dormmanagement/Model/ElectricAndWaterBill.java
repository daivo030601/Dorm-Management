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
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Mayy
 */
public class ElectricAndWaterBill extends Bill {
   protected double ChiSoDauDien;
   protected double ChiSoCuoiDien;
   protected double ChiSoDauNuoc;
   protected double ChiSoCuoiNuoc;
   

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc, String BillID, Employee employee, Apartment apartment, Room room, Date createDay, String total,String status) {
        super(BillID, employee, apartment, room, createDay, total,status);
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
        
        
        
    }

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc) {
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
        
       
    }
   
    public ElectricAndWaterBill() {
        this.billID = "";
        this.employee = new Employee();
        this.apartment = new Apartment();
        this.room = new Room();
        this.createDay = new Date();
        this.total = "0";
        this.ChiSoDauDien = 0.0;
        this.ChiSoCuoiDien = 0.0;
        this.ChiSoDauNuoc = 0.0;
        this.ChiSoCuoiNuoc = 0.0;
        this.status = "Chưa thu";
        
    }

    public double getChiSoDauDien() {
        return ChiSoDauDien;
    }

    public void setChiSoDauDien(double ChiSoDauDien) {
        this.ChiSoDauDien = ChiSoDauDien;
    }

    public double getChiSoCuoiDien() {
        return ChiSoCuoiDien;
    }

    public void setChiSoCuoiDien(double ChiSoCuoiDien) {
        this.ChiSoCuoiDien = ChiSoCuoiDien;
    }

    public double getChiSoDauNuoc() {
        return ChiSoDauNuoc;
    }

    public void setChiSoDauNuoc(double ChiSoDauNuoc) {
        this.ChiSoDauNuoc = ChiSoDauNuoc;
    }

    public double getChiSoCuoiNuoc() {
        return ChiSoCuoiNuoc;
    }

    public void setChiSoCuoiNuoc(double ChiSoCuoiNuoc) {
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
    }
   
    public ObservableList<Map<String, Object>> getEWBill(String apartment, int option, String month,String year){
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
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"'";
                    break;
                case 2:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Đã thu'";
                    break;
                case 3:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Chưa thu'";
                    break;
                default:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("room", resultSet.getString(3));
            item.put("electric", calElectricFee(resultSet.getDouble(6), resultSet.getDouble(7)));
            item.put("water", calWaterFee(resultSet.getDouble(8), resultSet.getDouble(9)));
            item.put("total", resultSet.getDouble(10));
            item.put("status", resultSet.getString(11));
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
    
    public ObservableList<Map<String, Object>> getSearchEWBill(String apartment, int option, String keyWord, String month,String year){
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
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"' and (IDRoom LIKE '%" + keyWord +"%' or IDEWBill LIKE '%"+keyWord+"%')";
                    break;
                case 2:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Đã thu' and (IDRoom LIKE '%" + keyWord +"%' or IDEWBill LIKE '%"+keyWord+"%')";
                    break;
                case 3:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Chưa thu' and (IDRoom LIKE '%" + keyWord +"%' or IDEWBill LIKE '%"+keyWord+"%')";
                    break;
                default:
                    query = "Select * from electricityandwaterbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and (IDRoom LIKE '%" + keyWord +"%' or IDEWBill LIKE '%"+keyWord+"%')";
                    break;
            }
            
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("room", resultSet.getString(3));
            item.put("electric", calElectricFee(resultSet.getDouble(6), resultSet.getDouble(7)));
            item.put("water", calWaterFee(resultSet.getDouble(8), resultSet.getDouble(9)));
            item.put("total", resultSet.getDouble(10));
            item.put("status", resultSet.getString(11));
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
    public double getLastEValueOfRoom(String room){
        double lastEValue = 0.0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select ChiSoCuoiDien from electricityandwaterbill where IDRoom = '"+room+"'" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastEValue = resultSet.getDouble(1);
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
        return lastEValue;
    }
    public double getLastWValueOfRoom(String room){
        double lastWValue = 0.0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select ChiSoCuoiNuoc from electricityandwaterbill where IDRoom = '"+room+"'" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastWValue = resultSet.getDouble(1);
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
        return lastWValue;
    }
    public int getLastBillIDIndex(){
        String lastEWBill = "";
        int index = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDEWBill from electricityandwaterbill ORDER BY IDEWBill DESC LIMIT 1" ;
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                lastEWBill=resultSet.getString(1);
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
        index = Integer.parseInt(lastEWBill.substring(2));
        return index;
    }
    
    public void insertNewEWBill(){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into electricityandwaterbill(IDEWBill,IDEmployee,IDRoom,IDApartment,Createday,ChiSoDauDien,ChiSoCuoiDien,ChiSoDauNuoc,ChiSoCuoiNuoc,Total,status)values(?,?,?,?,?,?,?,?,?,?,?)" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.billID);
            statement.setString(2, this.employee.getEmployeeID());
            statement.setString(3, this.room.getRoomID());
            statement.setString(4, this.apartment.getIDApartment());
            java.sql.Date sqlDate = new java.sql.Date(this.createDay.getTime());
            statement.setDate(5, sqlDate);
            statement.setDouble(6, this.ChiSoDauDien);
            statement.setDouble(7, ChiSoCuoiDien);
            statement.setDouble(8, ChiSoDauNuoc);
            statement.setDouble(9, ChiSoCuoiNuoc);
            statement.setDouble(10, Double.parseDouble(total));
            statement.setString(11, this.status);
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
    
    public double calElectricFee(double chiSoDau, double chiSoCuoi){
    return 3*(chiSoCuoi-chiSoDau);
    }
    public double calElectricFee(double eNumber){
    return 3*eNumber;
    }
    public double calWaterFee(double chiSoDau, double chiSoCuoi){
    return 10*(chiSoCuoi-chiSoDau);
    }
    public double calWaterFee(double wNumber){
    return 10*wNumber;
    }
    public double totalFee(double electricFee,double waterFee){
    return electricFee + waterFee;
    }
}
