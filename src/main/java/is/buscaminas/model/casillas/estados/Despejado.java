package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public class Despejado implements IEstadoCasilla {

    @Override
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        //Pre: Una casilla
        //Post: Devuelve (false,false), indicando que la casilla ya esta despejada por lo que no tiene que hacer nada

        return new Pair<>(false,false);
    }

    @Override
    public Pair<Boolean, Boolean> marcar(Casilla pCasilla)
    {
        //Pre: Una casilla
        //Post: Devuelve 0 ya que no se ha marcado ni desmarcado la casilla
        return new Pair<>(false,false); //No ocurre nada si se manda marcar una casilla despejada
    }

    @Override
    public void verMinas (Casilla pCasilla) {}
}
