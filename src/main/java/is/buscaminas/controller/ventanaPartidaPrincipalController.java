package is.buscaminas.controller;

import is.buscaminas.Partida;
import is.buscaminas.model.Tablero;
import is.buscaminas.view.VistaCasilla;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ventanaPartidaPrincipalController {
    //Atributos FXML
    @FXML
    private GridPane tableroCasillas;

    //Constructora
    @FXML
    public void initialize ()
    {
        //Se genera y configura el tablero (incluidos el listener del click inicial)
        inicializarTablero();

        //Se añaden las casillas
        crearCasillasTablero();
    }

    private void inicializarTablero ()
    {
        tableroCasillas.setVgap(0);
        tableroCasillas.setHgap(0);

        //Se crea el evento del primer click
        EventHandler<MouseEvent> primerClick = new EventHandler<>() {
            @Override
            public void handle (MouseEvent event)
            {
                //Se mira si es un click izquierdo
                if (event.isPrimaryButtonDown()) {
                    /*TODO:
                        -Obtener el gridpane como matriz
                        -Llamar a tablero para que genere las casillas
                        -Iniciar el timer
                    */

                    //Se elimina el evento
                    tableroCasillas.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                }
                else { event.consume(); }
            }
        };

        //Se le añade al GridPane el evento
        tableroCasillas.addEventFilter(MouseEvent.MOUSE_CLICKED, primerClick);
    }

    private void crearCasillasTablero ()
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
    {
        VistaCasilla nuevaCasilla = new VistaCasilla();
        GridPane.setFillHeight(nuevaCasilla, true);

        //Evento click izquierdo
        nuevaCasilla.setOnMouseClicked((onActionEvent) -> {
            if (onActionEvent.isPrimaryButtonDown()) {
                int filaCasilla = GridPane.getRowIndex(nuevaCasilla);
                int columnaCasilla = GridPane.getColumnIndex(nuevaCasilla);
                Tablero.getTablero().despejarCasilla(filaCasilla, columnaCasilla);
            }
        });

        return nuevaCasilla;
    }
}
