package is.buscaminas.model;


import is.buscaminas.Partida;
import is.buscaminas.model.casillas.Casilla;
import is.buscaminas.model.casillas.CasillaMina;
import is.buscaminas.model.casillas.CasillaNum;
import is.buscaminas.model.casillas.CasillaTemp;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class Tablero {

    //Atributos
    private PropertyChangeSupport lObservers;

    private static Tablero mTablero;
    private Casilla[][] matrizCasillas;
    private int casillasPorDespejar; //Las que no son minas y aún ocultas
    private int numMinas;
    private int numMinasPorMarcar; //numero de minas que te quedan por marcar (si marcas demasiadas casillas, será un num negativo)


    //Constructora
    private Tablero ()
    {
        lObservers = new PropertyChangeSupport(this);
        generarMatrizTablero();
    }

    public static Tablero getTablero ()
    {
        if (mTablero == null) mTablero = new Tablero();
        return mTablero;
    }

    public void iniciarTablero ()
    {
        mTablero = new Tablero();
    }

    //Metodos--------------------------------------------

    //Metodo para añadir observer del número del marcador de minas

    public void addObserver (PropertyChangeListener pObserver)
    {
        //Pre: Un observer
        //Post: Se ha añadido el observer a la lista de observers

        lObservers.addPropertyChangeListener(pObserver);
        lObservers.firePropertyChange("numMinasPorMarcar", null, numMinasPorMarcar); //para que al empezar la partida tengamos el número en pantalla
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
        numMinasPorMarcar = numMinas;
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

    public void marcarPrevio (int pFila, int pColumna, VistaCasilla pCasillaMarcada)
    {
        // Si no está generado el tablero y se hace click derecho, se genera una CasillaTemp temporal

        //      Pre:    - Fila y columna de la primera casilla seleccionada por el jugador
        //              - Referencias a la casilla de la Vista que debe ser marcada
        //      Post: Se ha generado una casilla marcada temporal

        // Se comprueba que en esa casilla no haya nada
        if (matrizCasillas[pFila][pColumna] == null) {
            //Se crea la CasillaNum temporal para que pueda ser marcada
            matrizCasillas[pFila][pColumna] = new CasillaTemp(pCasillaMarcada);
        }

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

            // Se comprueba que no sea ni la primera ni ninguna de las adyacentes
            // y que en esa casilla no haya nada o haya una CasillaTemp (significaría que la casilla es temporal, por lo que será cambiada a CasillaMina)

            // Si cumplen las condiciones se añade, de lo contrario se obtiene otra coordenada aleatoria
            if ((Math.abs(pFila - fila) > 1 || Math.abs(pColumna - columna) > 1)) // Si no es adyacente a la casilla del click
            {
                // Si la casilla esta vacía, se crea mina y se disminuyen las minas restantes
                if (matrizCasillas[fila][columna] == null) {
                    matrizCasillas[fila][columna] = new CasillaMina(pMatrizBotones[fila][columna]);
                    numMinas--;
                }
                // Si habia una casillaTemp:
                // Se manda crear una casilla mina pasandole la casillaTemp .
                // También disminuyen las minas restantes
                else if (matrizCasillas[fila][columna] instanceof CasillaTemp) {
                    //mando a la mina temp crear una casilla mina igual a ella
                    matrizCasillas[fila][columna] = new CasillaMina((CasillaTemp) matrizCasillas[fila][columna]);
                    numMinas--;
                }
            }
        }
    }

    private void generarNoMinas (VistaCasilla[][] pMatrizBotones)
    {
        //Pre: Matriz con referencias a las casillas de la Vista
        //Post: Se han generado las casillas que no contienen minas

        for (int fila = 0; fila < matrizCasillas.length; fila++) {
            for (int columna = 0; columna < matrizCasillas[fila].length; columna++) {

                // Si la casilla no ha sido creada, se crea
                if (matrizCasillas[fila][columna] == null) {
                    int minasAdyacentes = calcularMinasAdyacentes(fila, columna);
                    matrizCasillas[fila][columna] = new CasillaNum(minasAdyacentes, pMatrizBotones[fila][columna]);
                }

                // Si la casilla es de tipo casillaTemp
                // Se le manda crear una casillaNum dandole un valor de numMinasAdyacentes y la casillaTemp.
                else if (matrizCasillas[fila][columna] instanceof CasillaTemp) {
                    int minasAdyacentes = calcularMinasAdyacentes(fila, columna);
                    matrizCasillas[fila][columna] = new CasillaNum(minasAdyacentes, (CasillaTemp) matrizCasillas[fila][columna]);
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

    public void marcarCasilla (int pFila, int pColumna)
    {
        //Pre:  Recibe el número de fila y columna de la casilla seleccionada a ser marcada
        //Post: Se marca, desmarca o "interroga" la casilla en cuestión situada en esa fila y columna

        //      El resultado devuelto por la función marcar tiene la forma [key,value] (un par de (boolean,boolean))
        //      El primer boolean señala si se ha modificado el estado de la casilla (es decir, si se a marcado o desmarcado toma el valor TRUE)
        //      El segundo boolean (solo válido si el primer boolean es TRUE) indica con un TRUE si se ha marcado una casilla y FALSE si se ha desmarcado

        // (0,X) -> No se ha modificado nada (por conveniencia, siempre devolveremos FALSE en el segundo bit del par)
        // (1,0) -> Se ha desmarcado una casilla
        // (1,1) -> Se ha marcado una casilla


        if (Partida.getPartida().hayPartidaActiva()) //Si hay una partida activa
        {
            Pair<Boolean, Boolean> resultado = matrizCasillas[pFila][pColumna].marcar();

            if (resultado.getKey()) //Si se ha hecho algo (Primer boolean = TRUE)
            {
                if (resultado.getValue())           //Si se ha marcado una casilla (Segundo boolean = TRUE) se disminuye el numero de banderas "numMinasPorMarcar"
                {
                    lObservers.firePropertyChange("numMinasPorMarcar", numMinasPorMarcar, --numMinasPorMarcar);
                }
                else {                              //Si se ha desmarcado una casilla (Segundo boolean = FALSE) se aumenta el numero de banderas "numMinasPorMarcar"
                    lObservers.firePropertyChange("numMinasPorMarcar", numMinasPorMarcar, ++numMinasPorMarcar);
                }
            }
        }
    }
}
