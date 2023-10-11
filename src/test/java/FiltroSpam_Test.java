import com.getordecorreo.FiltroSpam;
import com.getordecorreo.Correo;
import com.getordecorreo.Usuario;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroSpam_Test {

    @Test
    public void testMarcarComoSpam_CorreoSpam() {
        FiltroSpam filtro = new FiltroSpam();
        Usuario remitenteSpam = new Usuario("Mercado Libre", "Mercado", "Libre", "Mercadolibre@example.com");
        Correo correoSpam = new Correo(remitenteSpam, null, null, null, null);

        filtro.marcarComoSpam(correoSpam);

        List<Correo> correosSpam = filtro.obtenerCorreosSpam();
        assertEquals(1, correosSpam.size());
        assertEquals(correoSpam, correosSpam.get(0));
    }

    @Test
    public void testMarcarComoSpam_CorreoNoSpam() {
        FiltroSpam filtro = new FiltroSpam();
        Usuario remitenteNoSpam = new Usuario("Otro Remitente", null, null, "otro@example.com");
        Correo correoNoSpam = new Correo(remitenteNoSpam, null, null, null, null);

        filtro.marcarComoSpam(correoNoSpam);

        List<Correo> correosSpam = filtro.obtenerCorreosSpam();
        assertEquals(0, correosSpam.size());
    }

    @Test
    public void testEsCorreoSpam_CorreoSpam() {
        FiltroSpam filtro = new FiltroSpam();
        Usuario remitenteSpam = new Usuario("Mercado Libre", "Mercado", "Libre", "Mercadolibre@example.com");
        Correo correoSpam = new Correo(remitenteSpam, null, null, null, null);
    
        assertEquals(true, filtro.esCorreoSpam(correoSpam));
    }
    
    @Test
    public void testEsCorreoSpam_CorreoNoSpam() {
        FiltroSpam filtro = new FiltroSpam();
        Usuario remitenteNoSpam = new Usuario("Otro Remitente", null, null, "otro@example.com");
        Correo correoNoSpam = new Correo(remitenteNoSpam, null, null, null, null);
    
        assertEquals(false, filtro.esCorreoSpam(correoNoSpam));
    }
}