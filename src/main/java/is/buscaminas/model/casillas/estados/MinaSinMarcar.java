package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public class MinaSinMarcar implements IEstadoCasilla {
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        return null;
    }

    @Override
    public Pair<Boolean, Boolean> marcar(Casilla pCasilla)
    {
        return null;
    }

    @Override
    public void verMinas (Casilla pCasilla) {}
}
