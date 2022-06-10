/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dormmanagement;

import com.mycompany.dormmanagement.Model.ElectricAndWaterBill;
import com.mycompany.dormmanagement.Model.Room;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
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
    private TableView dataTableView;
    @FXML
    private TableColumn idstudentCol, nameCol,genderCol,universityCol;
    @FXML
    private Label apartmentLable, roomLable, typeLable, nostudentLable, statusLable, rentingpriceLable;
    //xử lý khi nhấn nút quay lại
    @FXML        
    void backbtn(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void setDetailRoom (String data){
        loadData(data);
        
    }
    
    //lấy dữ liệu và hiển thị ra các label
    public void loadData(String billID){
        
        room = new Room();
        room.getInfo(billID);
        apartmentLable.setText(room.getApartment().getIDApartment());
        roomLable.setText(room.getRoomID());
        typeLable.setText(room.getType());
        nostudentLable.setText(room.getNoStudent());
        statusLable.setText(room.getStatus());
        rentingpriceLable.setText(String.valueOf(room.getRentingPrice()));
        initTableView(dataTableView); 
    }
    
    //tạo một table
    private void initTableView(TableView table){
        
        idstudentCol.setCellValueFactory(new MapValueFactory<>("IDStudent"));
        nameCol.setCellValueFactory(new MapValueFactory<>("Fullname"));
        genderCol.setCellValueFactory(new MapValueFactory<>("Gender"));
        universityCol.setCellValueFactory(new MapValueFactory<>("University"));
        
        idstudentCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));  
        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        universityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3)); 
        addDataToTable(table,1);     
    }
    
    //hàm thêm dữ liệu vào table
    private void addDataToTable(TableView table,int option){      
        room = new Room();
        String roomid = roomLable.getText().toString();
        table.getItems().addAll(room.getStudentRoom(roomid,1));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
