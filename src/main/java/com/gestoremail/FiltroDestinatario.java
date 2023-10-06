package com.gestoremail;

import java.util.ArrayList;
import java.util.List;

public class FiltroDestinatario {

    private String valorBusqueda;

    public FiltroDestinatario(String valorBusqueda) {
        this.valorBusqueda = valorBusqueda.toLowerCase(); // Convertir el valor de búsqueda a minúsculas para hacer coincidencias insensibles a mayúsculas y minúsculas
    }

    public List<Correo> aplicarFiltro(List<Correo> correos) {
        List<Correo> correosFiltrados = new ArrayList<>();

        for (Correo correo : correos) {
            // Verificar si el valor de búsqueda coincide con el correo electrónico, nombre o apellido de algún destinatario
            boolean encontrado = false;
            for (Contacto destinatario : correo.getDestinatarios()) {
                if (destinatario.getCorreoElectronico().toLowerCase().contains(valorBusqueda)
                        || destinatario.getNombre().toLowerCase().contains(valorBusqueda)
                        || destinatario.getApellido().toLowerCase().contains(valorBusqueda)) {
                    correosFiltrados.add(correo);
                    encontrado = true;
                    break; // Si encontramos una coincidencia en el destinatario, podemos agregar el correo y pasar al siguiente
                }
            }
            // Si no se encontró coincidencia en el destinatario, verificar si coincide con el correo electrónico del remitente
            if (!encontrado && correo.getRemitente().getCorreoElectronico().toLowerCase().contains(valorBusqueda)) {
                correosFiltrados.add(correo);
            }
        }

        return correosFiltrados;
    }
}