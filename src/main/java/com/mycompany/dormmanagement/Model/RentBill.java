/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import java.util.Date;

/**
 *
 * @author Mayy
 */
public class RentBill extends Bill{
    private Student student;

    public RentBill(Student student, String billID, Employee employee, Apartment apartment, Room room, Date createDay, String total,String status) {
        super(billID, employee, apartment, room, createDay, total,status);
        this.student = student;
    }

    public RentBill() {
        super();
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    


    
    
}
