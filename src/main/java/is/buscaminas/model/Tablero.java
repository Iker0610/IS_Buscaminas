package is.buscaminas.model;


import is.buscaminas.Partida;
import is.buscaminas.model.casillas.Casilla;
import is.buscaminas.model.casillas.CasillaMina;
import is.buscaminas.model.casillas.CasillaNum;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.util.Random;

public class Tablero {

    //Atributos
    private static Tablero mTablero;
    private Casilla[][] matrizCasillas;
    private int casillasPorDespejar; //Las que no son minas y aún ocultas
    private int numMinas;


    //Constructora
    private Tablero ()
    {
        generarMatrizTablero();
    }

    public static Tablero getTablero ()
    {
        if (mTablero == null) mTablero = new Tablero();
        return mTablero;
    }

    //Metodo para generar la matriz
    private void generarMatrizTablero ()
    {
        //Pre:
        //Post: - Se ha generado una matriz del tamaño indicado para el nivel de dificultad seleccionado
        //      - Se ha guardado el número de minas
        //      - Se ha guardado el número de casillas sin minas

        int dificultad = Partida.getPartida().getDificultad();
        switch (dificultad) {

            case 2:
                matrizCasillas = new Casilla[10][15];
                break;

            case 3:
                matrizCasillas = new Casilla[12][25];
                break;

            default:
                matrizCasillas = new Casilla[7][10];
                break;
        }
        numMinas = matrizCasillas[0].length * dificultad;
        casillasPorDespejar = (matrizCasillas.length * matrizCasillas[0].length) - numMinas;
    }

    //----------------------------------------------
    //Metodos para generar las casillas del tablero
    //----------------------------------------------
    public void generarCasillasTablero (int pFila, int pColumna, VistaCasilla[][] pMatrizBotones)
    {
        //* Este método es llamado por el controller cuando el usuario hace el primer click

        //Pre: - Fila y columna de la primera casilla seleccionada por el jugador
        //     - Matriz con referencias a las casillas de la Vista
        //Post: Se ha generado el tablero de casillas

        //Primero se generan las minas
        generarMinas(pFila, pColumna, pMatrizBotones);

        //El resto de casillas se rellenan con casillas sin minas
        generarNoMinas(pMatrizBotones);
    }

    private void generarMinas (int pFila, int pColumna, VistaCasilla[][] pMatrizBotones)
    {
        //Pre: - Fila y columna de la primera casilla seleccionada por el jugador
        //     - Matriz con referencias a las casillas de la Vista
        //Post: Se generan las minas con los siguientes criterios:
        //      - El número es el adecuado a la dificultad
        //      - Las minas se colocan aleatoriamente
        //      - No habrá ninguna mina ni en la primera casilla seleccionada por el jugador ni en sus adyacentes.

        int numMinas = this.numMinas;
        int fila, columna;
        Random random = new Random();

        while (numMinas > 0) {
            //Se obtienen 2 valores random que correspondan a posiciones dentro del tablero:
            fila = random.nextInt(matrizCasillas.length);
            columna = random.nextInt(matrizCasillas[0].length);

            //Se comprueba que en esa casilla no haya nada y que no sea ni la primera ni ninguna de las adyacentes
            // Si cumple las condiciones se añade, de lo contrario se obtiene otra coordenada aleatoria
            if ((matrizCasillas[fila][columna] == null) && (Math.abs(pFila - fila) > 1 || Math.abs(pColumna - columna) > 1)) {
                matrizCasillas[fila][columna] = new CasillaMina(pMatrizBotones[fila][columna]);
                numMinas--;
            }
        }
    }

    private void generarNoMinas (VistaCasilla[][] pMatrizBotones)
    {
        //Pre: Matriz con referencias a las casillas de la Vista
        //Post: Se han generado las casillas que no contienen minas

        for (int fila = 0; fila < matrizCasillas.length; fila++) {
            for (int columna = 0; columna < matrizCasillas[fila].length; columna++) {
                if (matrizCasillas[fila][columna] == null) {
                    int minasAdyacentes = calcularMinasAdyacentes(fila, columna);
                    matrizCasillas[fila][columna] = new CasillaNum(minasAdyacentes, pMatrizBotones[fila][columna]);
                }
            }
        }
    }

    private int calcularMinasAdyacentes (int pFila, int pColumna)
    {
        //Pre:  La fila y la columna pertenecen a valores de la matriz
        //Post: Devuelve el número de minas adyacentes de una casilla

        int minasAdyacentes = 0;

        for (int fila = pFila - 1; fila <= pFila + 1; fila++) {
            for (int columna = pColumna - 1; columna <= pColumna + 1; columna++) {
                //Se comprueba que se está dentro del tablero
                if (0 <= fila && fila < matrizCasillas.length && 0 <= columna && columna < matrizCasillas[0].length) {
                    //Si es mina se incrementa el contador
                    if (matrizCasillas[fila][columna] instanceof CasillaMina) minasAdyacentes++;
                }
            }
        }

        return minasAdyacentes;
    }
    //----------------------------------------------

    //---------------------------------------------------------------------------
    //Metodos accesibles por el controlador a la hora de seleccionar casillas
    //---------------------------------------------------------------------------
    public void despejarCasilla (int pFila, int pColumna)
    {
        //Pre:  La fila y la columna pertenecen a valores de la matriz
        //Post: - Se ha despejado la casilla en caso de poderse
        //      - Segun lo devuelto por la casilla se ejecutan las siguientes acciones:

        /* resultado, código boolean (key, value):
         * - (0,0) = no hacer absolutamente nada
         * - (0,1) = no hacer nada en especial, únicamente disminuir el contador 'casillasPorDespejar'
         * - (1,0) = mina, fin de la partida
         * - (1,1) = casilla sin minas adyacentes -> Despejar las de alrededor y decrementar el contador 'casillasPorDespejar'
         */
        if (Partida.getPartida().hayPartidaActiva()) {
            Pair<Boolean, Boolean> resultado = matrizCasillas[pFila][pColumna].despejar();

            if (resultado.getValue()) {
                casillasPorDespejar--;
            }
            if (casillasPorDespejar == 0) {
                Partida.getPartida().finalizarPartida(true);
            }
            else if (resultado.getKey()) {
                if (resultado.getValue()) {
                    //En caso de que se haya despejado una mina con número 0;
                    //Se despejan todas las minas alrededor
                    for (int fila = pFila - 1; fila <= pFila + 1; fila++) {
                        for (int columna = pColumna - 1; columna <= pColumna + 1; columna++) {
                            //Se comprueba que se está dentro del tablero
                            if (0 <= fila && fila < matrizCasillas.length && 0 <= columna &&
                                columna < matrizCasillas[0].length) {
                                despejarCasilla(fila, columna);
                            }
                        }
                    }
                }
                else {
                    //En caso de bomba
                    Partida.getPartida().finalizarPartida(false);
                }
            }
        }
    }
    //----------------------------------------------
}
