import com.getordecorreo.Usuario;
import com.getordecorreo.Correo;
import com.getordecorreo.Contacto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Correo_Test {
    private Correo correo;
    private Usuario remitente;
    private List<Contacto> destinatarios;

    @Before
    public void setUp() {
        // Configuración común para las pruebas
        remitente = new Usuario("remitente", "Nombre Remitente", "Apellido Remitente", "correo@remitente.com");
        destinatarios = new ArrayList<>();
        destinatarios.add(new Contacto("destinatario1", "Nombre Destinatario 1", "Apellido Destinatario 1", "correo1@destinatario.com"));
        destinatarios.add(new Contacto("destinatario2", "Nombre Destinatario 2", "Apellido Destinatario 2", "correo2@destinatario.com"));
        Date fechaEnvio = new Date();

        // Crear una instancia de Correo para cada prueba
        correo = new Correo(remitente, destinatarios, "Asunto del Correo", "Contenido del Correo", fechaEnvio);
    }

    @Test
    public void testGetRemitente() {
        assertEquals(remitente, correo.getRemitente());
    }

    @Test
    public void testGetDestinatarios() {
        assertEquals(destinatarios, correo.getDestinatarios());
    }

    @Test
    public void testGetAsunto() {
        assertEquals("Asunto del Correo", correo.getAsunto());
    }

    @Test
    public void testGetContenido() {
        assertEquals("Contenido del Correo", correo.getContenido());
    }

    @Test
    public void testGetFechaEnvio() {
        assertNotNull(correo.getFechaEnvio());
    }

    @Test
    public void testSetAndGetPrioridad() {
        correo.setPrioridad("Alta");
        assertEquals("Alta", correo.getPrioridad());
    }
}