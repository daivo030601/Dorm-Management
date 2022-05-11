/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Employee;
import com.mycompany.dormmanagement.Model.Room;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class AddRoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button insertbtn;
    @FXML
    private TextField roomText,nostudentText,statusText,rentingpriceText;
    private Apartment apartment;
    private Room room;
    private Employee employee;
    @FXML
    private ComboBox apartmentComboBox,typeComboBox;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
