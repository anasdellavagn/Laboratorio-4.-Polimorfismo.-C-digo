public class FutbolSala extends Encuentro {

    public FutbolSala(Equipo equipo1, Equipo equipo2) {
        super(equipo1, equipo2);
    }

    @Override
    protected void validarMarcador(int m1, int m2) {
        if (m1 < 0 || m2 < 0) {
            throw new IllegalArgumentException(
                "Los goles no pueden ser negativos."
            );
        }
    }

    @Override
    protected int calcularPuntos(int propio, int rival) {
        if (propio > rival) {
            return 3;
        } else if (propio == rival) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String getDescripcion() {
        return "Futbol sala: " + super.getDescripcion();
    }
}