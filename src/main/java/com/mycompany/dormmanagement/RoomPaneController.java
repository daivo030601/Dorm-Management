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
import com.mycompany.dormmanagement.Model.Student;
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
import javafx.scene.control.Alert;
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
    //x??? l?? khi ch???n gi?? tr??? combobox
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
    //x??? l?? khi ch???n c??c checkbox
    @FXML
    void checkBoxHandles(ActionEvent event){
        String keyWord = searchText.getText();
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);
        if(event.getSource()== allBox){
            doneBox.setSelected(false);
            unDoneBox.setSelected(false);
            //clear table sau ???? th??m l???i d??? li???u m???i theo gi?? tr??? checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,1);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1,keyWord));
        }
        if(event.getSource()== doneBox){
            allBox.setSelected(false);
            unDoneBox.setSelected(false);
            //clear table sau ???? th??m l???i d??? li???u m???i theo gi?? tr??? checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,2);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2,keyWord));
        }
        if(event.getSource()==unDoneBox){
            doneBox.setSelected(false);
            allBox.setSelected(false);
            //clear table sau ???? th??m l???i d??? li???u m???i theo gi?? tr??? checkbox
            dataTableView.getItems().clear();
            if(searchText.getText().isEmpty())
            addDataToTable(dataTableView,3);
            else dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
        } 
    }
    //x??? l?? khi v??n b???n search thay ?????i
    @FXML
    void textChange(KeyEvent event){
       tableSearch();
       showAllBtn.setVisible(true);
       
    }
    //h??m l???y d??? li???u t???t c??? sinh vi??n
    @FXML
    void showAll(ActionEvent event){
          dataTableView.getItems().clear();
          if(allBox.isSelected()) addDataToTable(dataTableView, 1);
          else if(doneBox.isSelected()) addDataToTable(dataTableView, 2);
          else addDataToTable(dataTableView, 3);
          searchText.clear();
          showAllBtn.setVisible(false);
    }
    //x??? l?? khi nh???n n??t th??m ph??ng m???i
    @FXML
    void addRoom(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/addRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Th??m ph??ng m???i");
        AddRoomPaneController controller= loader.getController();
        controller.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
        }
        
    }
    
    private void showNotification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng b??o");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
    }
    
    //h??m render l???i table
    public void refreshTable(){
        //ki???m tra gi?? tr??? c??c checkbox v?? l???y d??? li???u theo gi?? tr??? ????
        if(searchText.getText().isEmpty()){
            dataTableView.getItems().clear();
        if(allBox.isSelected()){       
            addDataToTable(dataTableView,1);
        }
        if(doneBox.isSelected()){     
            addDataToTable(dataTableView,2);
        }
        if(unDoneBox.isSelected()){      
            addDataToTable(dataTableView,3);
        }
        }else tableSearch();   
    }
    
    //h??m x??? l?? khi t??m ki???m b???ng thanh search
    private void tableSearch(){
       String keyWord = searchText.getText();
       dataTableView.getItems().clear();
       room = new Room();
       //l???y d??? li???u t??a t??? combobox
       String crApartment = apartmentComboBox.getValue().toString().substring(4);
       //ki???m tra gi?? tr??? c??c checkbox v?? l???y d??? li???u d???a tr??n thanh search, checkbox v?? gi?? tr??? t??a
       if(allBox.isSelected()) { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 1, keyWord));
       } else if(doneBox.isSelected()){ dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 2, keyWord));
       } else { dataTableView.getItems().addAll(room.getSearchRoom(crApartment, 3, keyWord));
       }
    }
    //h??m t???o table
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
    
    //h??m th??m d??? li???u v??o table (option l?? gi?? tr??? t????ng ???ng c???a checkbox)
    private void addDataToTable(TableView table,int option){      
        room = new Room();
        String crApartment = apartmentComboBox.getValue().toString().substring(4);   
        // l???y d??? li???u t??y v??o c??c gi?? tr??? c???a checkbox
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
    
    //h??m th??m c??c n??t v??o t???ng h??ng tr??n table
    private void addButtonToTable() {     
        Callback<TableColumn<Object, String>, TableCell<Object, String>> cellFactory = (TableColumn<Object, String> param) -> {
            // t???o ra h??ng ch???a c??c n??t
            final TableCell<Object, String> cell = new TableCell<Object, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ki???m tra item c?? null hay kh??ng n???u kh??ng th?? th??m c??c n??t
                    if (empty) {
                        setGraphic(null);
                    } else {
                        //t???o c??c n??t b??m v???i c??c h??nh ???nh icon v?? background color
                        Button btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
                        btnDetail.setStyle("-fx-background-color: transparent;");
                        Button btnEdit = new Button("",new ImageView("/Image/edit.png"));
                        btnEdit.setStyle("-fx-background-color: transparent;");
                        Button btnDelete = new Button("",new ImageView("/Image/delete.png"));
                        btnDelete.setStyle("-fx-background-color: transparent;");
                        //c??i s??? ki???n khi nh???n n??t cho t???ng n??t b???m
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
                            int index = getIndex();
                            String data = (String) indexCol.getCellObservableValue(index).getValue();
                                changeRoomDelete(data);
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
    
    //l???y d??? li???u th??m v??o combobox
    private void addDataToCombobox(ComboBox comboBox){
        apartment = new Apartment();
        ObservableList<String> items = FXCollections.<String>observableArrayList();
        //l???y d??? li???u t???t c??? c??c t??a r???i th??m v??o combobox
        for(var item : apartment.getAllApartment(1)){
            items.add("T??a "+ item);
        }
        comboBox.getItems().addAll(items);
        
    }
    
    //x??? l?? s??? ki???n khi b???m v??o n??t detail c???a t???ng item
    public void changeRoomDetail(String data)
    {
        try{
        //m??? m???t m??n h??nh th??ng tin m???i
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/detailRoomPane.fxml"));
        Parent roomdetial = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Chi ti???t th??ng tin ph??ng");
        //set Controller cho m??n h??nh m???i
        DetailRoomPaneController detailRoomPaneController = loader.getController();
        detailRoomPaneController.setDetailRoom(data,this);
        stage.setScene(new Scene(roomdetial));
        stage.show();
        } catch (IOException e) {
            System.out.println(e);
        } 
    }
    
    //x??? l?? s??? ki???n khi b???m v??o n??t add c???a t???ng item
    private void changeRoomEdit(String data){
        try {
        //m??? m???t m??n h??nh th??ng tin m???i
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editRoomPane.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ch???nh s???a th??ng tin ph??ng");
        EditRoomPaneController editRoomPaneController = loader.getController();
        editRoomPaneController.setDetailRoom(data);
        //set Controller cho m??n h??nh m???i
        EditRoomPaneController controller= loader.getController();
        controller.receiveData(this);
        editRoomPaneController.receiveData(this);
        stage.setScene(new Scene(root));
        stage.show();   
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //x??? l?? s??? ki???n khi b???m v??o n??t delete c???a t???ng item
    private void changeRoomDelete(String data){
         Student student = new Student();
            student.updateStudentRemoveFromRoom(data);
        try {
            room.deleteData(data);
            showNotification("X??a th??nh c??ng");
            refreshTable();
        } catch (Exception e) {
            showtification("C?? l???i x???y ra. Th??m kh??ng th??nh c??ng.");
        }
        
        System.out.println("success");
    }
    
    //h??m hi???n th??? th??ng b??o
    private void showtification(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Th??ng b??o");
        alert.setHeaderText(null);
	alert.setContentText(msg);
	alert.showAndWait();
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
