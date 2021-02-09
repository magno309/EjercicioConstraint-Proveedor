package com.example.ejercicioconstraint.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
    private long ID;
    private String Nombre;
    private String Contrasenia;
    private String Telefono;
    private String Email;
    private String Pais;

    public Usuario(long ID, String nombre, String contrasenia, String telefono, String email, String pais) {
        this.ID = ID;
        Nombre = nombre;
        Contrasenia = contrasenia;
        Telefono = telefono;
        Email = email;
        Pais = pais;
    }

    public Usuario(String nombre, String telefono, String email, String contrasenia, String pais) {
        Nombre = nombre;
        Telefono = telefono;
        Email = email;
        Contrasenia = contrasenia;
        Pais = pais;
    }

    public Usuario(String email, String contrasenia) {
        Email = email;
        Contrasenia = contrasenia;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }
}
