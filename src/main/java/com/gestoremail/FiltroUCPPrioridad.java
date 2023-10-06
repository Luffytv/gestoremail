package com.gestoremail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroUCPPrioridad {
    public static List<Correo> filtrarYPriorizarUCP(List<Correo> correos) {
        // Filtrar correos que tengan asunto "UCP" o correo @ucp.edu.ar
        List<Correo> correosFiltrados = correos.stream()
            .filter(correo ->
                "UCP".equalsIgnoreCase(correo.getAsunto()) ||
                correo.getDestinatarios().stream()
                    .anyMatch(destinatario -> destinatario.getCorreoElectronico().endsWith("@ucp.edu.ar")))
            .collect(Collectors.toList());
    
        // Separar los correos que cumplen ambos criterios y asignarles prioridad ALTA
        List<Correo> correosPrioridadAlta = new ArrayList<>();
        List<Correo> correosPrioridadNormal = new ArrayList<>();
    
        for (Correo correo : correosFiltrados) {
            if ("UCP".equalsIgnoreCase(correo.getAsunto()) &&
                correo.getDestinatarios().stream()
                    .anyMatch(destinatario -> destinatario.getCorreoElectronico().endsWith("@ucp.edu.ar"))) {
                // Cumple ambos criterios, asignar prioridad ALTA
                correo.setPrioridad("ALTA");
                correosPrioridadAlta.add(correo);
            } else {
                correosPrioridadNormal.add(correo);
            }
        }
    
        // Ordenar y mostrar primero los correos con prioridad ALTA
        correosPrioridadAlta.addAll(correosPrioridadNormal);
    
        return correosPrioridadAlta;
    }
}
