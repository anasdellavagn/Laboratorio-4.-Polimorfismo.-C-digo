import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Copa implements Iterable<Programable> {

    private ArrayList<Equipo> equipos;
    private ArrayList<Programable> eventos;

    public Copa() {
        equipos = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    public void registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException(
                "El equipo no puede ser nulo."
            );
        }

        if (buscarEquipo(equipo.getCodigo()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un equipo con ese codigo."
            );
        }

        equipos.add(equipo);
    }

    public void agregarEvento(Programable evento) {
        if (evento == null) {
            throw new IllegalArgumentException(
                "El evento no puede ser nulo."
            );
        }

        if (eventos.contains(evento)) {
            throw new IllegalArgumentException(
                "El evento ya esta registrado."
            );
        }

        eventos.add(evento);
    }

    public Equipo buscarEquipo(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().equalsIgnoreCase(
                    codigo.trim())) {
                return equipo;
            }
        }

        return null;
    }

    public void programarEvento(
            Programable evento,
            LocalDate fecha,
            int hora,
            String sede) {

        if (evento == null || !eventos.contains(evento)) {
            throw new IllegalArgumentException(
                "El evento no esta registrado."
            );
        }

        if (fecha == null || sede == null
                || sede.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Fecha y sede son obligatorias."
            );
        }

        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException(
                "La hora debe estar entre 0 y 23."
            );
        }

        if (verificarConflictoSede(
                evento, fecha, hora, sede)) {
            throw new IllegalArgumentException(
                "La sede ya esta ocupada."
            );
        }

        if (verificarConflictoEquipo(
                evento, fecha, hora)) {
            throw new IllegalArgumentException(
                "Uno de los equipos ya tiene otro encuentro."
            );
        }

        evento.programar(fecha, hora, sede);
    }

    private boolean verificarConflictoSede(
            Programable actual,
            LocalDate fecha,
            int hora,
            String sede) {

        for (Programable evento : eventos) {
            if (evento == actual || !evento.estaProgramado()) {
                continue;
            }

            if (evento.getFecha().equals(fecha) && evento.getHora() == hora && evento.getSede().equalsIgnoreCase( sede.trim())) {
                return true;
            }
        }

        return false;
    }

    private boolean verificarConflictoEquipo(
            Programable actual,
            LocalDate fecha,
            int hora) {

        for (Programable evento : eventos) {
            if (evento == actual || !evento.estaProgramado()) {
                continue;
            }

            if (evento.getFecha().equals(fecha)
                    && evento.getHora() == hora
                    && actual.tieneConflictoEquipo(evento)) {
                return true;
            }
        }

        return false;
    }

    public List<Equipo> getTablaPosiciones() {
        ArrayList<Equipo> tabla =
                new ArrayList<>(equipos);

        Collections.sort(tabla);
        return tabla;
    }

    public List<Programable> getEventos() {
        return Collections.unmodifiableList(eventos);
    }

    @Override
    public Iterator<Programable> iterator() {
        ArrayList<Programable> calendario =
                new ArrayList<>();

        for (Programable evento : eventos) {
            if (evento.estaProgramado()) {
                calendario.add(evento);
            }
        }

        calendario.sort(new ComparadorCalendario());

        return calendario.iterator();
    }
}
