/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Room;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class DetailRoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Room room;
    @FXML
    private Label apartmentLabel, roomLable, typeLable, nostudentLable, statusLable, rentingpriceLable;
    @FXML
    private Button apartmentbtn;
    public void setDetailRoom (ActionEvent event){
        apartmentLabel.setText(String.valueOf(room.getApartment()));
        roomLable.setText(String.valueOf(room.getRoomID()));
        typeLable.setText(String.valueOf(room.getType()));
        nostudentLable.setText(String.valueOf(room.getNoStudent()));
        statusLable.setText(String.valueOf(room.getStatus()));
        rentingpriceLable.setText(String.valueOf(room.getRentingPrice()));
    }
    private void DrawUI(){
        
        
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    

    
}
