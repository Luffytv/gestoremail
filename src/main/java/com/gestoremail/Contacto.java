package com.getordecorreo;

public class Contacto {
    private String userName;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public Contacto(String userName, String nombre,String apellido, String correoElectronico) {
        this.userName = userName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }

    public String getUsername(){
        return userName;
    }

    public void setuserName(String userName){
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}