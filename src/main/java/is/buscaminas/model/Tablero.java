package is.buscaminas.model;

import is.buscaminas.Partida;
import is.buscaminas.model.casillas.Casilla;

import java.util.List;

public class Tablero {
    private static Tablero mTablero;
    private Casilla[][] matrizCasillas;
    private int casillasPorDespejar; //Las que no son Bom-omb y aún son ladrillos

    //Constructora
    private Tablero(){
        matrizCasillas = generarMatrizTablero();

    }

    public static Tablero getTablero ()
    {
        if (mTablero == null) mTablero = new Tablero();
        return mTablero;
    }

    //Metodo para gene
    private void generarMatrizTablero (){
        int dificultad = Partida.getPartida.getDificultad();
    }
}
