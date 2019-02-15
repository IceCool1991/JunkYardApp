package org.ieselcaminas.pmdm.junkyardapp;

public class User {


    String nombre;
    String apellido;
    String desguace;
    String nif;
    String phone;
    int image;

    public User(String nombre, String apellido, String desguace, String nif, String phone, int image) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.desguace = desguace;
        this.nif = nif;
        this.phone = phone;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDesguace() {
        return desguace;
    }

    public void setDesguace(String desguace) {
        this.desguace = desguace;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
