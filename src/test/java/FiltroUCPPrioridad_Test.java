import com.gestoremail.Contacto;
import com.gestoremail.Correo;
import com.gestoremail.FiltroUCPPrioridad;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
    correos.add(new Correo("Asunto 1", "Contenido 1", null, null, null));
    correos.add(new Correo("Asunto 2", "Contenido 2", null, null, null));
    assertTrue(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos).isEmpty());
}

@Test
public void filtrarYPriorizarUCP_CorreosConUCP_DebeRetornarListaNoVacia() {
    List<Correo> correos = new ArrayList<>();
    Contacto contactoUCP = new Contacto("UCP Contacto", "Apellido", "ucp@example.com");
    List<Contacto> destinatarios = new ArrayList<>();
    destinatarios.add(contactoUCP);
    correos.add(new Correo("UCP", "Contenido 1", null, destinatarios, null));
    correos.add(new Correo("Asunto 2", "Contenido 2", null, null, null));
    assertFalse(FiltroUCPPrioridad.filtrarYPriorizarUCP(correos).isEmpty());
}
}