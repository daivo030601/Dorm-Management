/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
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
 * @author Mayy
 */
public class DetailEWBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private EwBillPaneController eWBillController;
    private ElectricAndWaterBill ewBill;
    @FXML
    private Label ID, apartment, room, eStart, eEnd, eNum, eFee, wStart, wEnd, wNum, wFee, total,date;
    @FXML
    private Button markAsDone;
    @FXML
    void back(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    @FXML
    void updateStatus(ActionEvent event){
        ewBill=new ElectricAndWaterBill();
        ewBill.updateStatusEWBill(ID.getText());
        markAsDone.setDisable(true);
        eWBillController.refreshTable();
        
    }
    public void reciveData(String data, EwBillPaneController parentController){
        loadData(data);
        eWBillController = parentController;
        
        
    }
    
    public void loadData(String billID){
        
        ewBill = new ElectricAndWaterBill();
        ewBill.getInfoBaseID(billID);
        if(ewBill.getStatus().equals("Đã thu")) markAsDone.setDisable(true);
        ID.setText(ewBill.getBillID());
        apartment.setText(ewBill.getApartment().getIDApartment());
        room.setText(ewBill.getRoom().getRoomID());
        eStart.setText(Double.toString(ewBill.getChiSoDauDien()));
        eEnd.setText(Double.toString(ewBill.getChiSoCuoiDien()));
        eNum.setText(Double.toString(ewBill.getChiSoCuoiDien()-ewBill.getChiSoDauDien()));
        eFee.setText(Double.toString(ewBill.calElectricFee(ewBill.getChiSoDauDien(),ewBill.getChiSoCuoiDien())));
        wStart.setText(Double.toString(ewBill.getChiSoDauNuoc()));
        wEnd.setText(Double.toString(ewBill.getChiSoCuoiNuoc()));
        wNum.setText(Double.toString(ewBill.getChiSoCuoiNuoc()-ewBill.getChiSoDauNuoc()));
        wFee.setText(Double.toString(ewBill.calWaterFee(ewBill.getChiSoDauNuoc(), ewBill.getChiSoCuoiNuoc())));
        total.setText(ewBill.getTotal());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date.setText(formatter.format(ewBill.getCreateDay()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
