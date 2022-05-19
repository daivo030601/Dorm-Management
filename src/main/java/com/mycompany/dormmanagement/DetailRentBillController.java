/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.RentBill;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class DetailRentBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private RentBillPaneController rentBillPaneController;
    private RentBill rentbill;
    @FXML
    private Label ID, apartment, room, idemployee, nameemployee, idstudent, namestudent,date, total,status;
    @FXML
    private Button markAsDonebtn;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    @FXML
    void updateStatus(ActionEvent event){
        rentbill = new RentBill();
        rentbill.updateStatusRentBill(ID.getText());
        markAsDonebtn.setDisable(true);
        rentBillPaneController.refreshTable();
        
    }
    public void reciveData(String data, RentBillPaneController RentBillController){
        loadData(data);
        rentBillPaneController = RentBillController;
        
        
    }
    
    public void loadData(String billID){
        
        rentbill = new RentBill();
        rentbill.getInfoBaseIDRentBill(billID);
        if(rentbill.getStatus().equals("Đã thu")) markAsDonebtn.setDisable(true);
        ID.setText(rentbill.getBillID());
        apartment.setText(rentbill.getApartment().getIDApartment());
        room.setText(rentbill.getRoom().getRoomID());
        idemployee.setText(rentbill.getEmployee().getEmployeeID());
        nameemployee.setText(rentbill.getEmployee().getFullname());
        idstudent.setText(rentbill.getStudent().getStudentID());
        namestudent.setText(rentbill.getStudent().getFullName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(formatter.format(rentbill.getCreateDay()));
        total.setText(rentbill.getTotal());
        status.setText(rentbill.getStatus());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
