package org.ieselcaminas.pmdm.junkyardapp;

public class Car {

    String make;
    String model;
    String engine;
    String year;
    String vin;
    String ownerId;

    public Car(String make, String model, String engine, String year, String vin, String ownerId) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.vin = vin;
        this.ownerId = ownerId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
