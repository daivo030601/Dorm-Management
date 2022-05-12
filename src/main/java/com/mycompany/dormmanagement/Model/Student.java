/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mayy
 */
public class Student {
    private String studentID;
    private String fullName;
    private Date birthday;
    private String Gender;
    private String IDCard;
    private String phoneNum;
    private String university;
    private String grade;
    protected Button btnDetail, btnEdit, btnDelete;

    public Student() {
        this.studentID = "";
        this.fullName = "";
        this.birthday = null;
        this.Gender = "";
        this.IDCard = "";
        this.phoneNum = "";
        this.university = "";
        this.grade = "";
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
    }

    
    public Student(String studentID, String fullName, Date birthday, String Gender, String IDCard, String phoneNum, String university, String grade) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.birthday = birthday;
        this.Gender = Gender;
        this.IDCard = IDCard;
        this.phoneNum = phoneNum;
        this.university = university;
        this.grade = grade;
        this.btnDetail = new Button("", new ImageView("/Image/viewdetails.png"));
        this.btnDetail.setStyle("-fx-background-color: transparent;");
        this.btnEdit = new Button("", new ImageView("/Image/edit.png"));
        this.btnEdit.setStyle("-fx-background-color: transparent;");
        this.btnDelete = new Button("", new ImageView("/Image/delete.png"));
        this.btnDelete.setStyle("-fx-background-color: transparent;");
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
    public ObservableList<Map<String, Object>> getStudent(int option){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from student";
                    break;
                case 2:
                    query = "Select * from student where status = 'ĐX'";
                    break;
                case 3:
                    query = "Select * from student where status = 'CX'";
                    break;
                default:
                    query = "Select * from student";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("name", resultSet.getString(2));
            item.put("gender", resultSet.getString(4));
            item.put("status", resultSet.getString(9));
            item.put("university", resultSet.getString(7));
            item.put("sYear", resultSet.getString(10));
            item.put("eYear", resultSet.getString(11));
            items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return items;
    }
    
    
    public ObservableList<Map<String, Object>> getSearchStudent(int option, String keyWord){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from student where IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%'";
                    break;
                case 2:
                    query = "Select * from student where status = 'ĐX' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
                case 3:
                    query = "Select * from student where status = 'CX' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
                default:
                    query = "Select * from student where IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("name", resultSet.getString(2));
            item.put("gender", resultSet.getString(4));
            item.put("status", resultSet.getString(9));
            item.put("university", resultSet.getString(7));
            item.put("sYear", resultSet.getString(10));
            item.put("eYear", resultSet.getString(11));
            items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return items;
    }
    
    public ArrayList<String> getAllStudent(){
    
        ArrayList<String> listStudent = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDStudent from student";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listStudent.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listStudent;
    }
    
}
