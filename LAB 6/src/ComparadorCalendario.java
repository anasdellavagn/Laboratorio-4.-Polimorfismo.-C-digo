import java.util.Comparator;

public class ComparadorCalendario
        implements Comparator<Programable> {

    @Override
    public int compare(Programable e1, Programable e2) {

        int resultado = e1.getFecha().compareTo(e2.getFecha());

        if (resultado != 0) {
            return resultado;
        }

        resultado = Integer.compare(e1.getHora(), e2.getHora());

        if (resultado != 0) {
            return resultado;
        }

        return e1.getSede().compareToIgnoreCase(e2.getSede());
    }
}
