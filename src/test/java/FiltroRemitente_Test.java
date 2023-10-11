import com.getordecorreo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroRemitente_Test {
    @Test
    public void testAplicarFiltro() {
        // Crear una lista de correos de ejemplo
        List<Correo> correos = new ArrayList<>();
        Usuario remitente1 = new Usuario("remitente1", "Nombre1", "Apellido1", "correo1@dominio.com");
        Usuario remitente2 = new Usuario("remitente2", "Nombre2", "Apellido2", "correo2@dominio.com");
        correos.add(new Correo(remitente1, null, null, null, null));
        correos.add(new Correo(remitente2, null, null, null, null));

        // Crear un filtro con criterio de búsqueda por remitente
        FiltroRemitente filtro = new FiltroRemitente("remitente1");

        // Aplicar el filtro a la lista de correos
        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Verificar que solo se obtiene el correo del remitente1
        assertEquals(1, correosFiltrados.size());
        assertEquals(remitente1, correosFiltrados.get(0).getRemitente());
    }

    @Test
    public void testAplicarFiltroSinCoincidencias() {
        // Crear una lista de correos de ejemplo
        List<Correo> correos = new ArrayList<>();
        Usuario remitente1 = new Usuario("remitente1", "Nombre1", "Apellido1", "correo1@dominio.com");
        Usuario remitente2 = new Usuario("remitente2", "Nombre2", "Apellido2", "correo2@dominio.com");
        correos.add(new Correo(remitente1, null, null, null, null));
        correos.add(new Correo(remitente2, null, null, null, null));

        // Crear un filtro con un criterio que no coincide con ningún remitente
        FiltroRemitente filtro = new FiltroRemitente("remitente3");

        // Aplicar el filtro a la lista de correos
        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Verificar que no se obtiene ningún correo
        assertEquals(0, correosFiltrados.size());
    }
}