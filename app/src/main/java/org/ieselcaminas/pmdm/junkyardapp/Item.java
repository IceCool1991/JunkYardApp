package org.ieselcaminas.pmdm.junkyardapp;

public class Item {

    String nombre;
    int image;

    public Item(String nombre, int image) {
        super();
        this.nombre = nombre;
        this.image = image;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return image;
    }

    public void setImagen(int logo) {
        this.image = logo;
    }

}
