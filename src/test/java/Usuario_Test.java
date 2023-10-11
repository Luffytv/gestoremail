import com.getordecorreo.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Usuario_Test {
    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario("usuario", "Nombre", "Apellido", "correo@example.com");
    }

    @Test
    public void testEnviarCorreo() {
        // Crear un usuario destinatario de ejemplo
        Usuario destinatario = new Usuario("destinatario", "NombreDest", "ApellidoDest", "correoDest@example.com");

        // Enviar un correo desde el usuario
        List<Usuario> destinatarios = new ArrayList<>();
        destinatarios.add(destinatario);
        usuario.enviarCorreo(destinatarios, "Asunto del Correo", "Contenido del Correo", new Date());

        // Verificar que el correo se ha enviado y recibido correctamente
        List<Correo> bandejaDeEnviados = usuario.getBandejaDeEnviados();
        List<Correo> bandejaDeEntradaDestinatario = destinatario.getBandejaDeEntrada();
        assertEquals(1, bandejaDeEnviados.size());
        assertEquals(1, bandejaDeEntradaDestinatario.size());
        assertEquals(bandejaDeEnviados.get(0), bandejaDeEntradaDestinatario.get(0));
    }

    @Test
    public void testRecibirCorreo() {
        // Crear un correo de ejemplo
        Usuario remitente = new Usuario("remitente", "NombreRemitente", "ApellidoRemitente", "correoRemitente@example.com");
        List<Contacto> destinatarios = new ArrayList<>();
        destinatarios.add(new Contacto("usuario", "Nombre", "Apellido", "correo@example.com"));
        Correo correo = new Correo(remitente, destinatarios, "Asunto del Correo", "Contenido del Correo", new Date());

        // Recibir el correo
        usuario.recibirCorreo(correo);

        // Verificar que el correo se ha recibido correctamente
        List<Correo> bandejaDeEntrada = usuario.getBandejaDeEntrada();
        assertEquals(1, bandejaDeEntrada.size());
        assertEquals(correo, bandejaDeEntrada.get(0));
    }

    @Test
    public void testEnviarCorreoA100Destinatarios() {
    Usuario destinatario = new Usuario("destinatario", "NombreDest", "ApellidoDest", "correoDest@example.com");

    List<Usuario> destinatarios = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        destinatarios.add(destinatario);
    }

    String asunto = "Asunto del Tp2";
    String contenido = "Contenido del Correo";
    usuario.enviarCorreo(destinatarios, asunto, contenido, new Date());

    List<Correo> bandejaDeEnviados = usuario.getBandejaDeEnviados();
    assertEquals(1, bandejaDeEnviados.size());

    for (Usuario destinatarioActual : destinatarios) {
        List<Correo> bandejaDeEntradaDestinatario = destinatarioActual.getBandejaDeEntrada();
        assertEquals(100, bandejaDeEntradaDestinatario.size());

        Correo correoRecibido = bandejaDeEntradaDestinatario.get(0);
        assertEquals(asunto, correoRecibido.getAsunto());
    }
}
}