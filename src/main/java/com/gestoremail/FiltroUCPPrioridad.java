package com.getordecorreo;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroUCPPrioridad {
    public static List<Correo> filtrarYPriorizarUCP(List<Correo> correos) {
        // Filtrar correos que tengan asunto "UCP" o remitente con correo @ucp.edu.ar
        List<Correo> correosFiltrados = correos.stream()
            .filter(correo ->
                "UCP".equalsIgnoreCase(correo.getAsunto()) ||
                correo.getRemitente().getCorreoElectronico().endsWith("@ucp.edu.ar"))
            .collect(Collectors.toList());

        // Asignar prioridad ALTA a los correos que cumplen ambos criterios
        for (Correo correo : correosFiltrados) {
            if ("UCP".equalsIgnoreCase(correo.getAsunto()) &&
                correo.getRemitente().getCorreoElectronico().endsWith("@ucp.edu.ar")) {
                correo.setPrioridad("ALTA");
            } else {
                correo.setPrioridad("NORMAL");
            }
        }

        return correosFiltrados;
    }
}