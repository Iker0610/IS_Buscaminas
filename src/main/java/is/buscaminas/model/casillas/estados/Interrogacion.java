package is.buscaminas.model.casillas.estados;

import is.buscaminas.model.casillas.Casilla;
import is.buscaminas.model.casillas.CasillaNum;
import javafx.util.Pair;

public class Interrogacion implements IEstadoCasilla {

    @Override
    public int despejar(Casilla pCasilla)
    {
        //Pre: Recibe una casilla para despejar
        //Post: Dependiendo del tipo de casilla devuelve número diferente (ver tabla valores en el método despejar de Tabla).

        pCasilla.cambiarEstado(new Despejado());
        if (pCasilla instanceof CasillaNum)                                     // Si no es mina
        {
            if (((CasillaNum) pCasilla).tieneCeroMinasAdyacentes()) return 3;       // si tiene 0 minas adyacentes
            else return 1;                                                          // Si tiene alguna mina adyacente
        }
        else return 2;                                                          // Si es mina
    }

    @Override
    public Pair<Boolean, Boolean> marcar(Casilla pCasilla) {
        //Pre: Se recibe una casilla para marcar
        //Post: Se cambia el estado a oculto. Como la casilla interrogación no cuenta como marcada, se devuelve (false,false)

        pCasilla.cambiarEstado(new Oculto());
        return new Pair<>(false,false);
    }
}
