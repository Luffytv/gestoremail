package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;

public class FiltroSpam {
    private List<Correo> correosSpam;

    public FiltroSpam() {
        this.correosSpam = new ArrayList<>();
    }

    public void marcarComoSpam(Correo correo) {
        if (esCorreoSpam(correo)) {
            correosSpam.add(correo);
            System.out.println("El correo con asunto '" + correo.getAsunto() + "' ha sido marcado como spam.");
        }
    }

    public boolean esCorreoSpam(Correo correo) {
        // Convertir los valores a minúsculas y comparar
        String usuarioRemitente = correo.getRemitente().getUsername().toLowerCase();
        String correoRemitente = correo.getRemitente().getCorreoElectronico().toLowerCase();
    
        return "mercado libre".equalsIgnoreCase(usuarioRemitente) &&
                "mercadolibre@example.com".equalsIgnoreCase(correoRemitente);
    }

    public List<Correo> obtenerCorreosSpam() {
        return new ArrayList<>(correosSpam);
    }
}