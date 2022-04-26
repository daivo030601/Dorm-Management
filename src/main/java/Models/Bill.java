/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mayy
 */
public class Bill {
   protected String  billID;
   protected Employee employee;
   protected Apartment apartment;
   protected Room room;
   protected String createDay;
   protected String total;

    public Bill(String BillID, Employee employee, Apartment apartment, Room room, String createDay, String total) {
        this.billID = BillID;
        this.employee = employee;
        this.apartment = apartment;
        this.room = room;
        this.createDay = createDay;
        this.total = total;
    }

    public Bill() {
        this.billID = "";
        this.employee = new Employee();
        this.apartment = new Apartment();
        this.room = new Room();
        this.createDay = "";
        this.total = "0";
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String BillID) {
        this.billID = BillID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
   
   
}
