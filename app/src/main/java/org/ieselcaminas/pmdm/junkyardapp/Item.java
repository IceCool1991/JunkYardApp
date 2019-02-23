package org.ieselcaminas.pmdm.junkyardapp;

public class Item {

    String nombre;
    String ref;
    String ownerId;
    String precio;
    String vehiculo;
    String itemId;

    public Item(String nombre, String ref, String ownerId, String precio, String vehiculo, String itemId) {
        this.nombre = nombre;
        this.ref = ref;
        this.ownerId = ownerId;
        this.precio = precio;
        this.vehiculo = vehiculo;
        this.itemId = itemId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
