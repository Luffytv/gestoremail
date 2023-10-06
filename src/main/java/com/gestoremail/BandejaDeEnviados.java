package com.gestoremail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class BandejaDeEnviados {
    private List<Correo> bandejaEnviados;

    public BandejaDeEnviados() {
        bandejaEnviados = new ArrayList<>();
    }

    public List<Correo> obtenerCorreosEnviadosDeCorreo(Correo correo) {
        return bandejaEnviados.stream()
            .filter(c -> c.getRemitente().equals(correo.getRemitente())) // Filtra por el mismo remitente
            .collect(Collectors.toList());
    }

    public List<Correo> obtenerCorreosEnviados() {
        return bandejaEnviados;
    }

    public void enviarCorreo(Correo correo) {
        bandejaEnviados.add(correo);
    }

    public List<Correo> buscarCorreosEnviados(Predicate<Correo> filtro) {
        return bandejaEnviados.stream()  // Cambiar "correosEnviados" a "bandejaEnviados"
                .filter(filtro)
                .collect(Collectors.toList());
    }

}