package is.buscaminas.controller;


import is.buscaminas.model.Ayuda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class menuAyudaController {

    //Metodos de eventos
    @FXML
    private void nextAyuda(ActionEvent pEvento) { Ayuda.getAyuda().siguiente(); }

    @FXML
    private void prevAyuda(ActionEvent pEvento) { Ayuda.getAyuda().anterior(); }
}
