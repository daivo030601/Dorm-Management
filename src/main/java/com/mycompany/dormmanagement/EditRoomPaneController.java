/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    @FXML
    private Label apartmentLabel, roomLable, statusLable;
    @FXML
    private TextField  rentingpriceText, nostudentText; 
    @FXML
    private ComboBox typeComboBox;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void setDetailRoom (String  data){
        apartmentLabel.setText(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
