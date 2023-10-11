import com.getordecorreo.Correo;
import com.getordecorreo.FiltroUCPPrioridad;
import com.getordecorreo.Usuario;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FiltroUCPPrioridad_Test {

    @Test
public void filtrarYPriorizarUCP_DebeRetornarListaNoNula() {
    List<Correo> correos = new ArrayList<>();
    assertNotNull(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos));
}

@Test
public void filtrarYPriorizarUCP_SinCorreos_DebeRetornarListaVacia() {
    List<Correo> correos = new ArrayList<>();
    assertTrue(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos).isEmpty());
}

@Test
public void filtrarYPriorizarUCP_CorreosSinUCP_DebeRetornarListaVacia() {
    List<Correo> correos = new ArrayList<>();
    correos.add(new Correo(new Usuario("Remitente1", "Nombre1", "Apellido1", "correo1@dominio.com"), null, "Asunto 1", "Contenido 1", null));
    correos.add(new Correo(new Usuario("Remitente2", "Nombre2", "Apellido2", "correo2@dominio.com"), null, "Asunto 2", "Contenido 2", null));
    assertTrue(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos).isEmpty());
}

@Test
public void filtrarYPriorizarUCP_CorreosConUCP_DebeRetornarListaNoVacia() {
    List<Correo> correos = new ArrayList<>();
    Usuario remitenteUCP = new Usuario("Remitente UCP", "Nombre", "Apellido", "ucp@example.com");
    correos.add(new Correo(remitenteUCP, null, "UCP", "Contenido 1", null));
    correos.add(new Correo(new Usuario("Otro Remitente", "Nombre Otro", "Apellido Otro", "otro@remitente.com"), null, "Asunto 2", "Contenido 2", null));
    assertFalse(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos).isEmpty());
}

@Test
public void testFiltrarYPriorizarUCP_CorreoUCPYCorreoUCP() {
    List<Correo> correos = new ArrayList<>(); // Inicializar la lista de correos
    // Crear un correo con asunto "UCP" y correo "ucp.edu.ar"
    Correo correo = new Correo(new Usuario("Remitente", "Nombre", "Apellido", "correo@ucp.edu.ar"), null, "UCP", null, null);

    correos.add(correo);

    List<Correo> correosPriorizados = FiltroUCPPrioridad.filtrarYPriorizarUCP(correos);

    // Comprobar que el correo tiene prioridad "ALTA"
    assertEquals(1, correosPriorizados.size());
    assertEquals("ALTA", correosPriorizados.get(0).getPrioridad());
}

@Test
public void testFiltrarYPriorizarUCP_CorreoUCPYCorreoNoUCP() {
    List<Correo> correos = new ArrayList<>(); // Inicializar la lista de correos
    // Crear un correo con asunto "UCP" y correo que no es de UCP
    Correo correo = new Correo(new Usuario("Remitente", "Nombre", "Apellido", "correo@dominio.com"), null, "UCP", null, null);

    correos.add(correo);

    List<Correo> correosPriorizados = FiltroUCPPrioridad.filtrarYPriorizarUCP(correos);

    // Comprobar que el correo tiene prioridad "NORMAL"
    assertEquals(1, correosPriorizados.size());
    assertEquals("NORMAL", correosPriorizados.get(0).getPrioridad());
}

@Test
public void testFiltrarYPriorizarUCP_CorreoUCPYCorreoUCPYCorreoNoUCP() {
    List<Correo> correos = new ArrayList<>(); // Inicializar la lista de correos

    // Crear un correo con asunto "UCP" y correo "ucp.edu.ar"
    Correo correo1 = new Correo(new Usuario("Remitente1", "Nombre1", "Apellido1", "correo1@ucp.edu.ar"), null, "UCP", null, null);

    // Crear un correo con asunto "UCP" pero con correo que no es de UCP
    Correo correo2 = new Correo(new Usuario("Remitente2", "Nombre2", "Apellido2", "correo2@dominio.com"), null, "UCP", null, null);

    correos.add(correo1);
    correos.add(correo2);

    List<Correo> correosPriorizados = FiltroUCPPrioridad.filtrarYPriorizarUCP(correos);

    // Comprobar que el primer correo tiene prioridad "ALTA"
    assertEquals(2, correosPriorizados.size());
    assertEquals("ALTA", correosPriorizados.get(0).getPrioridad());

    // Comprobar que el segundo correo tiene prioridad "NORMAL"
    assertEquals("NORMAL", correosPriorizados.get(1).getPrioridad());
}
}