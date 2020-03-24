package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import is.buscaminas.model.casillas.CasillaNum;
import javafx.util.Pair;

public class Oculto implements IEstadoBoton {
    @Override
    public Pair<Boolean, Boolean> despejar (Casilla pCasilla)
    {
        pCasilla.cambiarEstado(new Despejado());
        if (pCasilla instanceof CasillaNum) {
            return new Pair<>(((CasillaNum) pCasilla).estaDespejado(), true);
        }
        else {
            return new Pair<>(true, false);
        }
    }
}
