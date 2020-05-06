package is.buscaminas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentanaRankingController{

    @FXML
    private Button aceptar;

    @FXML
    public void pulsarAceptar() {
        Stage stage = (Stage) aceptar.getScene().getWindow();
        stage.close();
    }
}
