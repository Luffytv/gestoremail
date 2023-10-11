import com.getordecorreo.Contacto;
import com.getordecorreo.Correo;
import com.getordecorreo.BandejaDeEntrada;
import com.getordecorreo.Usuario;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BandejaDeEntrada_Test {
    private BandejaDeEntrada bandeja;

    @Before
    public void setUp() {
        bandeja = new BandejaDeEntrada();
    }

    @Test
    public void testAgregarCorreo() {
        Usuario remitente = new Usuario("remite", "Nombre", "Apellido", "correo@dominio.com");
        List<Contacto> destinatarios = new ArrayList<>(); // Asegúrate de inicializar la lista si no lo has hecho antes
        destinatarios.add(new Contacto("destinatario1", "Nombre1", "Apellido1", "correo1@dominio.com"));
        destinatarios.add(new Contacto("destinatario2", "Nombre2", "Apellido2", "correo2@dominio.com"));

        Correo correo = new Correo(remitente, destinatarios, "Asunto del correo", "Contenido del correo", new Date());
        bandeja.agregarCorreo(correo);

        assertEquals(1, bandeja.getCorreos().size());
    }

}