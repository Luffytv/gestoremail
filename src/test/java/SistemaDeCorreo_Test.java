import com.getordecorreo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SistemaDeCorreo_Test {
    private SistemaDeCorreo sistemaDeCorreo;

    @Before
    public void setUp() {
        sistemaDeCorreo = new SistemaDeCorreo();
    }

    @Test
    public void testAgregarUsuario() {
        Usuario usuario = new Usuario("usuario", "Nombre", "Apellido", "correo@example.com");
        sistemaDeCorreo.agregarUsuario(usuario);

        // Verificar que el usuario se haya agregado correctamente
        assertEquals(1, sistemaDeCorreo.getUsuarios().size());
        assertEquals(usuario, sistemaDeCorreo.getUsuarios().get(0));
    }

    @Test
    public void testIniciarSesionUsuarioExistente() {
        Usuario usuario = new Usuario("usuario", "Nombre", "Apellido", "correo@example.com");
        sistemaDeCorreo.agregarUsuario(usuario);

        // Iniciar sesión con el nombre de usuario existente
        Usuario usuarioIniciado = sistemaDeCorreo.iniciarSesion("usuario");

        // Verificar que se haya iniciado sesión con el usuario correcto
        assertEquals(usuario, usuarioIniciado);
    }

    @Test
    public void testIniciarSesionUsuarioNoExistente() {
        // Intentar iniciar sesión con un nombre de usuario que no existe
        Usuario usuarioIniciado = sistemaDeCorreo.iniciarSesion("usuarioNoExistente");

        // Verificar que no se haya iniciado sesión (usuarioIniciado debe ser null)
        assertNull(usuarioIniciado);
    }
}