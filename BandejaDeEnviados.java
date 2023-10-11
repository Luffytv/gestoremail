package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeEnviados {
    private List<Correo> correosEnviados;

    public BandejaDeEnviados() {
        correosEnviados = new ArrayList<>();
    }

    public void enviarCorreo(Correo correo) {
        correosEnviados.add(correo);
    }

    public List<Correo> obtenerCorreosEnviados() {
        return correosEnviados;
    }
}