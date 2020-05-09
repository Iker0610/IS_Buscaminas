package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import javafx.util.Pair;

public interface IEstadoCasilla {
    public int despejar(Casilla pCasilla);
    public Pair<Boolean, Boolean> marcar(Casilla pCasilla);
    public void verMinas(Casilla pCasilla);
}
