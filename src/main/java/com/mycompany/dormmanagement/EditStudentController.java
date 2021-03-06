/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import Utils.DataValidation;
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
import javafx.scene.control.Label;

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
    @FXML
    private Label nameError, IDCardError, phoneNumError, universityError, yearError,sYearError,eYearError;
    
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
            updateBtn.setDisable(false);
        } else {
            updateBtn.setDisable(true);
        }
    
    }
    
    //x??? l?? khi nh???n nut c???p nh???p
    @FXML
    private void updateData(ActionEvent event) {
        // l???y d??? li???u t??? ng?????i d??ng nh???p v??o
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
                showNotification("C?? l???i x???y ra. S???a kh??ng th??nh c??ng.");
                closeStage(event);
            }
            showNotification("S???a th??nh c??ng.");
            studentPaneController.refreshTable();
            closeStage(event);
        
    }
    //x??? l?? n??t th??m ???nh
    @FXML
    public void handleAddImage(ActionEvent event) {
        // m??? state m???i ????? ng?????i d??ng ch???n ???nh v?? l??u ???nh ???? v??o file
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
            updateBtn.setDisable(false);
        }
    }
    
    public void setDetailStudent (String  data) throws IOException{
        loadData(data);
    }
    // hi???n th??? th??ng b??o
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
    // th??m data v??o gender combobox
    private void addDataToGenderComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("Nam");
        items.add("N???");
        genderComboBox.setItems(items);
    }
    // th??m data v??o status combobox
    private void addDataToStatusComboBox(){
        ObservableList<String> items = FXCollections.<String>observableArrayList();       
        items.add("??X");
        items.add("CX");
        genderComboBox.setItems(items);
    }
    //h??m l???y d??? li???u v?? hi???n th??? l??n m??n h??nh
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
        // l???y d??? li???u ???nh tronng database
        try {
            InputStream is = student.getImage();
            //l??u d??? li???u v??o m???t file photo.jpg
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
            os.close();
            is.close();
            //hi???n th??? file photo.jpg l??n imageView
            imageFile = new Image("file:photo.jpg", 175, 225,true, true);
            file = new File("photo.jpg");
            image.setImage(imageFile);
            image.setFitWidth(175);
            image.setFitHeight(225);
            image.setPreserveRatio(true);
            } catch (Exception e) {
        }
        
    }
    
    private void DrawUI(){
        addDataToGenderComboBox();
        addDataToStatusComboBox();
    }
    
}
