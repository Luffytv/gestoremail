package com.gestoremail;

public class Contacto {
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private BandejaDeEntrada bandejaDeEntrada; // Agregar una bandeja de entrada para el contacto

    public Contacto(String nombre, String apellido, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.bandejaDeEntrada = new BandejaDeEntrada(); // Inicializa la bandeja de entrada del contacto
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public BandejaDeEntrada getBandejaDeEntrada() {
        return bandejaDeEntrada;
    }
}