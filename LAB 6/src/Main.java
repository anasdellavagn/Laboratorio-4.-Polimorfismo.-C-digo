import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Copa copa = new Copa();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosIniciales();
        mostrarMenu();
        scanner.close();
    }

    private static void cargarDatosIniciales() {
        Equipo faro = new Equipo("E01", "Faro", "Empresa A");
        Equipo luna = new Equipo("E02", "Luna", "Empresa B");
        Equipo sol = new Equipo("E03", "Sol", "Empresa A");
        Equipo norte = new Equipo("E04", "Norte", "Empresa B");

        copa.registrarEquipo(faro);
        copa.registrarEquipo(luna);
        copa.registrarEquipo(sol);
        copa.registrarEquipo(norte);

        Encuentro e1 = new FutbolSala(faro, luna);
        Encuentro e2 = new Baloncesto(sol, norte);
        Encuentro e3 = new FutbolSala(faro, sol);
        Encuentro e4 = new Baloncesto(luna, norte);

        copa.agregarEvento(e1);
        copa.agregarEvento(e2);
        copa.agregarEvento(e3);
        copa.agregarEvento(e4);

        LocalDate fecha = LocalDate.of(2026, 11, 15);

        copa.programarEvento(e1, fecha, 9, "Cancha A");
        copa.programarEvento(e2, fecha, 11, "Gimnasio");
        copa.programarEvento(e3, fecha.plusDays(1), 10, "Cancha A");
        copa.programarEvento(e4, fecha.plusDays(1), 12, "Gimnasio");

        e1.registrarResultado(2, 2);

        e2.registrarResultado(78, 74);

        e3.registrarResultado(1, 1);

        Exhibicion exhibicion =
                new Exhibicion("Clinica de baloncesto");

        copa.agregarEvento(exhibicion);
        copa.programarEvento(exhibicion, fecha, 14, "Gimnasio");
    }

    private static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== ARENAGT LITE =====");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Crear encuentro");
            System.out.println("3. Crear exhibicion");
            System.out.println("4. Programar o reprogramar evento");
            System.out.println("5. Registrar resultado");
            System.out.println("6. Mostrar tabla de posiciones");
            System.out.println("7. Mostrar calendario");
            System.out.println("8. Mostrar todos los eventos");
            System.out.println("0. Salir");

            opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1:
                        registrarEquipo();
                        break;
                    case 2:
                        crearEncuentro();
                        break;
                    case 3:
                        crearExhibicion();
                        break;
                    case 4:
                        programarEvento();
                        break;
                    case 5:
                        registrarResultado();
                        break;
                    case 6:
                        mostrarTabla();
                        break;
                    case 7:
                        mostrarCalendario();
                        break;
                    case 8:
                        mostrarEventos();
                        break;
                    case 0:
                        System.out.println("Hasta pronto.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (IllegalArgumentException
                    | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private static void registrarEquipo() {
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        Equipo equipo = new Equipo(codigo, nombre, empresa);
        copa.registrarEquipo(equipo);

        System.out.println("Equipo registrado correctamente.");
    }

    private static void crearEncuentro() {

        System.out.println("1. Futbol sala");
        System.out.println("2. Baloncesto");

        int deporte = leerEntero("Deporte: ");

        if (deporte != 1 && deporte != 2) {
            System.out.println("Deporte no valido.");
            return;
        }

        mostrarEquipos();

        System.out.print("Codigo del equipo 1: ");
        Equipo e1 = copa.buscarEquipo(scanner.nextLine());

        System.out.print("Codigo del equipo 2: ");
        Equipo e2 = copa.buscarEquipo(scanner.nextLine());

        if (e1 == null || e2 == null) {
            System.out.println("Uno de los equipos no existe.");
            return;
        }

        Encuentro encuentro;

        if (deporte == 1) {
            encuentro = new FutbolSala(e1, e2);
        } else {
            encuentro = new Baloncesto(e1, e2);
        }

        copa.agregarEvento(encuentro);

        System.out.println("Encuentro creado correctamente.");
    }

    private static void crearExhibicion() {

        System.out.print("Titulo de la exhibicion: ");
        String titulo = scanner.nextLine();

        copa.agregarEvento(new Exhibicion(titulo));

        System.out.println("Exhibicion creada correctamente.");
    }

    private static void programarEvento() {
        Programable evento = seleccionarEvento();

        if (evento == null) {
            return;
        }

        LocalDate fecha = leerFecha();
        int hora = leerEntero("Hora (0-23): ");

        System.out.print("Sede: ");
        String sede = scanner.nextLine();

        copa.programarEvento(evento, fecha, hora, sede);

        System.out.println("Evento programado correctamente.");
    }

    
    private static void registrarResultado() {

        List<Puntuable> encuentros = new ArrayList<>();

        for (Programable evento : copa.getEventos()) {
            Puntuable puntuable = evento.getPuntuable();

            if (puntuable != null) {
                encuentros.add(puntuable);
            }
        }

        if (encuentros.isEmpty()) {
            System.out.println("No hay encuentros.");
            return;
        }

        System.out.println("\n===== ENCUENTROS =====");

        for (int i = 0; i < encuentros.size(); i++) {
            System.out.println(
                (i + 1) + ". Encuentro " + (i + 1)
            );
        }

        int indice = leerEntero("Seleccione encuentro: ") - 1;

        if (indice < 0 || indice >= encuentros.size()) {
            System.out.println("Encuentro no valido.");
            return;
        }

        int m1 = leerEntero("Marcador equipo 1: ");
        int m2 = leerEntero("Marcador equipo 2: ");

        encuentros.get(indice).registrarResultado(m1, m2);

        System.out.println("Resultado registrado correctamente.");
    }


    private static void mostrarTabla() {
        System.out.println("\n===== TABLA DE POSICIONES =====");

        int posicion = 1;

        for (Equipo equipo : copa.getTablaPosiciones()) {
            System.out.println(posicion + ". " + equipo);
            posicion++;
        }
    }

    private static void mostrarCalendario() {
        System.out.println("\n===== CALENDARIO =====");

        boolean hayEventos = false;

        for (Programable evento : copa) {
            hayEventos = true;

            System.out.println(
                    evento.getFecha()
                    + " | "
                    + String.format("%02d:00", evento.getHora())
                    + " | "
                    + evento.getSede()
                    + " | "
                    + evento.getDescripcion()
            );
        }

        if (!hayEventos) {
            System.out.println("No hay eventos programados.");
        }
    }

    private static void mostrarEventos() {

        System.out.println("\n===== EVENTOS =====");

        List<Programable> eventos = copa.getEventos();

        for (int i = 0; i < eventos.size(); i++) {
            Programable evento = eventos.get(i);

            System.out.println(
                    (i + 1) + ". " + evento.getDescripcion()
            );
        }
    }

    private static void mostrarEquipos() {

        System.out.println("\n===== EQUIPOS =====");

        for (Equipo equipo : copa.getTablaPosiciones()) {
            System.out.println(equipo);
        }
    }

    private static Programable seleccionarEvento() {

        mostrarEventos();

        int indice =
                leerEntero("Seleccione evento: ") - 1;

        List<Programable> eventos = copa.getEventos();

        if (indice < 0 || indice >= eventos.size()) {
            System.out.println("Evento no valido.");
            return null;
        }

        return eventos.get(indice);
    }

    private static int leerEntero(String mensaje) {

        int numero = 0;
        boolean valido = false;

        do {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                numero = Integer.parseInt(entrada.trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: ingrese un numero entero."
                );
            }

        } while (!valido);

        return numero;
    }

    private static LocalDate leerFecha() {

        LocalDate fecha = null;

        do {
            System.out.print("Fecha (AAAA-MM-DD): ");
            String entrada = scanner.nextLine();

            try {
                fecha = LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Error: fecha incorrecta."
                );
            }

        } while (fecha == null);

        return fecha;
    }
}