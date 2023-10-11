package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeEntrada {
    private List<Correo> correos;

    public BandejaDeEntrada() {
        this.correos = new ArrayList<>();
    }

    public void agregarCorreo(Correo correo) {
        correos.add(correo);
    }

    public List<Correo> getCorreos() {
        return correos;
    }

}