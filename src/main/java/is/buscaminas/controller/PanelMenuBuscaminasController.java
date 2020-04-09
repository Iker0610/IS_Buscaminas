package is.buscaminas.controller;

import is.buscaminas.Partida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;



public class PanelMenuBuscaminasController {

    @FXML
    private void reiniciar (ActionEvent pEvento)
    {
            Partida.getPartida().reiniciarPartida();
    }
}
