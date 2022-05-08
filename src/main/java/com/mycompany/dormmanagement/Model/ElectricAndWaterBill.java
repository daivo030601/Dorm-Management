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

/**
 *
 * @author Mayy
 */
public class ElectricAndWaterBill extends Bill {
   protected double ChiSoDauDien;
   protected double ChiSoCuoiDien;
   protected double ChiSoDauNuoc;
   protected double ChiSoCuoiNuoc;
   protected Button btnDetail, btnEdit, btnDelete;

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc, String BillID, Employee employee, Apartment apartment, Room room, String createDay, String total) {
        super(BillID, employee, apartment, room, createDay, total);
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
        
        
    }

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc) {
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
         this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
       
    }
   
    public ElectricAndWaterBill() {
        this.billID = "";
        this.employee = new Employee();
        this.apartment = new Apartment();
        this.room = new Room();
        this.createDay = "";
        this.total = "0";
        this.ChiSoDauDien = 0.0;
        this.ChiSoCuoiDien = 0.0;
        this.ChiSoDauNuoc = 0.0;
        this.ChiSoCuoiNuoc = 0.0;
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
        
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

    public Button getBtnDetail() {
        return btnDetail;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
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
    
    public double calElectricFee(double chiSoDau, double chiSoCuoi){
    return 3*(chiSoCuoi-chiSoDau);
    }
    public double calWaterFee(double chiSoDau, double chiSoCuoi){
    return 10*(chiSoCuoi-chiSoDau);
    }
    public double totalFee(double electricFee,double waterFee){
    return electricFee + waterFee;
    }
}
