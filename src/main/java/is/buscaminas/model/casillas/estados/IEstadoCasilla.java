package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public interface IEstadoCasilla {
    public abstract Pair<Boolean, Boolean> despejar(Casilla pCasilla);
    public abstract void marcar(Casilla pCasilla);
}
