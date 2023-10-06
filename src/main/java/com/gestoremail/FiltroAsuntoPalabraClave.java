package com.gestoremail;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAsuntoPalabraClave {
    private String asunto;
    private String palabraClave;

    public FiltroAsuntoPalabraClave(String asunto, String palabraClave, Contacto destinatario) {
        this.asunto = asunto;
        this.palabraClave = palabraClave;
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> 
                (asunto == null || correo.getAsunto().contains(asunto)) ||
                (palabraClave == null || correo.getContenido().contains(palabraClave))
            )
            .collect(Collectors.toList());
    }
}