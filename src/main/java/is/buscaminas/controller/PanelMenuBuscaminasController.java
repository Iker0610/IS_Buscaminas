package is.buscaminas.controller;

import is.buscaminas.Partida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class PanelMenuBuscaminasController {

    @FXML
    private void reiniciar (ActionEvent pEvento)
    {
            Partida.getPartida().reiniciarPartida();
    }

    @FXML
    private void mostrarAyuda (ActionEvent pEvento) {
        Partida.getPartida().mostrarAyuda();
    }
}
