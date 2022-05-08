/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Room;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import se.alipsa.ymp.YearMonthPicker;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class RoomPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Apartment apartment;
    private Room room;
    @FXML
    private TableView dataTableView;
    @FXML
    private ComboBox apartmentComboBox;
    @FXML
    private TableColumn indexCol, nostudentCol,statusCol,typeCol,toolCol;
    @FXML
    private CheckBox allBox,doneBox,unDoneBox;
    @FXML
    private HBox box;
    @FXML
    void selectHandle(ActionEvent event){
        if(allBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,1);
        }
        if(doneBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,2);
        }
        if(unDoneBox.isSelected()){
        dataTableView.getItems().clear();
        addDataToTable(dataTableView,3);
        }
    }
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
    
    private void initTableView(TableView table){
        
        indexCol.setCellValueFactory(new MapValueFactory<>("idroom"));
        nostudentCol.setCellValueFactory(new MapValueFactory<>("nostudent"));
        statusCol.setCellValueFactory(new MapValueFactory<>("status"));
        typeCol.setCellValueFactory(new MapValueFactory<>("type"));
        
        indexCol.prefWidthProperty().bind(table.widthProperty().multiply(0.17));  
        nostudentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        statusCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        typeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2)); 
        toolCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        addDataToTable(table,1);
        
        
    }
    private void addDataToTable(TableView table,int option){      
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);    
        switch (option) {
                case 1:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                case 2:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                case 3:
                    table.getItems().addAll(room.getRoom(crApartment,option));
                    addButtonToTable();
                    break;
                default:
                    table.getItems().addAll(room.getRoom(crApartment,1));
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
    private void addDataToCombobox(ComboBox comboBox){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        for(var item : apartment.getAllApartment()){
            items.add("Tòa "+ item);
        }
        comboBox.getItems().addAll(items);
        
    }
    

    private void DrawUI(){
        allBox.setSelected(true);
        addDataToCombobox(apartmentComboBox);
        apartmentComboBox.getSelectionModel().select(0);
        initTableView(dataTableView);
        
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DrawUI();
    }    
    
}
