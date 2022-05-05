/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

/**
 *
 * @author Mayy
 */
public class ElectricAndWaterBill extends Bill {
   protected double ChiSoDauDien;
   protected double ChiSoCuoiDien;
   protected double ChiSoDauNuoc;
   protected double ChiSoCuoiNuoc;

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc, String BillID, Employee employee, Apartment apartment, Room room, String createDay, String total) {
        super(BillID, employee, apartment, room, createDay, total);
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
    }

    public ElectricAndWaterBill(double ChiSoDauDien, double ChiSoCuoiDien, double ChiSoDauNuoc, double ChiSoCuoiNuoc) {
        this.ChiSoDauDien = ChiSoDauDien;
        this.ChiSoCuoiDien = ChiSoCuoiDien;
        this.ChiSoDauNuoc = ChiSoDauNuoc;
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
    }
   
    public ElectricAndWaterBill() {
        this.billID = "";
        this.employee = new Employee();
        this.apartment = new Apartment();
        this.room = new Room();
        this.createDay = "";
        this.total = "0";
        this.ChiSoDauDien = 0.0;
        this.ChiSoCuoiDien = 0.0;
        this.ChiSoDauNuoc = 0.0;
        this.ChiSoCuoiNuoc = 0.0;
    }

    public double getChiSoDauDien() {
        return ChiSoDauDien;
    }

    public void setChiSoDauDien(double ChiSoDauDien) {
        this.ChiSoDauDien = ChiSoDauDien;
    }

    public double getChiSoCuoiDien() {
        return ChiSoCuoiDien;
    }

    public void setChiSoCuoiDien(double ChiSoCuoiDien) {
        this.ChiSoCuoiDien = ChiSoCuoiDien;
    }

    public double getChiSoDauNuoc() {
        return ChiSoDauNuoc;
    }

    public void setChiSoDauNuoc(double ChiSoDauNuoc) {
        this.ChiSoDauNuoc = ChiSoDauNuoc;
    }

    public double getChiSoCuoiNuoc() {
        return ChiSoCuoiNuoc;
    }

    public void setChiSoCuoiNuoc(double ChiSoCuoiNuoc) {
        this.ChiSoCuoiNuoc = ChiSoCuoiNuoc;
    }
 
}
