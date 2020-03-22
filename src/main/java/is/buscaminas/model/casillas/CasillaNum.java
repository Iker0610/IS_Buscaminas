package is.buscaminas.model.casillas;

import is.buscaminas.view.BotonCasilla;
import javafx.util.Pair;

public class CasillaNum extends Casilla {

    public CasillaNum (int minasAdyacentes, BotonCasilla pBotonCasilla) {super(pBotonCasilla);}

    @Override
    public Pair<Boolean, Boolean> despejar ()
    {
        //TODO
        return null;
    }
}
