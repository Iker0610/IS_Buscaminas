package is.buscaminas;

import is.buscaminas.model.Contador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
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
    private int dificultad = 1;
    private Stage ventanaAct;
    private boolean partidaActiva;
    private PropertyChangeSupport lObservers;

    //MAINS
    public static void main (String[] args)
    {
        //NO TOCAR!
        launch(args);
    }

    public Partida ()
    {
        partidaActiva = false;
        lObservers = new PropertyChangeSupport(this);
    }

    @Override
    public void start (Stage pStage) throws IOException
            //Pre: Un elemento Stage
            //Post: Se inicia la partida
    {
        //Se guarda la instancia
        mPartida = this;

        //Se guarda el Stage
        ventanaAct = pStage;

        //Se configura el Stage
        ventanaAct.setTitle("Buscaminas");
        ventanaAct.getIcons().add(new Image(new File("src/main/resources/is/buscaminas/ui/assets/logo/logoBuscaminas.png").toURI().toString()));
        ventanaAct.setResizable(false);
        ventanaAct.centerOnScreen();

        iniciarPartida();
    }

    //Singleton
    public static Partida getPartida ()
    {
        if (mPartida == null) mPartida = new Partida();
        return mPartida;
    }

    //Metodos relacionados a la partida
    private void iniciarPartida () throws IOException
    //Pre:
    //Post: Se inicia la partida
    {
        //Se carga la pantalla
        Parent root = FXMLLoader.load(Partida.class.getResource("ui/fxml/ventanaPartidaPrincipal.fxml"));
        ventanaAct.setScene(new Scene(root));

        //Se activa el boolean
        partidaActiva = true;

        //Se muestra una vez cargado
        ventanaAct.show();
    }

    public void finalizarPartida (boolean pVictoria)
            //Pre: Un boolean indicando si el jugador a ganado o no la partida
            //Post: Se ha finalizado la partida
    {
        Contador.getContador().parar();
        partidaActiva = false;
        lObservers.firePropertyChange("estadoPartida", null, pVictoria);
    }

    //Metodos publicos de la clase:
    public int getDificultad ()
    //Pre:
    //Post: Devuelve el nivel de dificultad
    {
        return dificultad;
    }

    public boolean hayPartidaActiva ()
            //Pre:
            //Post: Devuelve un boolean indicando si hay una partida activa o no
    {
        return partidaActiva;
    }

    public void addObserver (PropertyChangeListener pObserver)
            //Pre: Un observer
            //Post: Se ha añadido el observer a la lista de observer
    {
        lObservers.addPropertyChangeListener(pObserver);
    }
}
