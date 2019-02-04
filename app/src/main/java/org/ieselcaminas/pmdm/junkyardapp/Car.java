package org.ieselcaminas.pmdm.junkyardapp;

public class Car {

    String marca;
    String modelo;
    String desguace;
    String año;
    String vin;
    int image;

    public Car(String marca, String modelo, String desguace, String año, String vin, int image) {
        this.marca = marca;
        this.modelo = modelo;
        this.desguace = desguace;
        this.año = año;
        this.vin = vin;
        this.image = image;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDesguace() {
        return desguace;
    }

    public void setDesguace(String desguace) {
        this.desguace = desguace;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String precio) {
        this.año = precio;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
