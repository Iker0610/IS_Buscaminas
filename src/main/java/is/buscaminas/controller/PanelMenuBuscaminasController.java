package is.buscaminas.controller;

import is.buscaminas.Partida;
import is.buscaminas.view.VistaEmoji;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PanelMenuBuscaminasController {

    @FXML
    private void reiniciar (MouseEvent pEvento) throws IOException {
        //Se mira si es un click izquierdo y si no hay partida activa
        if (pEvento.isPrimaryButtonDown() && !Partida.getPartida().hayPartidaActiva()){
            Partida.getPartida().reiniciarPartida();
        }

    }
}
