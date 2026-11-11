public class Baloncesto extends Encuentro {

    public Baloncesto(Equipo equipo1, Equipo equipo2) {
        super(equipo1, equipo2);
    }

    @Override
    protected void validarMarcador(int m1, int m2) {
        if (m1 < 0 || m2 < 0) {
            throw new IllegalArgumentException(
                "Los puntos no pueden ser negativos."
            );
        }

        if (m1 == m2) {
            throw new IllegalArgumentException(
                "En baloncesto no se permiten empates."
            );
        }
    }

    @Override
    protected int calcularPuntos(int propio, int rival) {
        if (propio > rival) {
            return 2;
        }
        return 1;
    }

    @Override
    public String getDescripcion() {
        return "Baloncesto: " + super.getDescripcion();
    }
}

