/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DetailStudentController implements Initializable {

    @FXML
    private Label idStudentLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label birthLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label idCardLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label universityLabel;
    @FXML
    private Label gradeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label sYearLabel;
    @FXML
    private Label eYearLabel;
    @FXML
    private Label idRoomLabel;

    /**
     * Initializes the controller class.
     */
    private Student student;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void setDetailStudent (String data){
        loadData(data);
        
    }
    public void loadData(String studentID){
        
        student = new Student();
        student.getInfoByID(studentID);
        System.out.println("id: "+studentID + "name: " +student.getFullName());
        idStudentLabel.setText(student.getStudentID());
        nameLabel.setText(student.getFullName());
        birthLabel.setText("alo");
        genderLabel.setText(student.getGender());
        phoneLabel.setText(student.getPhoneNum());
        universityLabel.setText(student.getUniversity());
        gradeLabel.setText(student.getGrade());
        statusLabel.setText(student.getStatus());
        sYearLabel.setText(student.getSyear());
        eYearLabel.setText(student.getEyear());
        if (student.getIdRoom() != null) {
            idRoomLabel.setText(student.getIdRoom());
        } else {
            idRoomLabel.setText("Chưa có phòng ở");
        }
    }
    
}
