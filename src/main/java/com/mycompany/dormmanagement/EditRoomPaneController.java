/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Room;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class EditRoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Room room;
    private RoomPaneController roomPaneController;
    @FXML
    private Label apartmentLabel, roomLabel, statusLable,nostudentLable;
    @FXML
    private TextField  rentingpriceText; 
    @FXML
    private ComboBox typeComboBox;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void setDetailRoom (String  data){
        loadData(data);
    }
    public void receiveData (RoomPaneController parentController)
    {
        roomPaneController = parentController;
    }
    public void loadData(String billID){
        room = new Room();
        room.getInfo(billID);
        apartmentLabel.setText(room.getApartment().getIDApartment());
        roomLabel.setText(room.getRoomID());
        nostudentLable.setText(room.getNoStudent());
        statusLable.setText(room.getStatus());
        typeComboBox.setValue(room.getType());
        rentingpriceText.setText(String.valueOf(room.getRentingPrice()));
    }
    @FXML
    public void updatedata(ActionEvent event)
    {
        String roomtype = typeComboBox.getValue().toString();
        int RentingpriceText = (int)Double.parseDouble(rentingpriceText.getText());
        
        try {
            room.insertdatatoType(room.getRoomID(),roomtype,RentingpriceText);
        } catch (Exception e) {
            showNotification("Có lỗi xảy ra. Sửa không thành công.");
            closeStage(event);
        }
        showNotification("Sửa thành công.");
        roomPaneController.refreshTable();
        closeStage(event);
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
    private void addDataTypeCombobox(){ 
        ObservableList<String> items = FXCollections.<String>observableArrayList("2","4","6","8");
        typeComboBox.getItems().addAll(items);
    }
    private void DrawUI(){
        addDataTypeCombobox();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
