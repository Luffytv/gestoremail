import com.gestoremail.Correo;
import com.gestoremail.FiltroAsuntoPalabraClave;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroAsuntoPalabraClave_Test {

   @Test
public void aplicarFiltro_DebeFiltrarCorreosPorAsuntoYPalabraClave() {
    // Crear una lista de correos de prueba
    List<Correo> correos = new ArrayList<>();
    correos.add(new Correo("Asunto 1", "Contenido 1", null, null, null));
    correos.add(new Correo("Asunto 2", "Contenido distinto", null, null, null));
    correos.add(new Correo("Asunto 3", "Otro contenido", null, null, null));
    correos.add(new Correo("Asunto 1", "palabra clave", null, null, null));
    correos.add(new Correo("Asunto 2", "palabraclave", null, null, null));
    //correos.add(new Correo(null, null, null, null, null));

    // Crear un filtro con asunto y palabra clave
    FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave("Asunto 2", "palabra clave");

    // Aplicar el filtro a la lista de correos
    List<Correo> correosFiltrados = filtro.aplicarFiltro(correos);

    // Verificar que se obtengan los correos que cumplan con los criterios de asunto y palabra clave
    assertEquals(3, correosFiltrados.size()); // Ahora esperamos 3 correos filtrados
}
}