/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Room;
import com.mycompany.dormmanagement.Model.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;



/**
 * FXML Controller class
 *
 * @author Admin
 */
public class RoomArrangementFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    private TextFields;
    private Student student;
    private Apartment apartment;
    private Room room;
    @FXML
    private ComboBox apartmentComboBox, roomComboBox;
    @FXML
    private TextField nameText, genderText;
    @FXML
    private Button cancleBtn, addBtn;
    @FXML
    void selectHandle(ActionEvent event){
        room = new Room();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : room.getRoomAvailable(apartmentComboBox.getValue().toString())){
            items.add(item);
        }
        roomComboBox.getItems().clear();
        roomComboBox.getItems().addAll(items);
        roomComboBox.getSelectionModel().select(0);
    }
    
    private void autoCompleteText(TextField textField) {
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : student.getAllStudent()){
            items.add(item);
        }
//        System.out.println("alooooo: " + items);
//        TextFields
    }
    
    private void addApartmentToCombobox(ComboBox comboBox){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : apartment.getAllApartment()){
            items.add(item);
        }
        comboBox.getItems().addAll(items);
        
    }
    
    private void addRoomToCombobox(ComboBox comboBox){
        String crApartment = apartmentComboBox.getValue().toString();
        room = new Room();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : room.getRoomAvailable(crApartment)){
            items.add(item);
        }
        comboBox.getItems().addAll(items);
        
    }
    private void DrawUI(){
        addApartmentToCombobox(apartmentComboBox);
        apartmentComboBox.getSelectionModel().select(0);
//      
        addRoomToCombobox(roomComboBox);
        roomComboBox.getSelectionModel().select(0);
        
//        autoCompleteText(nameText);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DrawUI();
    }    
    
}
