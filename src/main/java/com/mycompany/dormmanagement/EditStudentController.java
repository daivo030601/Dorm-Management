/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
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
public class EditStudentController implements Initializable {
    private StudentPaneController studentPaneController;
    private Student student;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField idStudentText;
    @FXML
    private DatePicker birthdayDatePicker;
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
    private Button updateBtn;
    @FXML
    private TextField statusText;
    @FXML
    private TextField idRoomText;
    @FXML
    private ImageView image;
    
    private Image imageFile;
    private File file ;
    private FileInputStream fis;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    

    @FXML
    private void getDate(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    private void updateData(ActionEvent event) {
        String name = nameText.getText();
        String idStudent = idStudentText.getText();
        Date birthday = java.sql.Date.valueOf(birthdayDatePicker.getValue());
        String idCard = idCardText.getText();
        String phone = phoneText.getText();
        String university = universityText.getText();
        String grade = gradeText.getText();
        String eYear = eYearText.getText();
        String sYear = sYearText.getText();
        String idRoom = idRoomText.getText();
        String gender = genderComboBox.getValue().toString();
        String status = statusText.getText();
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        student = new Student();
        
            try {
                student.updateStudent(idStudent,name,birthday,gender,idCard,phone,university,grade,status,sYear,eYear,idRoom,(InputStream)fis,(int)file.length());
            } catch (Exception e) {
                showNotification("Có lỗi xảy ra. Sửa không thành công.");
                closeStage(event);
            }
            showNotification("Sửa thành công.");
            studentPaneController.refreshTable();
            closeStage(event);
        
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
    
    public void setDetailStudent (String  data) throws IOException{
        loadData(data);
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
    
    private void addDataToGenderComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("Nam");
        items.add("Nữ");
        genderComboBox.setItems(items);
    }
    
    private void addDataToStatusComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("ĐX");
        items.add("CX");
        genderComboBox.setItems(items);
    }
    
    public void loadData(String studentID) throws FileNotFoundException, IOException{
        student = new Student();
        student.getInfoByID(studentID);
        nameText.setText(student.getFullName());
        genderComboBox.getSelectionModel().select(student.getGender());
        statusText.setText(student.getStatus());
        idStudentText.setText(student.getStudentID());
        birthdayDatePicker.setValue(student.getBirthday().toLocalDate());
        idCardText.setText(student.getIDCard());
        phoneText.setText(student.getPhoneNum());
        universityText.setText(student.getUniversity());
        gradeText.setText(student.getGrade());
        sYearText.setText(student.getSyear());
        eYearText.setText(student.getEyear());
        idRoomText.setText(student.getIdRoom());
        if (student.getIdRoom() == null) {
            idRoomText.setDisable(true);
        }
        InputStream is = student.getImage();
        OutputStream os = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        while((size = is.read(content)) != -1) {
            os.write(content, 0, size);
        }
        os.close();
        is.close();
        
        imageFile = new Image("file:photo.jpg", 175, 225,true, true);
        image.setImage(imageFile);
        image.setFitWidth(175);
        image.setFitHeight(225);
        image.setPreserveRatio(true);
    }
    
    private void DrawUI(){
        addDataToGenderComboBox();
        addDataToStatusComboBox();
    }
    
}
