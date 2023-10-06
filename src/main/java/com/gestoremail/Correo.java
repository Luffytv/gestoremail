package com.gestoremail;

import java.util.Date;
import java.util.List;

public class Correo {
    private String asunto;
    private String contenido;
    private Contacto remitente;
    private List<Contacto> destinatarios;
    private String prioridad;
    private Date fechaEnvio; // Campo para almacenar la fecha de envío

    private BandejaDeEnviados bandejaDeEnviados; // Campo para almacenar la referencia a la bandeja de enviados

    // Constructor que inicializa todos los campos, incluido fechaEnvio
    public Correo(String asunto, String contenido, Contacto remitente, List<Contacto> destinatarios, Date fechaEnvio) {
        this.asunto = asunto;
        this.contenido = contenido;
        this.remitente = remitente;
        this.destinatarios = destinatarios;
        this.fechaEnvio = fechaEnvio;
    }

    public void setBandejaDeEnviados(BandejaDeEnviados bandejaDeEnviados) {
        this.bandejaDeEnviados = bandejaDeEnviados;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fechaEnvio;
    }

    public Contacto getRemitente() {
        return remitente;
    }

    public List<Contacto> getDestinatarios() {
        return destinatarios;
    }

    public List<Correo> obtenerCorreosEnBandejaEnviados() {
        if (bandejaDeEnviados != null) {
            return bandejaDeEnviados.obtenerCorreosEnviadosDeCorreo(this);
        } else {
            return null;
        }
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad(){
        return prioridad;
    }
   
}