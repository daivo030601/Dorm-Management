/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mayy
 */
public class RentBill extends Bill{
    private Student student;

    public RentBill(Student student, String billID, Employee employee, Apartment apartment, Room room, String createDay, String total) {
        super(billID, employee, apartment, room, createDay, total);
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
