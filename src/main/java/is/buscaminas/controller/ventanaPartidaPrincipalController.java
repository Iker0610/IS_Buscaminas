package is.buscaminas.controller;

import is.buscaminas.Partida;
import is.buscaminas.model.Contador;
import is.buscaminas.model.Tablero;
import is.buscaminas.view.VistaCasilla;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ventanaPartidaPrincipalController {
    //Atributos FXML
    @FXML
    private GridPane tableroCasillas;

    //Constructora
    @FXML
    public void initialize ()
    //Pre:
    //Post: El tablero se ha generado y configurado, y se han añadido las casillas
    {
        //Se genera y configura el tablero (incluidos el listener del click inicial)
        inicializarTablero();

        //Se añaden las casillas
        crearCasillasTablero();
    }

    private void inicializarTablero ()
            //Pre:
            //Post: Se ha generado y configurado el trablero
    {
        tableroCasillas.setVgap(0);
        tableroCasillas.setHgap(0);

        //Se crea el evento del primer click
        EventHandler<MouseEvent> primerClick = new EventHandler<>() {
            @Override
            public void handle (MouseEvent event)
            //Pre:
            //Post: Se ha generado y configurado el trablero
            {
                //Se mira si es un click izquierdo
                if (event.isPrimaryButtonDown()) {
                    //Obtenemos la fila y columna de la casilla que se ha clickado
                    int fila = GridPane.getRowIndex((Node)event.getTarget());
                    int columna = GridPane.getColumnIndex((Node)event.getTarget());

                    //Obtenemos la matriz de Casillas
                    VistaCasilla[][] matrizCasillas = getMatrizCasillas();

                    //Llamamos al tablero
                    Tablero.getTablero().generarCasillasTablero(fila,columna,matrizCasillas);

                    //Se inicia el contador
                    Contador.getContador().inicio();

                    //Se elimina el evento
                    tableroCasillas.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
                else { event.consume(); }
            }
        };

        //Se le añade al GridPane el evento
        tableroCasillas.addEventFilter(MouseEvent.MOUSE_PRESSED, primerClick);
    }

    private void crearCasillasTablero ()
            //Pre:
            //Post: Se han añadido las caasillas al tablero
    {
        int numColumnas, numFilas;

        int dificultad = Partida.getPartida().getDificultad();
        switch (dificultad) {

            case 2:
                numFilas = 10;
                numColumnas = 15;
                break;

            case 3:
                numFilas = 12;
                numColumnas = 25;
                break;

            default:
                numFilas = 7;
                numColumnas = 10;
                break;
        }

        for (int colum = 0; numColumnas > colum; colum++) {
            for (int row = 0; numFilas > row; row++) { tableroCasillas.add(generarCasilla(), colum, row); }
        }
    }

    private VistaCasilla generarCasilla ()
            //Pre:
            //Post: Se ha generado la casilla
    {
        VistaCasilla nuevaCasilla = new VistaCasilla();
        GridPane.setFillHeight(nuevaCasilla, true);

        //Evento click izquierdo
        nuevaCasilla.setOnMousePressed(this::gestionarEventoCasilla);

        return nuevaCasilla;
    }

    private void gestionarEventoCasilla(MouseEvent event){
        VistaCasilla casilla = (VistaCasilla) event.getTarget();
        if (event.isPrimaryButtonDown()) {
            int filaCasilla = GridPane.getRowIndex(casilla);
            int columnaCasilla = GridPane.getColumnIndex(casilla);
            Tablero.getTablero().despejarCasilla(filaCasilla, columnaCasilla);
        }
    }

    private VistaCasilla[][] getMatrizCasillas ()
            //Pre:
            //Post: Se ha creado una matriz de casillas
    {
        VistaCasilla[][] matrizCasillas = new VistaCasilla[tableroCasillas.getRowCount()][tableroCasillas.getColumnCount()];
        ObservableList<Node> casillas = tableroCasillas.getChildren();

        for (Node casilla : casillas) {
            matrizCasillas[GridPane.getRowIndex(casilla)][GridPane.getColumnIndex(casilla)] = (VistaCasilla) casilla;
        }

        return matrizCasillas;
    }
}
