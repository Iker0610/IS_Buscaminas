package is.buscaminas.controller;

import is.buscaminas.model.estructurasDatos.Node;
import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;
import is.buscaminas.model.ranking.JugadorRanking;
import is.buscaminas.model.ranking.Ranking;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class VentanaRankingController{

    @FXML
    private Button aceptar;

    @FXML
    public void pulsarAceptar() {
        Stage stage = (Stage) aceptar.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void mostrarRanking1(){
        //TODO
    }

    @FXML
    public void mostrarRanking2(){
        //TODO
    }

    @FXML
    public void mostrarRanking3(){
        //TODO
    }
}
