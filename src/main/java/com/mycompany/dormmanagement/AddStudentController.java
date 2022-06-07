/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddStudentController implements Initializable {
    private StudentPaneController studentPaneController;
    private Student student;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField idStudentText;
    @FXML
    private TextField idCardText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField universityText;
    @FXML
    private TextField gradeText;
    @FXML
    private TextField sYearText;
    @FXML
    private TextField eYearText;
    @FXML
    private Button insertBtn;
    @FXML
    private Button addImageBtn;
    @FXML
    private ImageView image;
    @FXML
    private DatePicker birthdayDatePicker;
    private File file ;
    private FileInputStream fis;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void getDate(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawUI();
    }    

    @FXML
    private void back(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    private void insertData(ActionEvent event) {
        String name = nameText.getText();
        String idStudent = idStudentText.getText();
        Date birthday = java.sql.Date.valueOf(birthdayDatePicker.getValue());
        String idCard = idCardText.getText();
        String phone = phoneText.getText();
        String university = universityText.getText();
        String grade = gradeText.getText();
        String sYear = sYearText.getText();
        String eYear = eYearText.getText();
        String gender = genderComboBox.getValue().toString();
        
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (name.isEmpty() || idStudent.isEmpty() || birthday.toString().isEmpty() || idCard.isEmpty() || phone.isEmpty() || university.isEmpty() || grade.isEmpty() || sYear.isEmpty() || eYear.isEmpty()) {
            showNotification("Bạn chưa điền đầy đủ thông tin! Xin vui lòng điền đầy đủ trước khi thêm sinh viên.");
        } else {
            student = new Student(idStudent, name, birthday, gender, idCard, phone, university, grade, "CX", sYear, eYear, null, (InputStream)fis);
            try {
            student.insertStudentdata((int)file.length());
           
            } catch (Exception e) {
                showNotification("Có lỗi xảy ra. Thêm không thành công.");
                closeStage(event);
            }
            studentPaneController.refreshTable();
            showNotification("Thêm thành công.");
            closeStage(event); 
        }
    }
    
     private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
     
     private void closeStage(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
     
    public void receiveData (StudentPaneController parentController)
    {
        studentPaneController = parentController;
    }
    @FXML
    public void handleAddImage(ActionEvent event) {
        
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(stage);
        System.out.println("file ne: " + file);
        if (file != null) {
            Image imageFile = new Image(file.toURI().toString(),175,225,true,true);
            System.out.println("file ne: " + imageFile);
            image.setImage(imageFile);
            image.setFitWidth(175);
            image.setFitHeight(225);
            image.setPreserveRatio(true);
        }
    }
    
    private void addDataToComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("Nam");
        items.add("Nữ");
        genderComboBox.setItems(items);
        genderComboBox.getSelectionModel().select(0);
    }
    
    private void addDataToTextfield(){
        student = new Student();
        int lastIndex = student.getLastStudentIndex() + 1;
        idStudentText.setText("ST"+ lastIndex);
    }
    
    private void DrawUI(){
        addDataToComboBox();
        addDataToTextfield();
    }

    
    
}
