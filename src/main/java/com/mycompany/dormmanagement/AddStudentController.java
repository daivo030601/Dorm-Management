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
import Utils.DataValidation;
import Utils.RemoveAccent;
import java.time.LocalDate;
import javafx.scene.input.KeyEvent;
import Utils.RemoveAccent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddStudentController implements Initializable {
    private StudentPaneController studentPaneController;
    private Student student;
    @FXML
    private Label IDCardError;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private Label eYearError;

    @FXML
    private TextField eYearText;

    @FXML
    private ComboBox genderComboBox;

    @FXML
    private TextField gradeText;

    @FXML
    private TextField idCardText;

    @FXML
    private TextField idStudentText;

    @FXML
    private Label nameError;

    @FXML
    private TextField nameText;

    @FXML
    private Label phoneNumError;

    @FXML
    private TextField phoneText;

    @FXML
    private Label sYearError;

    @FXML
    private TextField sYearText;

    @FXML
    private Label universityError;

    @FXML
    private TextField universityText;

    @FXML

    private Label yearError;
    
    @FXML
    private Button insertBtn;

    private Button addImageBtn;
    @FXML
    private ImageView image;
    private File file ;
    private FileInputStream fis;

    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawUI();
    }    

    @FXML
    private void back(ActionEvent event) {
        closeStage(event);
    }
    
    //h??m x??? l?? khi v??n b???n thay ?????i
    @FXML
    void TextChange(){
        
        
        //check name text field null or empty
        if(DataValidation.textFieldIsNull(nameText, nameError, "Vui l??ng kh??ng ????? tr???ng")){
            nameError.setVisible(true);
        } //check valid character, name is alphabet?
        else if(!DataValidation.textAlphabet(nameText, nameError, "T??n ch???a k?? t??? kh??ng h???p l???")){
            nameError.setVisible(true);
        } else nameError.setVisible(false);      
        
        //check IDCard text field null or empty
        if(DataValidation.textFieldIsNull(idCardText, IDCardError, "Vui l??ng kh??ng ????? tr???ng")){
            IDCardError.setVisible(true);
        } //ID card must be a numberic
        else if(!DataValidation.textNumeric(idCardText, IDCardError , "CMND/CCCD ph???i l?? s???")){
            IDCardError.setVisible(true);
        } else IDCardError.setVisible(false);
        
        //check phone number null or empty
        if(DataValidation.textFieldIsNull(phoneText, phoneNumError, "Vui l??ng kh??ng ????? tr???ng")){
            phoneNumError.setVisible(true);
        } //phone number must be a numberic
        else if(!DataValidation.textNumeric(phoneText, phoneNumError , "S??? ??i???n tho???i ph???i l?? s???")){
            phoneNumError.setVisible(true);
        }//phone number larger than 10 character 
        else if(!DataValidation.dataLengthMinMax(phoneText, phoneNumError, "S??? ??i???n tho???i ph???i c?? ??t nh???t 10 s???", "10","50")){
            phoneNumError.setVisible(true);
        } else phoneNumError.setVisible(false);
        
        //check university null or empty
        if(DataValidation.textFieldIsNull(universityText, universityError, "Vui l??ng kh??ng ????? tr???ng")){
            universityError.setVisible(true);
        } else universityError.setVisible(false);
        
        //check grade null or empty
        if(DataValidation.textFieldIsNull(gradeText, yearError, "Vui l??ng kh??ng ????? tr???ng")){
            yearError.setVisible(true);
        } //grade must be a numberic
        else if(!DataValidation.textNumeric(gradeText, yearError, "Vui l??ng nh???p n??m ??ang h???c l?? s???")){
            yearError.setVisible(true);
        } //max year study in VietNam university is 9
        else if(Integer.parseInt(gradeText.getText().trim())> 9) {
            yearError.setText("N??m h???c t???i ??a l?? 9 n??m");
            yearError.setVisible(true);
        } else yearError.setVisible(false);
        
        //check start year null or empty
        if(DataValidation.textFieldIsNull(sYearText, sYearError, "Vui l??ng kh??ng ????? tr???ng")){
            sYearError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(sYearText, sYearError, "N??m b???t ?????u h???c ph???i ph???i l?? s???")){
            sYearError.setVisible(true);
        } 
        //check if text field value >0, if not show the error because renting price must larger than 0
        else if(Integer.parseInt(sYearText.getText().trim())<=0){
            sYearError.setText("N??m b???t ?????u h???c ph???i > 0");
            sYearError.setVisible(true);
        } else sYearError.setVisible(false);
        
        //check end year null or empty
        if(DataValidation.textFieldIsNull(eYearText, eYearError, "Vui l??ng kh??ng ????? tr???ng")){
            eYearError.setVisible(true);
        } 
        // check if text field value is number, if not show the error
        else if(!DataValidation.textNumeric(eYearText, eYearError, "N??m b???t ?????u h???c ph???i ph???i l?? s???")){
            eYearError.setVisible(true);
        }
        //check if text field value >0, if not show the error because renting price must larger than 0
        else if(Integer.parseInt(eYearText.getText().trim())<=0){
            eYearError.setText("N??m b???t ?????u h???c ph???i > 0");
            eYearError.setVisible(true);
        } else eYearError.setVisible(false);
        
        //check when start year and end year not null end year must larger than start year 
        if(!sYearText.getText().trim().isEmpty() && !eYearText.getText().trim().isEmpty()){
            int sYear = -1;
            int eYear = -1;
            try {
                sYear = Integer.parseInt(sYearText.getText().trim());
                eYear = Integer.parseInt(eYearText.getText().trim());
                if(sYear >= eYear) {
                eYearError.setText("N??m k???t th??c ph???i l???n h??n n??m b???t ?????u");
                eYearError.setVisible(true);
                } else eYearError.setVisible(false);
            } catch (NumberFormatException e) {
            }    
        }
        
        if(!nameError.isVisible() && !IDCardError.isVisible() && !phoneNumError.isVisible() && !universityError.isVisible() && !yearError.isVisible() && !sYearError.isVisible() && !eYearError.isVisible()){
            insertBtn.setDisable(false);
        } else {
            insertBtn.setDisable(true);
        }
    }
    
    //x??? l?? n??t th??m m???i
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
        //ki???m tra n???u ng?????i kh??ng th??m h??nh th?? l???y file h??nh m???c ?????nh l??m avatar
        if (file == null) {
            file = new File("avatar.jpg");
        }
        //ki???m tra th??ng tin n???u tr??ng th?? kh??ng cho th??m d??? li???u
        if (name.isEmpty() || idStudent.isEmpty() || birthday.toString().isEmpty() || idCard.isEmpty() || phone.isEmpty() || university.isEmpty() || grade.isEmpty() || sYear.isEmpty() || eYear.isEmpty()) {
            showNotification("B???n ch??a ??i???n ?????y ????? th??ng tin! Xin vui l??ng ??i???n ?????y ????? tr?????c khi th??m sinh vi??n.");
        } else {
            // t???o InputStream t??? file ???nh ng?????i d??ng ch???n ????? th??m v??o database
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            student = new Student(idStudent, name, birthday, gender, idCard, phone, university, grade, "CX", sYear, eYear, null, (InputStream)fis);
            try {
            student.insertStudentdata((int)file.length());
           
            } catch (Exception e) {
                showNotification("C?? l???i x???y ra. Th??m kh??ng th??nh c??ng.");
                closeStage(event);
            }
            studentPaneController.refreshTable();
            showNotification("Th??m th??nh c??ng.");
            closeStage(event); 
        }
    }
    //hi???n th??? th??ng b??o
     private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng b??o");
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
    
    //x??? l?? n??t th??m ???nh
    @FXML
    public void handleAddImage(ActionEvent event) {
        //m??? folder ????? ng?????i d??ng ch???n ???nh t??? m??y t??nh
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(stage);
        System.out.println("file ne: " + file);
        //ki???m tra n???u file kh??c null th?? hi???n th??? tr??n ImageView
        if (file != null) {
            Image imageFile = new Image(file.toURI().toString(),175,225,true,true);
            System.out.println("file ne: " + imageFile);
            image.setImage(imageFile);
            image.setFitWidth(175);
            image.setFitHeight(225);
            image.setPreserveRatio(true);
        }
    }
    //h??m th??m d??? li???u v??o combobox
    private void addDataToComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("Nam");
        items.add("N???");
        genderComboBox.setItems(items);
        genderComboBox.getSelectionModel().select(0);
    }
    //h??m th??m d??? li???u v??o Textfield
    private void addDataToTextfield(){
        student = new Student();
        int lastIndex = student.getLastStudentIndex() + 1;
        idStudentText.setText("ST"+ lastIndex);
    }
    
    private void DrawUI(){
        addDataToComboBox();
        addDataToTextfield();
        birthdayDatePicker.setValue(LocalDate.now());
    }

    
    
}
