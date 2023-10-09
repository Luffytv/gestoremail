import com.gestoremail.Correo;
import com.gestoremail.FiltroFecha;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroFecha_Test {

    private FiltroFecha filtroFecha;
    private List<Correo> correos;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        filtroFecha = new FiltroFecha(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        correos = new ArrayList<>();

        // Correos con fechas dentro y fuera del rango
        correos.add(new Correo("Asunto1", "Contenido1", null, null, Date.from(LocalDate.of(2023, 2, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
        correos.add(new Correo("Asunto2", "Contenido2", null, null, Date.from(LocalDate.of(2023, 6, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
        correos.add(new Correo("Asunto3", "Contenido3", null, null, Date.from(LocalDate.of(2022, 12, 31).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
    }

    @Test
    public void aplicarFiltro_DebeRetornarCorreosDentroDelRango() {
        List<Correo> correosFiltrados = filtroFecha.aplicarFiltro(correos);
        assertEquals(2, correosFiltrados.size());
    }

    @Test
    public void aplicarFiltro_DebeRetornarListaVaciaSiNoHayCorreosDentroDelRango() {
        filtroFecha = new FiltroFecha(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
        List<Correo> correosFiltrados = filtroFecha.aplicarFiltro(correos);
        assertEquals(0, correosFiltrados.size());
    }

    @Test
    public void aplicarFiltro_DebeRetornarTodosLosCorreosSiNoSeEspecificanFechas() {
        filtroFecha = new FiltroFecha(null, null);
        List<Correo> correosFiltrados = filtroFecha.aplicarFiltro(correos);
        assertEquals(3, correosFiltrados.size());
    }
}