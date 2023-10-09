import com.gestoremail.Contacto;
import com.gestoremail.BandejaDeEntrada;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class Contacto_Test {

    private Contacto contacto;

    @Before
    public void setUp() {
        contacto = new Contacto("Nombre", "Apellido", "correo@example.com");
    }

    @Test
    public void constructorDeContacto_InicializaCamposCorrectamente() {
        assertEquals("Nombre", contacto.getNombre());
        assertEquals("Apellido", contacto.getApellido());
        assertEquals("correo@example.com", contacto.getCorreoElectronico());
    }

    @Test
    public void setNombre_ModificaNombreCorrectamente() {
        contacto.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", contacto.getNombre());
    }

    @Test
    public void setApellido_ModificaApellidoCorrectamente() {
        contacto.setApellido("NuevoApellido");
        assertEquals("NuevoApellido", contacto.getApellido());
    }

    @Test
    public void setCorreoElectronico_ModificaCorreoCorrectamente() {
        contacto.setCorreoElectronico("nuevo@correo.com");
        assertEquals("nuevo@correo.com", contacto.getCorreoElectronico());
    }

    @Test
    public void getBandejaDeEntrada_DebeRetornarBandejaDeEntradaNoNula() {
        BandejaDeEntrada bandeja = contacto.getBandejaDeEntrada();
        // Verifica que la bandeja de entrada no sea nula
        assert (bandeja != null);
    }

    @Test
    public void verificarBandejasDeEntradaDeVariosContactos() {
        // Crear una lista de Contacto
        List<Contacto> contactos = new ArrayList<>();

        // Agregar 5 contactos a la lista
        for (int i = 0; i < 5; i++) {
            Contacto contacto = new Contacto("Nombre" + i, "Apellido" + i, "correo" + i + "@example.com");
            contactos.add(contacto);
        }

        // Verificar que las bandejas de entrada no sean nulas para cada contacto
        for (Contacto contacto : contactos) {
            BandejaDeEntrada bandeja = contacto.getBandejaDeEntrada();
            assertNotNull(bandeja); // Asegura que la bandeja de entrada no sea nula
        }
    }
}