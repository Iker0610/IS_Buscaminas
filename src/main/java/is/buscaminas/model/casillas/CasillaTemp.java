package is.buscaminas.model.casillas;

import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

public class CasillaTemp extends Casilla{

    public CasillaTemp(VistaCasilla pVistaCasilla) {
        super(pVistaCasilla);
    }

    @Override
    public Pair<Boolean, Boolean> despejar ()
    {
        //Pre:
        //Post: Un par de booleans indicando qué acción debe realizar la tabla

        return new Pair<>(false,false);
    }
}
