import com.gestoremail.BandejaDeEnviados;
import com.gestoremail.Contacto;
import com.gestoremail.Correo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class Correo_Test {

    private Correo correo;
    private BandejaDeEnviados bandejaDeEnviados;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        bandejaDeEnviados = new BandejaDeEnviados();
        correo = new Correo("Asunto de prueba", "Contenido de prueba",
                new Contacto("Remitente", "Apellido", "remitente@example.com"),
                new ArrayList<>(), new Date());

        // Establece la bandeja de enviados
        correo.setBandejaDeEnviados(bandejaDeEnviados);
    }

    @Test
    public void obtenerCorreosEnBandejaEnviados_DebeRetornarListaNoNula() {
        List<Correo> correosEnviados = correo.obtenerCorreosEnBandejaEnviados();
        assertNotNull(correosEnviados);
    }

    @Test
    public void obtenerCorreosEnBandejaEnviados_SinBandejaDeEnviados_DebeRetornarNull() {
        // Configuración sin bandeja de enviados
        correo.setBandejaDeEnviados(null);

        List<Correo> correosEnviados = correo.obtenerCorreosEnBandejaEnviados();
        assertNull(correosEnviados);
    }

    @Test
    public void setPrioridad_DebeCambiarPrioridad() {
        correo.setPrioridad("Alta");
        assertEquals("Alta", correo.getPrioridad());
    }

    @Test
    public void obtenerAsunto_DebeRetornarAsuntoCorrecto() {
        assertEquals("Asunto de prueba", correo.getAsunto());
    }

    @Test
    public void obtenerContenido_DebeRetornarContenidoCorrecto() {
        assertEquals("Contenido de prueba", correo.getContenido());
    }

    @Test
    public void obtenerFecha_DebeRetornarFechaCorrecta() {
        assertNotNull(correo.getFecha());
    }

    @Test
    public void obtenerRemitente_DebeRetornarRemitenteCorrecto() {
        Contacto remitente = correo.getRemitente();
        assertEquals("Remitente", remitente.getNombre());
        assertEquals("Apellido", remitente.getApellido());
        assertEquals("remitente@example.com", remitente.getCorreoElectronico());
    }

    @Test
    public void obtenerDestinatarios_DebeRetornarListaVacia() {
        List<Contacto> destinatarios = correo.getDestinatarios();
        assertTrue(destinatarios.isEmpty());
    }
}