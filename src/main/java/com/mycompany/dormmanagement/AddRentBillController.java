/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.RentBill;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class AddRentBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private Room room;
    private RentBillPaneController rentBillPaneController;
    private RentBill rentbill;
    private Employee employee;
    private Student student;
    @FXML
    private TextField idText,dateText,totalText,statusText;
    @FXML
    private ComboBox apartmentComboBox,roomComboBox,employeeComboBox,studentComboBox;
    @FXML
    void backbtn(ActionEvent event){
        closeStage(event);
    }
    
    @FXML
    void selectApartment(ActionEvent event){
        addDataIDRomText();
    }
    @FXML
    void insertdata(ActionEvent event){
        String idtext = idText.getText();
        String apartmentName = apartmentComboBox.getValue().toString();
        String roomname = roomComboBox.getValue().toString();
        String employeeid = employeeComboBox.getValue().toString();
        String studentid = studentComboBox.getValue().toString();
        String totaltext = totalText.getText(); 
        String statustext = statusText.getText();
        apartment = new Apartment();
        apartment.getInfo(apartmentName);
        room = new Room();
        room.getInfo(roomname);
        employee = new Employee();
        employee.getInfoBaseID(employeeid);
        student = new Student();
        student.getInfo(studentid);
        Date createDay = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        rentbill = new RentBill(idtext,employee,room,apartment,student,createDay,totaltext,statustext);
        try {
            rentbill.insertRentBilldata();
            showNotification("Thêm thành công.");
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Thêm không thành công.");
            closeStage(event);
        }
        rentBillPaneController.refreshTable();
        closeStage(event);   
    }
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    public void receiveData (RentBillPaneController parentController)
    {
        rentBillPaneController = parentController;
    }
    
    private void addDataToCombobox(){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        for(var item : apartment.getAllApartment(1)){
            items.add(item);
        }
        apartmentComboBox.setItems(items);
        apartmentComboBox.getSelectionModel().select(0);
        addDataIDRomText();
    }
    private void addDataIDRomText()  
    {
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        String apartmentName = apartmentComboBox.getValue().toString();
        for(var item : apartment.getRoomNameBaseApartment(apartmentName)){
            items.add(item);
        }
        roomComboBox.setItems(items);
        roomComboBox.getSelectionModel().select(0);
        addDataStudentCombobox();
        addDataTotalText();
    }
    private void addDataEmployeeCombobox(){ 
        employee = new Employee();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : employee.getIDNameBaseEmployee()){
            items.add(item);
        }
        employeeComboBox.setItems(items);
        employeeComboBox.getSelectionModel().select(0);
    }
    private void addDataStudentCombobox(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        String apartmentName = apartmentComboBox.getValue().toString();
        String studentName = roomComboBox.getValue().toString();
        for(var item : apartment.getStudentNameBaseRentBill(apartmentName,studentName)){
            items.add(item);
        }
        studentComboBox.setItems(items);
        studentComboBox.getSelectionModel().select(0);
    }
    private void addDataTotalText(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        String apartmentName = apartmentComboBox.getValue().toString();
        String studentName = roomComboBox.getValue().toString();
        String totaltext = String.valueOf(apartment.getTotalRentBill(apartmentName,studentName));
        totalText.setText(totaltext);
    }
    private void addDataIDRentBillText()  
    {
        rentbill = new RentBill();
        int index = rentbill.getLastBillIDIndex()+ 1;
        idText.setText("RB"+index);
    }
    private void addDataLable()  
    {
        Date thoiGian = new Date(); //Khai bao dinh dang ngay thang         
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd/MM/yyyy ");           
        //parse ngay thang sang dinh dang va chuyen thanh string.         
        String showTime = dinhDangThoiGian.format(thoiGian.getTime());               
        dateText.setText(showTime);
        statusText.setText("Chưa thu");
    }
    
    private void DrawUI(){
        addDataIDRentBillText();
        addDataToCombobox();
        addDataEmployeeCombobox();
        addDataLable();
    }
    private void closeStage(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }     
    
}
