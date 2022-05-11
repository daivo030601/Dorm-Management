/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.dormmanagement;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import com.mycompany.dormmanagement.Model.Apartment;
import com.mycompany.dormmanagement.Model.Room;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import se.alipsa.ymp.*;

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
    private TextField searchText;
    @FXML
    private Button showAllBtn;
    
    @FXML
    void selectHandle(ActionEvent event){
        if(searchText.getText().isEmpty()){
        dataTableView.getItems().clear();
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
        }else tableSearch();
    }
    @FXML
    void checkBoxHandles(ActionEvent event){
        String keyWord = searchText.getText();
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);
        if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1,keyWord));
        }
        if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2,keyWord));
        }
        if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
        } 
    }
    @FXML
    void textChange(KeyEvent event){
       tableSearch();
       showAllBtn.setVisible(true);
       
    }
    @FXML
    void showAll(ActionEvent event){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          searchText.clear();
          showAllBtn.setVisible(false);
    }
    @FXML
    void addRoom(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm phòng mới");
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
        }
        
    }
    
    private void tableSearch(){
       String keyWord = searchText.getText();
       dataTableView.getItems().clear();
       room = new Room();
       String crApartment = apartmentComboBox.getValue().toString().substring(4);
       if(allBox.isSelected()) { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1, keyWord));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2, keyWord));
       } else { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
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
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                            changeRoomDetail(data);
                        });
                        btnEdit.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                            changeRoomEdit(data);
                            
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
    public void changeRoomDetail(String data)
    {
        try{
   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailRoomPane.fxml"));
        Parent roomdetial = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chi tiết thông tin phòng");
        DetailRoomPaneController detailRoomPaneController = loader.getController();
        detailRoomPaneController.setDetailRoom(String.valueOf(room.getApartment()),room.getRoomID(),room.getNoStudent(),room.getStatus(),room.getType(),String.valueOf(room.getRentingPrice()));
        stage.setScene(new Scene(roomdetial));
        stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("success");
    }
    private void changeRoomEdit(String data){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chỉnh sửa thông tin phòng");
        EditRoomPaneController editRoomPaneController = loader.getController();
        editRoomPaneController.setDetailRoom(data);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("success");
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
