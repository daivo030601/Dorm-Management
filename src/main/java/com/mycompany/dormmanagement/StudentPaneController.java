/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Student;
import connect.DataConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class StudentPaneController implements Initializable {
    private Student student;
    @FXML
    private TableView dataTableView;
    @FXML
    private TableColumn idCol, nameCol, genderCol, statusCol, universityCol, sYearCol, eYearCol, toolCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    void checkBoxHandles(ActionEvent event){
      if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            addDataToTable(dataTableView,1);
      }
      if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            addDataToTable(dataTableView,2);
      }
      if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            addDataToTable(dataTableView,3);
      } 
    }
    
    private void DrawUI(){
        allBox.setSelected(true);
        initTable(dataTableView);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
        
    }      
    
    private void initTable(TableView table) {
        idCol.setCellValueFactory(new MapValueFactory<>("id"));
        nameCol.setCellValueFactory(new MapValueFactory<>("name"));
        genderCol.setCellValueFactory(new MapValueFactory<>("gender"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        universityCol.setCellValueFactory(new MapValueFactory<>("university"));
        sYearCol.setCellValueFactory(new MapValueFactory<>("sYear"));
        eYearCol.setCellValueFactory(new MapValueFactory<>("eYear"));
        
        idCol.prefWidthProperty().bind(table.widthProperty().multiply(0.06));  
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12)); 
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13)); 
        universityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13)); 
        sYearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        eYearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        addDataToTable(table,1);
    }
    
    private void addDataToTable(TableView table,int option){      
        student = new Student();  
        switch (option) {
                case 1:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(student.getStudent(option));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(student.getStudent(1));
                    addButtonToTable();
                    break;
            }
    }
    private void addButtonToTable() {     
        Callback<TableColumn<Object, String>, TableCell<Object, String>> cellFactory = (TableColumn<Object, String> param) -> {
            // make cell containing buttons
            final TableCell<Object, String> cell = new TableCell<Object, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Button btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
                        btnDetail.setStyle("-fx-background-color: transparent;");
                        Button btnEdit = new Button("",new ImageView("/Image/edit.png"));
                        btnEdit.setStyle("-fx-background-color: transparent;");
                        Button btnDelete = new Button("",new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        btnDetail.setOnAction((ActionEvent event) -> {
                            int i = getIndex();
                            System.out.println("A"+i);
                        });
                        btnEdit.setOnAction((ActionEvent event) -> {
                            int i = getIndex();
                            System.out.println("B"+i);
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            int i = getIndex();
                            System.out.println("C"+i);
                        });
                        HBox btnManage = new HBox(btnDetail, btnEdit, btnDelete);
                        btnManage.setStyle("-fx-alignment:center");                   
                        setGraphic(btnManage);
                    }
                }
            };
            return cell;         
        };

        toolCol.setCellFactory(cellFactory);
    }
}
