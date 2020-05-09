package is.buscaminas.model.casillas;

import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

public class CasillaTemp extends Casilla{

    public CasillaTemp(VistaCasilla pVistaCasilla) {
        super(pVistaCasilla);
    }

    @Override
    public int despejar ()
    {
        //Pre:
        //Post: Devuelve 0 indicando que no hay que hacer nada. Esta opción nunca debería *debería* presentarse. Pero por si las moscas.
        return 0;
    }
}
