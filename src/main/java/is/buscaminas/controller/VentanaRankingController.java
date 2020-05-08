package is.buscaminas.controller;

import is.buscaminas.Partida;
import is.buscaminas.model.Contador;
import is.buscaminas.model.estructurasDatos.Node;
import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;
import is.buscaminas.model.ranking.JugadorRanking;
import is.buscaminas.model.ranking.Ranking;
import is.buscaminas.view.VistaRanking;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentanaRankingController {
    // Atributos
    VistaRanking[] rankingPorNivel;

    @FXML
    private Button aceptar;
    @FXML
    private StackPane zonaRanking;

    public void initialize ()
    {
        rankingPorNivel = new VistaRanking[3];
        for (int i = 0; i<3; i++) rankingPorNivel[i] = new VistaRanking();

        mostrarRanking(Partida.getPartida().getDificultad());
    }

    @FXML
    public void cambiarVistaRanking (ActionEvent event)
    {
        int dificultad = Integer.parseInt(((Button) event.getSource()).getText().split(" ")[1]);
        mostrarRanking(dificultad);
    }

    private void mostrarRanking(int pDificultad){
        zonaRanking.getChildren().clear();
        zonaRanking.getChildren().add(rankingPorNivel[pDificultad-1]);
        Ranking.getRanking().obtenerRanking(pDificultad);
    }

    @FXML
    public void pulsarAceptar ()
    {
        Stage stage = (Stage) aceptar.getScene().getWindow();
        Contador.getContador().continuar();
        stage.close();
    }
}
