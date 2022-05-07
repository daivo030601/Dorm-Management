/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import java.sql.Date;
import javafx.collections.ObservableList;

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

    public Student() {
        this.studentID = "";
        this.fullName = "";
        this.birthday = null;
        this.Gender = "";
        this.IDCard = "";
        this.phoneNum = "";
        this.university = "";
        this.grade = "";
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
    
//    public ObservableList<Map<String >>
    
}
