package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import is.buscaminas.model.casillas.CasillaNum;
import javafx.util.Pair;

public class Oculto implements IEstadoCasilla {

    @Override
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        //Pre: Recibe una casilla para despejar
        //Post: Dependiendo del tipo de casilla devuelve un par de booleans diferente (ver tabla valores en el método despejar de Tabla).

        pCasilla.cambiarEstado(new Despejado());
        if (pCasilla instanceof CasillaNum) {
            return new Pair<>(((CasillaNum) pCasilla).estaDespejado(), true);
        }
        else {
            return new Pair<>(true, false);
        }
    }

    @Override
    public Pair<Boolean, Boolean> marcar(Casilla pCasilla)
    {
        //Pre: Se recibe una casilla para marcar
        //Post: Se cambia el estado a marcado. Al marcar esta casilla, devolvemos el par (TRUE,TRUE)

        pCasilla.cambiarEstado(new Marcado());
        return new Pair<>(true,true);
    }

}
