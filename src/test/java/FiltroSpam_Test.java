import com.gestoremail.Correo;
import com.gestoremail.FiltroSpam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiltroSpam_Test {

    private FiltroSpam filtroSpam;
    private Correo correo;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        filtroSpam = new FiltroSpam();
        correo = new Correo("Asunto de Spam", "Contenido de Spam", null, null, null);
    }

    @Test
    public void marcarComoSpam_DebeMarcarCorreoComoSpam() {
        assertFalse(filtroSpam.esCorreoSpam(correo)); // El correo no debe estar marcado como spam inicialmente
        filtroSpam.marcarComoSpam(correo);
        assertTrue(filtroSpam.esCorreoSpam(correo)); // Ahora el correo debe estar marcado como spam
    }

    @Test
    public void obtenerCorreosSpam_DebeRetornarListaNoNula() {
        assertNotNull(filtroSpam.obtenerCorreosSpam());
    }

    @Test
    public void obtenerCorreosSpam_SinCorreosSpam_DebeRetornarListaVacia() {
        assertTrue(filtroSpam.obtenerCorreosSpam().isEmpty());
    }

    @Test
    public void obtenerCorreosSpam_ConCorreosSpam_DebeRetornarListaNoVacia() {
        filtroSpam.marcarComoSpam(correo);
        assertFalse(filtroSpam.obtenerCorreosSpam().isEmpty());
    }
}