package is.buscaminas.controller;

import is.buscaminas.Partida;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PanelMenuBuscaminasController {

    @FXML
    private void reiniciar (MouseEvent pEvento)
    {
        //Se mira si es un click izquierdo y si no hay partida activa
        if (pEvento.isPrimaryButtonDown() && !Partida.getPartida().hayPartidaActiva()) {
            Partida.getPartida().reiniciarPartida();
        }

    }
}
