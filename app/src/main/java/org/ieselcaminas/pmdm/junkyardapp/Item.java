package org.ieselcaminas.pmdm.junkyardapp;

public class Item {

    String nombre;
    String ref;
    String desguace;
    String precio;
    String vehiculo;
    int image;

    public Item(String nombre, String ref, String desguace, String precio, String vehiculo, int image) {
        this.nombre = nombre;
        this.ref = ref;
        this.desguace = desguace;
        this.precio = precio;
        this.vehiculo = vehiculo;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesguace() {
        return desguace;
    }

    public void setDesguace(String desguace) {
        this.desguace = desguace;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
