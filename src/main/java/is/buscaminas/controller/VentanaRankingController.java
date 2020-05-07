package is.buscaminas.controller;

import is.buscaminas.model.estructurasDatos.Node;
import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;
import is.buscaminas.model.ranking.JugadorRanking;
import is.buscaminas.model.ranking.Ranking;
import is.buscaminas.view.VistaRanking;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VentanaRankingController{

    private VistaRanking tabla;
    @FXML
    private Button aceptar;
    @FXML
    private GridPane matrizRanking;

    public void initialize ()
    {
        Ranking.getRanking().actualizarRanking(1);
    }

    @FXML
    public void pulsarAceptar() {
        Stage stage = (Stage) aceptar.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void mostrarRanking1(){
        Ranking.getRanking().actualizarRanking(1);

    }

    @FXML
    public void mostrarRanking2(){
        Ranking.getRanking().actualizarRanking(2);
    }

    @FXML
    public void mostrarRanking3(){
        Ranking.getRanking().actualizarRanking(3);
    }
}
