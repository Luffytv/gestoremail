import com.gestoremail.BandejaDeEntrada;
import com.gestoremail.Correo;
import com.gestoremail.Contacto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class BandejaDeEntrada_Test {

    private BandejaDeEntrada bandeja;

    @Before
    public void setUp() {
        bandeja = new BandejaDeEntrada();
    }

    @Test
public void recibirCorreo_AgregaCorreoALaBandejaEntrada() {
    Contacto remitente = new Contacto("Remitente", "Apellido", "remitente@example.com");
    Correo correo = new Correo("Asunto", "Contenido", remitente, null, null); // Asumiendo que no es necesario especificar destinatarios ni fechaEnvio
    bandeja.recibirCorreo(correo);

    List<Correo> correosEnBandeja = bandeja.obtenerCorreosEnBandejaEntrada();
    assertEquals(1, correosEnBandeja.size());
    assertTrue(correosEnBandeja.contains(correo));
}

    @Test
    public void enviarCorreo_MueveCorreoALaBandejaEnviados() {
        Contacto remitente = new Contacto("Remitente", "Apellido", "remitente@example.com");
        Correo correo = new Correo("Asunto", "Contenido", remitente, null, null);
        bandeja.recibirCorreo(correo);
        bandeja.enviarCorreo(correo);

        List<Correo> correosEnBandejaEntrada = bandeja.obtenerCorreosEnBandejaEntrada();
        assertEquals(0, correosEnBandejaEntrada.size());
    }

    @Test
    public void buscarCorreos_FiltrarPorAsunto() {
        Contacto remitente = new Contacto("Remitente", "Apellido", "remitente@example.com");
        Correo correo1 = new Correo("Asunto 1", "Contenido 1", remitente, null, null);
        Correo correo2 = new Correo("Asunto 2", "Contenido 2", remitente, null, null);
        bandeja.recibirCorreo(correo1);
        bandeja.recibirCorreo(correo2);

        Predicate<Correo> filtro = correo -> correo.getAsunto().equals("Asunto 1");
        List<Correo> correosFiltrados = bandeja.buscarCorreos(filtro);

        assertEquals(1, correosFiltrados.size());
        assertTrue(correosFiltrados.contains(correo1));
        assertFalse(correosFiltrados.contains(correo2));
    }

    @Test
    public void eliminarCorreoDeEntrada_EliminaCorreoDeLaBandejaEntrada() {
        Contacto remitente = new Contacto("Remitente", "Apellido", "remitente@example.com");
        Correo correo = new Correo("Asunto", "Contenido", remitente, null, null);
        bandeja.recibirCorreo(correo);
        bandeja.eliminarCorreoDeEntrada(correo);

        List<Correo> correosEnBandejaEntrada = bandeja.obtenerCorreosEnBandejaEntrada();

        assertEquals(0, correosEnBandejaEntrada.size());
        assertFalse(correosEnBandejaEntrada.contains(correo));
    }
}