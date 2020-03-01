package is.buscaminas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Partida extends Application {
    /**
     * Esta clase se encarga de administrar la partida:
     * - Guardará el nombre del Usuario
     * - Guardará el nivel de dificultad
     * - Se encarga del tránsito entre pantallas
     */

    //Atibutos
    private String nombreJugador;
    private int dificultad;
    private Stage stageAct;

    //MAINS
    public static void main (String[] args)
    {
        //NO TOCAR!
        launch(args);
    }

    @Override
    public void start (Stage pStage) throws IOException
    {
        //Se guarda el Stage
        stageAct = pStage;

        //Se configura el Stage
        stageAct.setTitle("Buscaminas");
        stageAct.centerOnScreen();

        //Se carga la pantalla
        Parent root = FXMLLoader.load(Partida.class.getResource("ui/fxml/ventanaPartidaPrincipal.fxml"));
        stageAct.setScene(new Scene(root));

        //Se muestra
        stageAct.show();
    }
}
