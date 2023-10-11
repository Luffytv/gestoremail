import com.getordecorreo.BandejaDeEnviados;
import com.getordecorreo.Contacto;
import com.getordecorreo.Correo;
import com.getordecorreo.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BandejaDeEnviados_Test {
    private BandejaDeEnviados bandejaDeEnviados;

    @Before
    public void setUp() {
        bandejaDeEnviados = new BandejaDeEnviados();
    }

    @Test
    public void testEnviarCorreo() {
        // Crear un correo de ejemplo
        Usuario remitente = new Usuario("remitente", "Nombre Remitente", "Apellido Remitente", "correo@remitente.com");
        List<Contacto> destinatarios = new ArrayList<>();
        destinatarios.add(new Contacto("destinatario", "Nombre Destinatario", "Apellido Destinatario", "correo@destinatario.com"));
        Correo correo = new Correo(remitente, destinatarios, "Asunto del Correo", "Contenido del Correo", null);

        bandejaDeEnviados.enviarCorreo(correo);

        // Verificar que el correo se ha agregado correctamente a la bandeja de enviados
        List<Correo> correosEnviados = bandejaDeEnviados.obtenerCorreosEnviados();
        assertEquals(1, correosEnviados.size());
        assertEquals(correo, correosEnviados.get(0));
    }

    @Test
    public void testObtenerCorreosEnviados() {
        // Verificar que la bandeja de enviados esté inicialmente vacía
        List<Correo> correosEnviados = bandejaDeEnviados.obtenerCorreosEnviados();
        assertEquals(0, correosEnviados.size());

        // Agregar un correo y verificar que se pueda obtener
        Usuario remitente = new Usuario("remitente", "Nombre Remitente", "Apellido Remitente", "correo@remitente.com");
        List<Contacto> destinatarios = new ArrayList<>();
        destinatarios.add(new Contacto("destinatario", "Nombre Destinatario", "Apellido Destinatario", "correo@destinatario.com"));
        Correo correo = new Correo(remitente, destinatarios, "Asunto del Correo", "Contenido del Correo", null);
        bandejaDeEnviados.enviarCorreo(correo);

        correosEnviados = bandejaDeEnviados.obtenerCorreosEnviados();
        assertEquals(1, correosEnviados.size());
        assertEquals(correo, correosEnviados.get(0));
    }
}