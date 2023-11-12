package com.example.tareaunidaddos;

public class Paciente {

    int id;
    String nombre;
    String razon;
    String email;
    String telefono;
    String horario;

    public  Paciente(){

    }
    public Paciente(String nombre, String razon, String email, String telefono, String horario) {
        this.nombre = nombre;
        this.razon = razon;
        this.email = email;
        this.telefono = telefono;
        this.horario = horario;
    }

    public Paciente(int id, String nombre, String razon, String email, String telefono, String horario) {
        this.id = id;
        this.nombre = nombre;
        this.razon = razon;
        this.email = email;
        this.telefono = telefono;
        this.horario = horario;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getrazon() {
        return razon;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String gethorario() {
        return horario;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setrazon(String razon) {
        this.razon = razon;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void sethorario(String horario) {
        this.horario = horario;
    }
}
