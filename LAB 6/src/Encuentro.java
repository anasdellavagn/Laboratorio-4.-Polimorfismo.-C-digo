import java.time.LocalDate;

public abstract class Encuentro implements Programable, Puntuable {

    private Equipo equipo1;
    private Equipo equipo2;
    private int marcador1;
    private int marcador2;
    private EstadoEncuentro estado;
    private LocalDate fecha;
    private int hora;
    private String sede;

    protected Encuentro(Equipo equipo1, Equipo equipo2) {

        if (equipo1 == null || equipo2 == null) {
            throw new IllegalArgumentException(
                "Los equipos no pueden ser nulos."
            );
        }

        if (equipo1.getCodigo().equalsIgnoreCase(
                equipo2.getCodigo())) {
            throw new IllegalArgumentException(
                "Un equipo no puede jugar contra si mismo."
            );
        }

        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.marcador1 = 0;
        this.marcador2 = 0;
        this.estado = EstadoEncuentro.SIN_PROGRAMAR;
        this.fecha = null;
        this.hora = -1;
        this.sede = null;
    }

    @Override
    public void programar(LocalDate fecha, int hora, String sede) {

        if (estado == EstadoEncuentro.FINALIZADO) {
            throw new IllegalStateException(
                "No se puede reprogramar un encuentro finalizado."
            );
        }

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
        this.estado = EstadoEncuentro.PROGRAMADO;
    }

    @Override
    public void registrarResultado(int m1, int m2) {

        if (estado != EstadoEncuentro.PROGRAMADO) {
            throw new IllegalStateException(
                "El encuentro no esta disponible para resultados."
            );
        }

        if (m1 < 0 || m2 < 0) {
            throw new IllegalArgumentException(
                "Los marcadores no pueden ser negativos."
            );
        }

        validarMarcador(m1, m2);

        int puntos1 = calcularPuntos(m1, m2);
        int puntos2 = calcularPuntos(m2, m1);

        equipo1.sumarPuntos(puntos1);
        equipo2.sumarPuntos(puntos2);

        this.marcador1 = m1;
        this.marcador2 = m2;
        this.estado = EstadoEncuentro.FINALIZADO;
    }

    protected abstract void validarMarcador(int m1, int m2);

    protected abstract int calcularPuntos(
        int marcadorPropio,
        int marcadorRival
    );

    @Override
    public int getPuntosEquipo1() {
        if (estado != EstadoEncuentro.FINALIZADO) {
            return 0;
        }
        return calcularPuntos(marcador1, marcador2);
    }

    @Override
    public int getPuntosEquipo2() {
        if (estado != EstadoEncuentro.FINALIZADO) {
            return 0;
        }
        return calcularPuntos(marcador2, marcador1);
    }

    @Override
    public boolean tieneConflictoEquipo(Programable otro) {
        if (otro == null || otro == this) {
            return false;
        }

        return otro.participaEquipo(equipo1)|| otro.participaEquipo(equipo2);
    }

    @Override
    public boolean participaEquipo(Equipo equipo) {
        return equipo != null
            && (equipo1 == equipo || equipo2 == equipo);
    }

    @Override
    public Puntuable getPuntuable() {
        return this;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public EstadoEncuentro getEstado() {
        return estado;
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
        return estado != EstadoEncuentro.SIN_PROGRAMAR;
    }

    @Override
    public String getDescripcion() {
        String descripcion = equipo1.getNombre() + " vs " + equipo2.getNombre();

        if (estado == EstadoEncuentro.FINALIZADO) {
            descripcion += " | Resultado: " + marcador1 + " - " + marcador2;
        }

        return descripcion;
    }
}
