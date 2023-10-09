import com.gestoremail.BandejaDeEnviados;
import com.gestoremail.Correo;
import com.gestoremail.Contacto;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class BandejaDeEnviados_Test {

    private BandejaDeEnviados bandeja;

    @Before
    public void setUp() {
        bandeja = new BandejaDeEnviados();
    }

    @Test
    public void enviarCorreo_AgregaCorreoALaBandejaEnviados() {
        Correo correo = new Correo("Asunto", "Contenido", new Contacto("Remitente", "Apellido", "remitente@example.com"), null, null);
        bandeja.enviarCorreo(correo);

        List<Correo> correosEnviados = bandeja.obtenerCorreosEnviados();
        assertEquals(1, correosEnviados.size());
        assertTrue(correosEnviados.contains(correo));
    }

    @Test
    public void buscarCorreosEnviados_ConFiltroPersonalizado() {
        Correo correo1 = new Correo("Asunto1", "Contenido1", new Contacto("Remitente", "Apellido", "remitente@example.com"), null, null);
        Correo correo2 = new Correo("Asunto2", "Contenido2", new Contacto("Remitente", "Apellido", "remitente@example.com"), null, null);
        Correo correo3 = new Correo("Asunto3", "Contenido3", new Contacto("OtroRemitente", "OtroApellido", "otroremitente@example.com"), null, null);

        bandeja.enviarCorreo(correo1);
        bandeja.enviarCorreo(correo2);
        bandeja.enviarCorreo(correo3);

        Predicate<Correo> filtroRemitente = c -> c.getRemitente().getCorreoElectronico().equals("remitente@example.com");
        List<Correo> correosFiltrados = bandeja.buscarCorreosEnviados(filtroRemitente);

        assertEquals(2, correosFiltrados.size());
        assertTrue(correosFiltrados.contains(correo1));
        assertTrue(correosFiltrados.contains(correo2));
        assertFalse(correosFiltrados.contains(correo3));
    }
}