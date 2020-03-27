package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public class Marcado implements IEstadoCasilla{

    @Override
    public Pair<Boolean, Boolean> despejar(Casilla pCasilla)
    {
        //Pre: Se recibe una casilla para despejar
        //Post: Puesto que la casilla está marcada, no se realiza ninguna acción, por lo que se devuelve el par (false,false)

        return new Pair<>(false,false);
    }

    @Override
    public void marcar(Casilla pCasilla)
    {
        //Pre: Se recibe una casilla para marcar
        //Post: Se cambia el estado a Interrogacion

        pCasilla.cambiarEstado(new Interrogacion());
    }
}
