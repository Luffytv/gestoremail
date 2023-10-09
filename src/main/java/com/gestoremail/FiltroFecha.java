package com.gestoremail;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

public class FiltroFecha {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public FiltroFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> 
                (fechaInicio == null || correo.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(fechaInicio)) &&
                (fechaFin == null || correo.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(fechaFin))
            )
            .collect(Collectors.toList());
    }
}