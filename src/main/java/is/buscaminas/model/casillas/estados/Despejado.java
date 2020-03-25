package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public class Despejado implements IEstadoBoton {

    @Override
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        //Pre: Una casilla
        //Post: Devuelve (false,false), indicando que la casilla ya esta despejada por lo que no tiene que hacer nada

        return new Pair<>(false,false);
    }
}
