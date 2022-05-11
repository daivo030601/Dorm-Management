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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/*
 * FXML Controller class
 *
 * @author DVHHHTNT
 */
public class DetailRoomPaneController implements Initializable {

    /*
     *
     * Initializes the controller class.
     */
    private Room room;
    @FXML
    private Label apartmentLable, roomLable, typeLable, nostudentLable, statusLable, rentingpriceLable;
    @FXML
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void setDetailRoom (String apartment,String room,String type,String nostudent,String status,String rentingprice){
        apartmentLable.setText(apartment);
        roomLable.setText(room);
        typeLable.setText(type);
        nostudentLable.setText(nostudent);
        statusLable.setText(status);
        rentingpriceLable.setText(rentingprice);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    
}