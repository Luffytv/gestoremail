package com.gestoremail;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroUCP {
    public static List<Correo> filtrarPorAsuntoUCP(List<Correo> correos) {
        return correos.stream()
            .filter(correo ->
                "UCP".equalsIgnoreCase(correo.getAsunto()) // Filtrar por asunto "UCP" (insensible a mayúsculas y minúsculas)
            )
            .collect(Collectors.toList());
    }

    public static List<Correo> filtrarPorDireccionUCP(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> correo.getDestinatarios().stream()
                .anyMatch(destinatario -> destinatario.getCorreoElectronico().endsWith("@ucp.edu.ar")))
            .collect(Collectors.toList());
    }

    public static List<Correo> filtrarPorAsuntoUCPYDireccionUCP(List<Correo> correos) {
        return correos.stream()
            .filter(correo -> correo.getAsunto().equalsIgnoreCase("UCP") &&
                correo.getDestinatarios().stream()
                    .anyMatch(destinatario -> destinatario.getCorreoElectronico().endsWith("@ucp.edu.ar")))
            .collect(Collectors.toList());
    }
}
