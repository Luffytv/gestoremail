package com.gestoremail;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BandejaDeEntrada {
    private List<Correo> bandejaEntrada;
    private List<Correo> bandejaEnviados;

    public BandejaDeEntrada() {
        bandejaEntrada = new ArrayList<>();
        bandejaEnviados = new ArrayList<>();
    }

    public void recibirCorreo(Correo correo) {
        bandejaEntrada.add(correo);
    }

    public void enviarCorreo(Correo correo) {
        bandejaEnviados.add(correo);
        bandejaEntrada.remove(correo);
    }

    public List<Correo> buscarCorreos(Predicate<Correo> predicado) {
        return bandejaEntrada.stream().filter(predicado).collect(Collectors.toList());
    }


    public List<Correo> obtenerCorreosEnBandejaEntrada() {
        return bandejaEntrada;
    }


    public void eliminarCorreoDeEntrada(Correo correo) {
        bandejaEntrada.remove(correo); // Eliminar el correo de la bandeja de entrada
    }
}