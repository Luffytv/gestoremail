import com.getordecorreo.Contacto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Contacto_Test {
    private Contacto contacto;

    @Before
    public void setUp() {
        contacto = new Contacto("usuario", "Nombre", "Apellido", "correo@example.com");
    }

    @Test
    public void testGetUsername() {
        assertEquals("usuario", contacto.getUsername());
    }

    @Test
    public void testSetUsername() {
        contacto.setuserName("nuevoUsuario");
        assertEquals("nuevoUsuario", contacto.getUsername());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Nombre", contacto.getNombre());
    }

    @Test
    public void testSetNombre() {
        contacto.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", contacto.getNombre());
    }

    @Test
    public void testGetApellido() {
        assertEquals("Apellido", contacto.getApellido());
    }

    @Test
    public void testSetApellido() {
        contacto.setApellido("NuevoApellido");
        assertEquals("NuevoApellido", contacto.getApellido());
    }

    @Test
    public void testGetCorreoElectronico() {
        assertEquals("correo@example.com", contacto.getCorreoElectronico());
    }

    @Test
    public void testSetCorreoElectronico() {
        contacto.setCorreoElectronico("nuevo@correo.com");
        assertEquals("nuevo@correo.com", contacto.getCorreoElectronico());
    }
}