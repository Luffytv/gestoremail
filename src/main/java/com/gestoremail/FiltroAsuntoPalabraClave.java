package com.getordecorreo;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAsuntoPalabraClave {
    private String criterioBusqueda;

    public FiltroAsuntoPalabraClave(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> 
                (criterioBusqueda == null || correo.getAsunto().contains(criterioBusqueda)) ||
                (criterioBusqueda == null || correo.getContenido().contains(criterioBusqueda))
            )
            .collect(Collectors.toList());
    }
}