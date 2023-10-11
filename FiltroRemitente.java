package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;

public class FiltroRemitente {

    private String valorBusqueda;

    public FiltroRemitente(String valorBusqueda) {
        this.valorBusqueda = valorBusqueda.toLowerCase(); // Convertir el valor de búsqueda a minúsculas para hacer coincidencias insensibles a mayúsculas y minúsculas
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
    List<Correo> correosFiltrados = new ArrayList<>();

        if (correos != null) {
        for (Correo correo : correos) {
            // Verificar si el valor de búsqueda coincide con el correo electrónico, nombre, apellido o usuario del remitente
            String remitenteUsuario = correo.getRemitente().getUsername().toLowerCase();
            String remitenteNombre = correo.getRemitente().getNombre().toLowerCase();
            String remitenteApellido = correo.getRemitente().getApellido().toLowerCase();
            String remitenteCorreo = correo.getRemitente().getCorreoElectronico().toLowerCase();
            
            if (remitenteUsuario.contains(valorBusqueda) || remitenteCorreo.contains(valorBusqueda) || remitenteNombre.contains(valorBusqueda) || remitenteApellido.contains(valorBusqueda)) {
                correosFiltrados.add(correo);
            }
        }
        }

        return correosFiltrados;
    }
}