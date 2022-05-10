/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class EditEWBillController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label ID, apartment, room, eNum, eFee, wNum, wFee, total;
    
    @FXML
    private TextField eStart,eEnd,wStart,wEnd;
    @FXML
    void back(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void reciveData(String data){
        ID.setText(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
