package com.gestoremail;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFecha {
    private Date fechaInicio;
    private Date fechaFin;

    public FiltroFecha(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> 
                (fechaInicio == null || correo.getFecha().after(fechaInicio)) &&
                (fechaFin == null || correo.getFecha().before(fechaFin))
            )
            .collect(Collectors.toList());
    }
}