package com.gestoremail;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        List<Contacto> contactos = new ArrayList<>();
        Contacto remitente = new Contacto("Lucas", "Caamaño", "lucas@gmail.com");
        contactos.add(new Contacto("Juan", "Perez", "juan@example.com"));
        contactos.add(new Contacto("Maria","Fernandez", "maria@example.com"));
        contactos.add(new Contacto("Pedro","Picapiedra", "pedro@ucp.edu.ar"));
        contactos.add(new Contacto("Laura","Sanchez", "laura@example.com"));
        contactos.add(new Contacto("Jose","Sanchez", "josesan@example.com"));
        contactos.add(new Contacto("Mercado","Libre", "Mercadolibre@example.com"));

        BandejaDeEntrada bandeja = new BandejaDeEntrada();
        BandejaDeEnviados bandejaEnviados = new BandejaDeEnviados();
        FiltroSpam filtroSpam = new FiltroSpam();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("¿Qué deseas hacer?");
                System.out.println("1. Enviar correo");
                System.out.println("2. Ver bandeja de entrada");
                System.out.println("3. Ver bandeja de enviados");
                System.out.println("4. Buscar Correo por Asunto");
                System.out.println("5. Buscar Correo por Fecha");
                System.out.println("6. Buscar Correo por Destinatario");
                System.out.println("7. Buscar Correos de la UCP");
                System.out.println("8. Correos Spam");
                System.out.println("9. Salir");
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Escribe el asunto del correo:");
                        String asunto = scanner.nextLine();

                        System.out.println("Escribe el contenido del correo:");
                        String contenido = scanner.nextLine();

                        // Solicitar al usuario que ingrese la fecha en un formato específico (por ejemplo, "dd/MM/yyyy")
                        System.out.println("Escribe la fecha del correo (formato dd/MM/yyyy):");
                        String fechaStr = scanner.nextLine();

                        // Agregar la hora al final de la fecha ingresada por el usuario (por ejemplo, "00:00:00")
                        fechaStr += " 00:00:00";

                        // Intentar convertir la cadena de fecha en un objeto Date
                        Date fechaEnvio = null;
                        try {
                            fechaEnvio = dateFormat.parse(fechaStr);
                        } catch (ParseException e) {
                            System.out.println("Formato de fecha incorrecto. El formato debe ser dd/MM/yyyy.");
                            // Puedes manejar el error de acuerdo a tus necesidades
                        }

                        if (fechaEnvio != null) {
                            List<Contacto> destinatarios = new ArrayList<>(); // Declaración y creación de la lista de destinatarios

                            System.out.println("Selecciona destinatarios (ingresa números separados por espacios):");
                            for (int i = 0; i < contactos.size(); i++) {
                                System.out.println((i + 1) + ". " + contactos.get(i).getNombre() + " " + contactos.get(i).getApellido() + " " + contactos.get(i).getCorreoElectronico());
                            }

                            String[] numerosDestinatarios = scanner.nextLine().split(" ");
                            for (String numero : numerosDestinatarios) {
                                int indice = Integer.parseInt(numero) - 1;
                                if (indice >= 0 && indice < contactos.size()) {
                                    destinatarios.add(contactos.get(indice));
                                }
                            }

                            Correo correo = new Correo(asunto, contenido, remitente, destinatarios, fechaEnvio);
                            bandeja.recibirCorreo(correo); // Recibir el correo en la bandeja de entrada
                            bandejaEnviados.enviarCorreo(correo); // Enviar el correo a la bandeja de enviados
                            System.out.println("Correo enviado a la bandeja de enviados.");

                            // Eliminar el correo de la bandeja de entrada
                            bandeja.eliminarCorreoDeEntrada(correo);
                        }
                        break;

                    case 2:
                            List<Correo> correosEnBandeja = bandeja.obtenerCorreosEnBandejaEntrada();
                            
                            if (correosEnBandeja.isEmpty()) {
                                System.out.println("Bandeja de entrada vacía.");
                            } else {
                                System.out.println("Bandeja de entrada:");
                                for (Correo c : correosEnBandeja) {
                                    System.out.println("Asunto: " + c.getAsunto());
                                    System.out.println("Contenido: " + c.getContenido());
                                    System.out.println("Remitente: " + c.getRemitente().getNombre());
                                    System.out.println("Destinatarios: " + c.getDestinatarios().stream().map(Contacto::getNombre).collect(Collectors.joining(", ")));
                                    System.out.println("------------");
                                }
                            }
                            break;

                    case 3:
                        System.out.println("Bandeja de enviados:");
                        List<Correo> correosEnviados = bandejaEnviados.obtenerCorreosEnviados();
                    
                        for (Correo c : correosEnviados) {
                            System.out.println("Asunto: " + c.getAsunto());
                            System.out.println("Contenido: " + c.getContenido());
                            System.out.println("Remitente: " + c.getRemitente().getNombre() + " " + c.getRemitente().getApellido() + " (" + c.getRemitente().getCorreoElectronico() + ")");
                            System.out.println("Fecha: " + c.getFecha()); // Imprimir la fecha 
                            System.out.println("Destinatarios:");
                        for (Contacto destinatario : c.getDestinatarios()) {
                            System.out.println("- " + destinatario.getNombre() + " " + destinatario.getApellido() + " (" + destinatario.getCorreoElectronico() + ")");
}
                            System.out.println("------------");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el asunto a buscar (deje en blanco si no desea filtrar por asunto): ");
                        String asuntoABuscar = scanner.nextLine();
                    
                        System.out.print("Ingrese la palabra clave a buscar (deje en blanco si no desea filtrar por palabra clave): ");
                        String palabraClave = scanner.nextLine();
                    
                        // Crear una instancia de FiltroAsuntoPalabraClave con los criterios proporcionados por el usuario
                        FiltroAsuntoPalabraClave filtro = new FiltroAsuntoPalabraClave(asuntoABuscar, palabraClave);
                    
                        // Aplicar el filtro a la lista de correos en la bandeja de enviados
                        List<Correo> correosFiltrados = filtro.aplicarFiltro(bandejaEnviados.obtenerCorreosEnviados());
                    
                        if (correosFiltrados.isEmpty()) {
                            System.out.println("No se encontraron correos que coincidan con los criterios de búsqueda.");
                        } else {
                            System.out.println("Correos encontrados que coinciden con los criterios de búsqueda:");
                            for (Correo correoEncontrado : correosFiltrados) {
                                System.out.println("Asunto: " + correoEncontrado.getAsunto());
                                System.out.println("Mensaje: " + correoEncontrado.getContenido());
                                System.out.println("Remitente: " + correoEncontrado.getRemitente().getCorreoElectronico());
                                System.out.println("Destinatarios: " + correoEncontrado.getDestinatarios().stream().map(Contacto::getCorreoElectronico).collect(Collectors.joining(", ")));
                                System.out.println("------------");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Filtrar correos por rango de fechas.");
                        System.out.println("Ingrese la fecha de inicio (formato dd/MM/yyyy):");
                        String fechaInicioStr = scanner.nextLine();
                        System.out.println("Ingrese la fecha de fin (formato dd/MM/yyyy):");
                        String fechaFinStr = scanner.nextLine();
                    
                        try {
                            // Agregar automáticamente la hora al final de las fechas ingresadas
                            Date fechaInicio = dateFormat.parse(fechaInicioStr + " 00:00:00");
                            Date fechaFin = dateFormat.parse(fechaFinStr + " 23:59:59");
                    
                            List<Correo> correosFiltradosPorFecha = new ArrayList<>();
                    
                            // Recorre todos los correos en la bandeja de enviados y verifica si están dentro del rango de fechas
                            for (Correo correo : bandejaEnviados.obtenerCorreosEnviados()) {
                                Date fechaCorreo = correo.getFecha();
                                if (fechaCorreo != null && !fechaCorreo.before(fechaInicio) && !fechaCorreo.after(fechaFin)) {
                                    correosFiltradosPorFecha.add(correo);
                                }
                            }
                    
                            if (correosFiltradosPorFecha.isEmpty()) {
                                System.out.println("No se encontraron correos dentro del rango de fechas especificado.");
                            } else {
                                System.out.println("Correos encontrados dentro del rango de fechas especificado:");
                                for (Correo correoEncontrado : correosFiltradosPorFecha) {
                                    System.out.println("Asunto: " + correoEncontrado.getAsunto());
                                    System.out.println("Mensaje: " + correoEncontrado.getContenido());
                                    System.out.println("Remitente: " + correoEncontrado.getRemitente().getCorreoElectronico());
                                    System.out.println("Fecha: " + dateFormat.format(correoEncontrado.getFecha())); // Imprimir la fecha formateada
                                    System.out.println("Destinatarios: " + correoEncontrado.getDestinatarios().stream().map(Contacto::getCorreoElectronico).collect(Collectors.joining(", ")));
                                    System.out.println("------------");
                                }
                            }
                        } catch (ParseException e) {
                            System.out.println("Formato de fecha incorrecto. El formato debe ser dd/MM/yyyy.");
                        }
                        break;

                    case 6:
                        System.out.print("Ingrese el valor de búsqueda (correo, nombre o apellido): ");
                        String valorBusqueda = scanner.nextLine();
                    
                        FiltroDestinatario filtroDestinatario = new FiltroDestinatario(valorBusqueda);
                        List<Correo> correosFiltradosPorDestinatario = filtroDestinatario.aplicarFiltro(bandejaEnviados.obtenerCorreosEnviados());
                    
                        if (correosFiltradosPorDestinatario.isEmpty()) {
                            System.out.println("No se encontraron correos que coincidan con el valor de búsqueda.");
                        } else {
                            System.out.println("Correos encontrados que coinciden con el valor de búsqueda:");
                            for (Correo correoEncontrado : correosFiltradosPorDestinatario) {
                                System.out.println("Asunto: " + correoEncontrado.getAsunto());
                                System.out.println("Mensaje: " + correoEncontrado.getContenido());
                                System.out.println("Remitente: " + correoEncontrado.getRemitente().getCorreoElectronico());
                                System.out.println("Destinatarios: " + correoEncontrado.getDestinatarios().stream().map(Contacto::getCorreoElectronico).collect(Collectors.joining(", ")));
                                System.out.println("------------");
                            }
                        }
                        break;

                    case 7:
                        List<Correo> correosUCP = FiltroUCPPrioridad.filtrarYPriorizarUCP(bandejaEnviados.obtenerCorreosEnviados());
                        
                        if (correosUCP.isEmpty()) {
                            System.out.println("No se encontraron correos que cumplan ambos criterios.");
                        } else {
                            for (Correo correo : correosUCP) {
                                if ("UCP".equalsIgnoreCase(correo.getAsunto()) && correo.getDestinatarios().stream()
                                    .anyMatch(d -> d.getCorreoElectronico().endsWith("@ucp.edu.ar"))) {
                                    // Cumple ambos criterios, asignar prioridad ALTA
                                    correo.setPrioridad("ALTA");
                                } else {
                                    // No cumple ambos criterios, asignar prioridad NORMAL (si lo deseas)
                                    correo.setPrioridad("NORMAL");
                                }
                                
                                // Imprimir la información del correo
                                System.out.println("Prioridad: " + correo.getPrioridad());
                                System.out.println("Asunto: " + correo.getAsunto());
                                System.out.println("Mensaje: " + correo.getContenido());
                                System.out.println("Remitente: " + correo.getRemitente().getCorreoElectronico());
                                System.out.println("Destinatarios: " + correo.getDestinatarios().stream().map(Contacto::getCorreoElectronico).collect(Collectors.joining(", ")));
                                System.out.println("------------");
                            }
                        }
                        break;

                    case 8:
                        System.out.println("Selecciona un correo que deseas marcar como spam:");
                        // Mostrar la lista de correos electrónicos para que el usuario elija uno

                        int indiceCorreoSpam = Integer.parseInt(scanner.nextLine()) - 1; // Suponiendo que los correos se muestran numerados

                        if (indiceCorreoSpam >= 0 && indiceCorreoSpam < bandejaEnviados.obtenerCorreosEnviados().size()) {
                            Correo correoSpam = bandejaEnviados.obtenerCorreosEnviados().get(indiceCorreoSpam);

                            filtroSpam.marcarComoSpam(correoSpam); // Marcar el correo como spam
                        } else {
                            System.out.println("Índice de correo no válido.");
                        }
                        
                        // Mostrar automáticamente todos los correos marcados como spam con asunto "Mercado Libre" o correo "Mercadolibre@example.com"
                        List<Correo> correosSpamMercadoLibre = new ArrayList<>();
                        for (Correo correo : filtroSpam.obtenerCorreosSpam()) {
                            if (correo.getAsunto().equalsIgnoreCase("Mercado Libre") || correo.getRemitente().getCorreoElectronico().equalsIgnoreCase("Mercadolibre@example.com")) {
                                correosSpamMercadoLibre.add(correo);
                            }
                        }
                        
                        if (!correosSpamMercadoLibre.isEmpty()) {
                            System.out.println("Correos spam con asunto 'Mercado Libre' o correo 'Mercadolibre@example.com':");
                            for (Correo c : correosSpamMercadoLibre) {
                                System.out.println("Asunto: " + c.getAsunto());
                                System.out.println("Contenido: " + c.getContenido());
                                System.out.println("Remitente: " + c.getRemitente().getNombre());
                                System.out.println("Destinatarios: " + c.getDestinatarios().stream().map(Contacto::getNombre).collect(Collectors.joining(", ")));
                                System.out.println("------------");
                            }
                        }
                        break;

                    case 9:
                        System.exit(0);
                        default:
                            System.out.println("Opción no válida.");
                            break;
                }
            }
        } // El Scanner se cerrará automáticamente al salir del bloque try
    }
}