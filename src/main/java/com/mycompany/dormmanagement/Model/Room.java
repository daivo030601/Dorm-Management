/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

/**
 *
 * @author Mayy
 */
public class Room {
private String roomID;
private Apartment apartment;
private String noStudent;
private String status;
private String type;
private int rentingPrice;

    public Room() {
        this.roomID = "";
        this.apartment = new Apartment();
        this.noStudent = "";
        this.status = "";
        this.type = "";
        this.rentingPrice = 0;
    }

    public Room(String roomID, Apartment apartment, String noStudent, String status, String type, int rentingPrice) {
        this.roomID = roomID;
        this.apartment = apartment;
        this.noStudent = noStudent;
        this.status = status;
        this.type = type;
        this.rentingPrice = rentingPrice;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getNoStudent() {
        return noStudent;
    }

    public void setNoStudent(String noStudent) {
        this.noStudent = noStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(int rentingPrice) {
        this.rentingPrice = rentingPrice;
    }
    
    



}
