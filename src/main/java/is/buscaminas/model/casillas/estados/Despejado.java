package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public class Despejado implements IEstadoBoton {

    @Override
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        return new Pair<>(false,false);
    }
}
