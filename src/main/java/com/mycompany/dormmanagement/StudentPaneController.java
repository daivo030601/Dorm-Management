/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Student;
import connect.DataConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class StudentPaneController implements Initializable {
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> idCol;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> birthCol;
    @FXML
    private TableColumn<Student, String> genderCol;
    @FXML
    private TableColumn<Student, String> idCardCol;
    @FXML
    private TableColumn<Student, String> phoneNumCol;
    @FXML
    private TableColumn<Student, String> universityCol;
    @FXML
    private TableColumn<Student, String> gradeCol;
    
    ObservableList<Student> studentList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = DataConnection.getConnection();
            ResultSet rs =con.createStatement().executeQuery("SELECT * FROM quanlyktx.student");
            while (rs.next()) {
                studentList.add(new Student(rs.getString("studentID"), rs.getString("fullName"), rs.getDate("birthday"), rs.getString("Gender"), rs.getString("IDCard"), rs.getString("phoneNum"), rs.getString("university"), rs.getString("grade")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateTable();
    }    
    
    private void updateTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        idCardCol.setCellValueFactory(new PropertyValueFactory<>("IDCard"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        universityCol.setCellValueFactory(new PropertyValueFactory<>("university"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        System.out.print("dATA.........: " + studentList);
        studentsTable.setItems(studentList);
    }
}
