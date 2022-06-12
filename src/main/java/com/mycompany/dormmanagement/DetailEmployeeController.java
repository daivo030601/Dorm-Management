/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
//chi tiết thông tin tài khoản và thông tin nhân viên
public class DetailEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label IDText;
    @FXML
    private Label nameText;
    @FXML
    private Label birthdayText;
    @FXML
    private Label addressText;
    @FXML
    private Label positionText;
    @FXML
    private Label phoneText;
    @FXML
    private Label usernameText;
    @FXML
    private Label passwordText;
    @FXML
    private Label permissionText;
    @FXML
    void back(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }//quay lại trang trước
    public void receiveData(String idEmployee,String username){   
        showData(idEmployee, username);
    }//nhận dữ liệu từ form cha
    private void showData(String idEmployee,String username){
        
        Account account = new Account();
        Employee employee = new Employee();
        account.GetDataByUsername(username);
        employee.getInfoBaseID(idEmployee);
        IDText.setText(employee.getEmployeeID());
        nameText.setText(employee.getFullname());
        birthdayText.setText(employee.getBirthday());
        addressText.setText(employee.getAddress());
        positionText.setText(employee.getPosition());
        phoneText.setText(employee.getPhoneNumber());
        usernameText.setText(account.getUsername());
        String crUserPermission = LoginFormController.currentUser.getPermission();
        Employee crEmployee = new Employee();
        crEmployee.getInfoBaseAccountID(LoginFormController.currentUser.getIDAccount());
        String crPosition = crEmployee.getPosition();
       
        if(LoginFormController.currentUser.getPermission().equals("admin") && crPosition.equals("Người quản trị")){
            passwordText.setText(account.getPassword());
         }else {
            String pass = account.getPassword();
            String convertPass = "";
            for(int i=0;i<pass.length();i++){
            convertPass = convertPass+"*";
            }
            passwordText.setText(convertPass);
        }
        
        permissionText.setText(account.getPermission());
    
    }//hiển thị dữ liệu lên giao diện
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
