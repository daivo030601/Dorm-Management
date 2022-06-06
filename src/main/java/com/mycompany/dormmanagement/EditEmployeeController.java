/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Employee;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class EditEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label IDText;
    @FXML
    private TextField nameText;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private TextField addressText;
    @FXML
    private ComboBox positionCombo;
    @FXML
    private TextField phoneText;
    @FXML
    private Label usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private ComboBox permissionCombo;
    private Account account = new Account();
    private Employee employee = new Employee();
    private AccountPaneController accountPaneController;
    @FXML
    void updateEmployee(){
        String idEmployee,name,birthday,address,position,phoneNum;
        idEmployee = IDText.getText();
        name = nameText.getText().trim();
        address = addressText.getText().trim();
        birthday = birthdayPicker.getValue().toString();
        position = positionCombo.getSelectionModel().getSelectedItem().toString();
        phoneNum = phoneText.getText().trim();
        employee.update(idEmployee, name, birthday, address, position, phoneNum);
        accountPaneController.refreshTable();
    }
    @FXML
    void updateAccount(){
    
    }
    public void receiveData(String idEmployee,String username, AccountPaneController parentController){
        showData(idEmployee, username);
        accountPaneController = parentController;
    }
    private void showData(String idEmployee,String username){
        
        
        account.GetDataByUsername(username);
        employee.getInfoBaseID(idEmployee);
        IDText.setText(employee.getEmployeeID());
        nameText.setText(employee.getFullname());
        String dateArr[] = employee.getBirthday().split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int date = Integer.parseInt(dateArr[2]);
        birthdayPicker.setValue(LocalDate.of(year, month, date));
        positionCombo.getItems().addAll("Người quản trị","Trưởng nhà","Nhân viên");
        positionCombo.getSelectionModel().select(employee.getPosition());
        addressText.setText(employee.getAddress());
        phoneText.setText(employee.getPhoneNumber());
        usernameText.setText(account.getUsername());
        passwordText.setText(account.getPassword());
        permissionCombo.getItems().addAll("admin","user");
        permissionCombo.getSelectionModel().select(account.getPermission());
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
