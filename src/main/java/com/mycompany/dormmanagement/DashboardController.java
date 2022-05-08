/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Pane view;
    @FXML
    private Pane homePane, aboutPane, contactPane, feedbackPane, homeMainView;
    @FXML
    private Button homeBtn, aboutBtn, contactBtn, feedbackBtn, roomBtn, studentBtn, ewBillBtn, rentBillBtn, reportBtn;
    @FXML
    private Label crUsername, crPermission;
    @FXML
    private ImageView logo;
    
    @FXML
    void handleClicks(ActionEvent event){
    if(event.getSource()== homeBtn){
    homePane.toFront();
    homeBtn.setStyle("-fx-opacity: 100%");
    aboutBtn.setStyle("-fx-opacity: 47%");
    contactBtn.setStyle("-fx-opacity: 47%");
    feedbackBtn.setStyle("-fx-opacity: 47%");
    }
    if(event.getSource()==aboutBtn){
    aboutPane.toFront();
    homeBtn.setStyle("-fx-opacity: 47%");
    aboutBtn.setStyle("-fx-opacity: 100%");
    contactBtn.setStyle("-fx-opacity: 47%");
    feedbackBtn.setStyle("-fx-opacity: 47%");
    }
    if(event.getSource()==contactBtn){
    contactPane.toFront();
    homeBtn.setStyle("-fx-opacity: 47%");
    aboutBtn.setStyle("-fx-opacity: 47%");
    contactBtn.setStyle("-fx-opacity: 100%");
    feedbackBtn.setStyle("-fx-opacity: 47%");
    }
    if(event.getSource()==feedbackBtn){
    feedbackPane.toFront();
    homeBtn.setStyle("-fx-opacity: 47%");
    aboutBtn.setStyle("-fx-opacity: 47%");
    contactBtn.setStyle("-fx-opacity: 47%");
    feedbackBtn.setStyle("-fx-opacity: 100%");
    } 
    if(event.getSource()==roomBtn){
    homeMainView.getChildren().setAll(getPane("/View/roomPane.fxml"));
    roomBtn.setStyle("-fx-text-fill: #2CA8E9");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==studentBtn){
    homeMainView.getChildren().setAll(getPane("/View/studentPane.fxml"));    
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: #2CA8E9");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==ewBillBtn){
    homeMainView.getChildren().setAll(getPane("/View/ewBillPane.fxml"));
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: #2CA8E9");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==rentBillBtn){
    homeMainView.getChildren().setAll(getPane("/View/rentBillPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: #2CA8E9");
    reportBtn.setStyle("-fx-text-fill: black");
    }
     if(event.getSource()==reportBtn){
    homeMainView.getChildren().setAll(getPane("/View/reportPane.fxml"));     
    roomBtn.setStyle("-fx-text-fill: black");
    studentBtn.setStyle("-fx-text-fill: black");
    ewBillBtn.setStyle("-fx-text-fill: black");
    rentBillBtn.setStyle("-fx-text-fill: black");
    reportBtn.setStyle("-fx-text-fill: #2CA8E9");
    }
    }
    private Pane getPane(String path){
        try {
            view = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return view;
    }
    private void DrawUI(){
    logo.setImage(new Image(getClass().getResourceAsStream("/Image/logo.png")));
    crUsername.setText(LoginFormController.currentUser.getUsername());
    crPermission.setText(LoginFormController.currentUser.getPermission());
    homePane.toFront();
    homeMainView.getChildren().setAll(getPane("/View/roomPane.fxml"));
    homeBtn.setStyle("-fx-opacity: 100%");
    roomBtn.setStyle("-fx-text-fill: #2CA8E9");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       DrawUI();
    }    
    
}