package com.gestoremail;

import java.util.ArrayList;
import java.util.List;

public class FiltroSpam {
    private List<Correo> correosSpam;

    public FiltroSpam() {
        this.correosSpam = new ArrayList<>();
    }

    public void marcarComoSpam(Correo correo) {
        correosSpam.add(correo);
        System.out.println("El correo con asunto '" + correo.getAsunto() + "' ha sido marcado como spam.");
    }

    public boolean esCorreoSpam(Correo correo) {
        return correosSpam.contains(correo);
    }
}