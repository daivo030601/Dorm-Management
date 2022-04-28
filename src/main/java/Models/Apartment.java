/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mayy
 */
public class Apartment {
    protected String IDApartment;
    protected String NoRoom;
    protected String Gender;
    protected String IDEmployee;

    public Apartment(String IDApartment, String NoRoom, String Gender, String IDEmployee) {
        this.IDApartment = IDApartment;
        this.NoRoom = NoRoom;
        this.Gender = Gender;
        this.IDEmployee = IDEmployee;
    }
    
    public Apartment() {
        this.IDApartment = "";
        this.NoRoom = "";
        this.Gender = "";
        this.IDEmployee = "";
    }

    public String getIDApartment() {
        return IDApartment;
    }

    public void setIDApartment(String IDApartment) {
        this.IDApartment = IDApartment;
    }

    public String getNoRoom() {
        return NoRoom;
    }

    public void setNoRoom(String NoRoom) {
        this.NoRoom = NoRoom;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getIDEmployee() {
        return IDEmployee;
    }

    public void setIDEmployee(String IDEmployee) {
        this.IDEmployee = IDEmployee;
    }
    
    
}
