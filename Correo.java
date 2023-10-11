package com.getordecorreo;

import java.util.List;
import java.util.Date;

public class Correo {
    private Usuario remitente;
    private List<Contacto> destinatarios;
    private String asunto;
    private String contenido;
   private Date fechaEnvio;
   private String prioridad;

    public Correo(Usuario remitente, List<Contacto> destinatarios, String asunto, String contenido, Date fechaEnvio) {
        this.remitente = remitente;
        this.destinatarios = destinatarios;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public List<Contacto> getDestinatarios() {
        return destinatarios;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFechaEnvio(){
        return fechaEnvio;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad(){
        return prioridad;
    }
}