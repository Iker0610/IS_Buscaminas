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
     * - Se encarga del tránsito entre ventanas
     */

    //Atibutos
    private static Partida mPartida;
    private String nombreJugador;
    private int dificultad = 1;
    private Stage ventanaAct;

    //MAINS
    public static void main (String[] args)
    {
        //NO TOCAR!
        launch(args);
    }

    @Override
    public void start (Stage pStage) throws IOException
    {
        //Se guarda la instancia
        mPartida = this;

        //Se guarda el Stage
        ventanaAct = pStage;

        //Se configura el Stage
        ventanaAct.setTitle("Buscaminas");
        ventanaAct.centerOnScreen();

        //Se carga la pantalla
        Parent root = FXMLLoader.load(Partida.class.getResource("ui/fxml/ventanaPartidaPrincipal.fxml"));
        ventanaAct.setScene(new Scene(root));

        //Se muestra una vez cargado
        ventanaAct.show();
    }

    //Singleton
    public Partida getPartida(){
        if (mPartida == null) mPartida = new Partida();
        return mPartida;
    }

    //Metodos publicos de la clase:
    public int getDificultad(){
        return dificultad;
    }

    //Metodos relacionados a la partida
    private void iniciarPartida(){

    }

    public void finalizarPartida (boolean pVictoria){
        //TODO
        System.exit(0);
    }
}
