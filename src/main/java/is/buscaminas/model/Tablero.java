package is.buscaminas.model;

import is.buscaminas.model.casillas.Casilla;

import java.util.List;

public class Tablero {
    private static Tablero mTablero;
    private Casilla[][] matrizCasillas;
    private List<Casilla> casillasMarcadas;
}
