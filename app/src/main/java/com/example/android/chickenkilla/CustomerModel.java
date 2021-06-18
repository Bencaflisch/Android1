package com.example.android.chickenkilla;

public class CustomerModel {

    private int id;
    private String name;
    private String address;
    private String violation;
    private String date;
    private int surveyor;

    //constructors


    public CustomerModel(int id, String name, String address, String violation, String date, int surveyor) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.violation = violation;
        this.date = date;
        this.surveyor = surveyor;
    }

    public CustomerModel() {
    }

    //toString to print contents of class


    @Override
    public String toString() {
        return "CustomerModel" +
                "id=" + id +
                ", name='" + name + " " +
                ", address=" + address +
                ", violation=" + violation +
                ", date=" + date +
                ", surveyor=" + surveyor +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getViolation() { return violation; }

    public void setViolation(String violation) {
        violation = violation;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public int getSurveyor() { return surveyor; }

    public void  setSurveyor (int surveyor) {this.surveyor = surveyor;}

}
