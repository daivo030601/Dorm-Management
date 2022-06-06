/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Account;
import com.mycompany.dormmanagement.Model.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class AccountPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Employee employee = new Employee();
    @FXML
    private TableView dataTableView;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private ComboBox positionCombobox;
    @FXML
    private TextField searchText,idTextField,passwordText,usernameText,phoneText,addressText;
    @FXML
    private TableColumn IDCol,nameCol,birthdayCol,positonCol,usernameCol,passCol,toolCol;
    @FXML
    private Button showAllBtn,addBtn;
    @FXML 
    private Label nameError,birthdayError,addressError,phoneError,usernameError,passError;
    @FXML
    void textChange(){
        tableSearch(dataTableView);
        showAllBtn.setVisible(true);
    }
    @FXML
    void fieldValueChange(){
    if(idTextField.getText().isEmpty()) {
        nameError.setVisible(true);
    }
    else nameError.setVisible(false);
    if(birthdayPicker.getValue()==null){
        birthdayError.setVisible(true);
    }
    else birthdayError.setVisible(false);
    if(addressText.getText().isEmpty()) {
        addressError.setVisible(true);    
    }
    else addressError.setVisible(false);
    if(phoneText.getText().isEmpty()) {
        phoneError.setVisible(true);
    }
    else phoneError.setVisible(false);
    if(usernameText.getText().isEmpty()) {
        usernameError.setVisible(true);   
    }
    else usernameError.setVisible(false);
    if(passwordText.getText().isEmpty()) {
        passError.setVisible(true);   
    }
    else passError.setVisible(false);
    if(!idTextField.getText().isEmpty() &&birthdayPicker.getValue() !=null&& !phoneText.getText().isEmpty() && !addressText.getText().isEmpty() && !usernameText.getText().isEmpty() && !passwordText.getText().isEmpty()){
    addBtn.setDisable(false);
    }
    else{
    addBtn.setDisable(true);
    }
    
    }

    @FXML
    void showAll(){
        searchText.clear();
        dataTableView.getItems().clear();
        addDataToTable(dataTableView);
        showAllBtn.setVisible(false);
    }
    @FXML
    void addEmployee(){
        String idEmployee,name,birthday,address,position,idAccount,phone,username,pass,permission;
        name = idTextField.getText().trim();
        birthday = birthdayPicker.getValue().toString();
        address = addressText.getText().trim();
        position = positionCombobox.getSelectionModel().getSelectedItem().toString();
        phone = phoneText.getText().trim();
        username = usernameText.getText().trim();
        pass = passwordText.getText().trim();
        int index = employee.getLastEmployeeIDIndex() + 1;
        idEmployee = "NV" + index;
        idAccount = "AC" + index;
        if(position.equals("Người quản trị")) permission = "admin";
        else permission = "user";
        try {
            Account newAccount = new Account(idAccount, username, pass, permission);
            newAccount.insertNewAccount();
            Employee newEmployee = new Employee(idEmployee, idAccount, name, address, position, phone, birthday);
            newEmployee.insertNewEmployee();
            showNotification("Thêm thành công");
            refreshTable();
            clearAll();
        } catch (Exception e) {
            showNotification("Thêm không thành công");
        }
        
        
    }
    @FXML
    void clearText(){
    clearAll();
    }
    public void refreshTable(){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView); 
    }
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    private void initTable(TableView table){
        IDCol.setCellValueFactory(new MapValueFactory<>("id"));
        nameCol.setCellValueFactory(new MapValueFactory<>("name"));
        positonCol.setCellValueFactory(new MapValueFactory<>("position"));
        birthdayCol.setCellValueFactory(new MapValueFactory<>("birthday"));
        usernameCol.setCellValueFactory(new MapValueFactory<>("username"));
        passCol.setCellValueFactory(new MapValueFactory<>("pass"));
        toolCol.setCellValueFactory(new MapValueFactory<>("tool"));
        
        IDCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));  
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        positonCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15)); 
        birthdayCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15)); 
        usernameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2)); 
        passCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        addDataToTable(table);
        addButtonToTable();
    }
    private void addDataToTable(TableView table){
        table.getItems().clear();
        table.getItems().addAll(employee.getAllEmployeeIncludeAccount());
    }
    private void tableSearch(TableView table){
    String keyWord = searchText.getText().trim();
    table.getItems().clear();
    table.getItems().addAll(employee.getSearchEmployeeIncludeAccount(keyWord));
    }
    private void clearAll(){
    idTextField.clear();
    passwordText.clear();
    usernameText.clear();
    addressText.clear();
    phoneText.clear();
    positionCombobox.getSelectionModel().clearSelection();
    birthdayPicker.getEditor().clear();
    birthdayPicker.setValue(null);
    passError.setVisible(false);
    usernameError.setVisible(false);
    addressError.setVisible(false);
    phoneError.setVisible(false);
    nameError.setVisible(false);
    birthdayError.setVisible(false);
    }
     private void addButtonToTable() {     
        Callback<TableColumn<Object, String>, TableCell<Object, String>> cellFactory = (TableColumn<Object, String> param) -> {
            // make cell containing buttons
            final TableCell<Object, String> cell = new TableCell<Object, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Button btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
                        btnDetail.setStyle("-fx-background-color: transparent;");
                        Button btnEdit = new Button("",new ImageView("/Image/edit.png"));
                        btnEdit.setStyle("-fx-background-color: transparent;");
                        Button btnDelete = new Button("",new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        btnDetail.setOnAction((ActionEvent event) -> {
                            int index = getIndex();  
                            String idEmployee = (String) IDCol.getCellObservableValue(index).getValue();
                            String username = (String) usernameCol.getCellObservableValue(index).getValue();
                            sendDetailData(idEmployee,username);
                        });
                        btnEdit.setOnAction((ActionEvent event) -> {
                            
                            int index = getIndex();  
                            String idEmployee = (String) IDCol.getCellObservableValue(index).getValue();
                            String username = (String) usernameCol.getCellObservableValue(index).getValue();
                            sendEditData(idEmployee,username);
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            

                        });
                        HBox btnManage = new HBox(btnDetail, btnEdit, btnDelete);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };

        toolCol.setCellFactory(cellFactory);
    }
     private void sendDetailData(String idEmployee, String username){
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailEmployee.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Employee and account detail");
        stage.setScene(new Scene(root));
        DetailEmployeeController detailEmployeeController = loader.getController(); 
        detailEmployeeController.receiveData(idEmployee,username);
        stage.show(); 
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("success");
        
    }
     private void sendEditData(String idEmployee, String username){
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editEmployee.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit employee and account");
        stage.setScene(new Scene(root));
        EditEmployeeController editEmployeeController = loader.getController(); 
        editEmployeeController.receiveData(idEmployee,username,this);
        stage.show(); 
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("success");
        
    }
    private void addDataToCombobox(){
        positionCombobox.getItems().addAll("Người quản trị","Trưởng nhà","Nhân viên");
        positionCombobox.getSelectionModel().select(1);
    }
    private void DrawUI(){
        initTable(dataTableView);
        addDataToCombobox();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
