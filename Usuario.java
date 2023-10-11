package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Usuario {
    private String username;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private List<Correo> bandejaDeEntrada;
    private List<Correo> bandejaDeEnviados;

    public Usuario(String username, String nombre, String apellido, String correoElectronico) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.bandejaDeEntrada = new ArrayList<>();
        this.bandejaDeEnviados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getUsername() {
        return username;
    }

    public List<Correo> getBandejaDeEntrada() {
        return bandejaDeEntrada;
    }

    public List<Correo> getBandejaDeEnviados() {
        return bandejaDeEnviados;
    }

    public void enviarCorreo(List<Usuario> destinatarios, String asunto, String contenido, Date fechaEnvio) {
        List<Contacto> destinatarioContactos = new ArrayList<>();
        for (Usuario destinatario : destinatarios) {
            Contacto contacto = new Contacto(destinatario.getUsername(), destinatario.getNombre(), destinatario.getApellido(), destinatario.getCorreoElectronico());
            destinatarioContactos.add(contacto);
        }
        Correo correo = new Correo(new Usuario(this.username, this.nombre, this.apellido, this.correoElectronico), destinatarioContactos, asunto, contenido, fechaEnvio);
        this.bandejaDeEnviados.add(correo);
        for (Usuario destinatario : destinatarios) {
            destinatario.recibirCorreo(correo);
        }
    }

    public void recibirCorreo(Correo correo) {
        bandejaDeEntrada.add(correo);
    }
}