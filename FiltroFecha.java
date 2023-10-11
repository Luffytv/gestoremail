package com.getordecorreo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class FiltroFecha {
    private Date fechaInicio;
    private Date fechaFin;

    public FiltroFecha(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        List<Correo> correosFiltrados = new ArrayList<>();

        for (Correo correo : correos) {
            Date fechaCorreo = correo.getFechaEnvio();
            if (fechaCorreo != null && !fechaCorreo.before(fechaInicio) && !fechaCorreo.after(fechaFin)) {
                correosFiltrados.add(correo);
            }
        }

        return correosFiltrados;
    }
}