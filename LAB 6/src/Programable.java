import java.time.LocalDate;
public interface Programable {
    void programar(LocalDate fecha, int hora, String sede);

    LocalDate getFecha();

    int getHora();

    String getSede();

    boolean estaProgramado();

    String getDescripcion();

    boolean tieneConflictoEquipo(Programable otro);

    boolean participaEquipo(Equipo equipo);

    default Puntuable getPuntuable() {
        return null;
    }
}