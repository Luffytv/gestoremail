package com.getordecorreo;

import java.util.ArrayList;
import java.util.List;

public class SistemaDeCorreo {
    private List<Usuario> usuarios;

    public SistemaDeCorreo() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario iniciarSesion(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}