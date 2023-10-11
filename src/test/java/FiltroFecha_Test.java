import com.getordecorreo.Correo;
import com.getordecorreo.FiltroFecha;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroFecha_Test {
    private List<Correo> correos;
    private FiltroFecha filtro;

    @Before
    public void setUp() {
        correos = new ArrayList<>();
        filtro = new FiltroFecha(new Date(1000L), new Date(3000L)); // Rango de fechas de 1 segundo a 3 segundos después de la época
    }

    @Test
    public void testAplicarFiltro_CorreosDentroDelRango() {
        // Crear correos con fechas dentro del rango
        Correo correo1 = new Correo(null, null, null, null, new Date(2000L)); // Fecha dentro del rango
        Correo correo2 = new Correo(null, null, null, null, new Date(2500L)); // Fecha dentro del rango

        correos.add(correo1);
        correos.add(correo2);

        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Comprobar que los correos dentro del rango están en la lista filtrada
        assertEquals(2, correosFiltrados.size());
        assertEquals(correo1, correosFiltrados.get(0));
        assertEquals(correo2, correosFiltrados.get(1));
    }

    @Test
    public void testAplicarFiltro_CorreosFueraDelRango() {
        // Crear correos con fechas fuera del rango
        Correo correo1 = new Correo(null, null, null, null, new Date(500L)); // Fecha antes del rango
        Correo correo2 = new Correo(null, null, null, null, new Date(3500L)); // Fecha después del rango

        correos.add(correo1);
        correos.add(correo2);

        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Comprobar que los correos fuera del rango no están en la lista filtrada
        assertEquals(0, correosFiltrados.size());
    }

    @Test
    public void testAplicarFiltro_CorreosConFechaNula() {
        // Crear correos con fecha nula
        Correo correo1 = new Correo(null, null, null, null, null);
        Correo correo2 = new Correo(null, null, null, null, null);

        correos.add(correo1);
        correos.add(correo2);

        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Comprobar que los correos con fecha nula no están en la lista filtrada
        assertEquals(0, correosFiltrados.size());
    }
}
