import java.time.LocalDate;

public class Exhibicion implements Programable {

    private String titulo;
    private LocalDate fecha;
    private int hora;
    private String sede;
    private EstadoExhibicion estado;

    public Exhibicion(String titulo) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El titulo no puede estar vacio."
            );
        }

        this.titulo = titulo.trim();
        this.fecha = null;
        this.hora = -1;
        this.sede = null;
        this.estado = EstadoExhibicion.SIN_PROGRAMAR;
    }

    @Override
    public void programar(LocalDate fecha, int hora, String sede) {

        if (fecha == null) {
            throw new IllegalArgumentException(
                "La fecha no puede estar vacia."
            );
        }

        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException(
                "La hora debe estar entre 0 y 23."
            );
        }

        if (sede == null || sede.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La sede no puede estar vacia."
            );
        }

        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede.trim();
        this.estado = EstadoExhibicion.PROGRAMADA;
    }

    @Override
    public boolean tieneConflictoEquipo(Programable otro) {
        return false;
    }

    @Override
    public boolean participaEquipo(Equipo equipo) {
        return false;
    }

    @Override
    public Puntuable getPuntuable() {
        return null;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public int getHora() {
        return hora;
    }

    @Override
    public String getSede() {
        return sede;
    }

    @Override
    public boolean estaProgramado() {
        return estado == EstadoExhibicion.PROGRAMADA;
    }

    @Override
    public String getDescripcion() {
        return "Exhibicion: " + titulo;
    }
}