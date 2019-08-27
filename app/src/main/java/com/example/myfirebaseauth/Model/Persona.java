package com.example.myfirebaseauth.Model;

public class Persona {

    public  String uiId;
    public String Nombre;
    public String Apellido;
    public String correo;

    // constructor vacio
    public Persona() {
    }

    //get y set

    public String getUiId() {
        return uiId;
    }

    public void setUiId(String uiId) {
        this.uiId = uiId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //toString

    @Override
    public String toString() {
        return Nombre;
    }


}
