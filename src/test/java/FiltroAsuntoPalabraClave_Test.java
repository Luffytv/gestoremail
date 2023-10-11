import com.getordecorreo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroAsuntoPalabraClave_Test {

    @Test
    public void testAplicarFiltro() {
        List<Correo> correos = new ArrayList<>();
        correos.add(new Correo(null, null, "Asunto 1", "Contenido 1", null));
        correos.add(new Correo(null, null, "Asunto 2", "Contenido 2", null));
        correos.add(new Correo(null, null, "NO SE", "Otro Contenido", null));

        FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave("Asunto");

        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        assertEquals(2, correosFiltrados.size());
        assertEquals("Asunto 1", correosFiltrados.get(0).getAsunto());
        assertEquals("Asunto 2", correosFiltrados.get(1).getAsunto());
    }

    @Test
    public void testAplicarFiltroSinCriterioDeBusqueda() {
        List<Correo> correos = new ArrayList<>();
        correos.add(new Correo(null, null, "Asunto 1", "Contenido 1", null));
        correos.add(new Correo(null, null, "Asunto 2", "Contenido 2", null));
        correos.add(new Correo(null, null, "Otro Asunto", "Otro Contenido", null));


        FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave(null);

  
        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        assertEquals(3, correosFiltrados.size());
    }

    @Test
    public void testAplicarFiltroPorPalabraClave() {
        // Crear una lista de correos de ejemplo
        List<Correo> correos = new ArrayList<>();
        correos.add(new Correo(null, null, "Asunto 1", "Contenido 1 con palabra clave", null));
        correos.add(new Correo(null, null, "Asunto 2", "Contenido 2", null));

        // Crear un filtro con criterio de búsqueda por palabra clave
        FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave("palabra clave");

        // Aplicar el filtro a la lista de correos
        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Verificar que solo se obtiene el correo con la palabra clave en el contenido
        assertEquals(1, correosFiltrados.size());
        assertEquals("Asunto 1", correosFiltrados.get(0).getAsunto());
    }

    @Test
    public void testAplicarFiltroPorAsuntoYPalabraClave() {
        // Crear una lista de correos de ejemplo
        List<Correo> correos = new ArrayList<>();
        correos.add(new Correo(null, null, "Cursado", "Contenido 1", null));
        correos.add(new Correo(null, null, "Asunto 2", "Cursado", null));
        correos.add(new Correo(null, null, "Cursado", "mañana hay cursado", null));

        // Crear un filtro con criterio de búsqueda por asunto y palabra clave
        FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave("Cursado");

        // Aplicar el filtro a la lista de correos
        List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

        // Verificar que solo se obtiene el correo con el asunto y palabra clave coincidentes
        assertEquals(3, correosFiltrados.size());
        assertEquals("Cursado", correosFiltrados.get(0).getAsunto());
    }
}