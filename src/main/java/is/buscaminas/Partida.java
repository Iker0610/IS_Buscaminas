package is.buscaminas;

import is.buscaminas.model.Contador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public class Partida extends Application {
    /**
     * Esta clase se encarga de administrar la partida:
     * - Guardará el nombre del Usuario (futuros sprint)
     * - Guardará el nivel de dificultad
     * - Se encarga del tránsito entre ventanas
     * - Guardará si hay partidas activas o no
     */

    //Atibutos
    private static Partida mPartida;
    private Stage ventanaAct;
    private PropertyChangeSupport lObservers;
    private int dificultad = 1;
    private boolean partidaActiva;

    //MAINS y CONSTRUCTORAS
    public static void main (String[] args)
    {
        //NO TOCAR!
        launch(args);
    }

    @Override
    public void start (Stage pStage) throws IOException
    {
        //Pre:
        //Post: Se inicia la aplicación

        //Se guarda la instancia
        mPartida = this;

        //Se guarda el Stage
        ventanaAct = pStage;

        //Se inicializa la partida
        partidaActiva = false;
        lObservers = new PropertyChangeSupport(this);

        //Se configura el Stage
        ventanaAct.setTitle("Buscaminas");
        ventanaAct.getIcons().add(new Image(new File("src/main/resources/is/buscaminas/ui/assets/logo/logoBuscaminas.png").toURI().toString()));
        ventanaAct.setResizable(false);
        ventanaAct.centerOnScreen();

        //Se inciia una partida
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
    {
        //Pre:
        //Post: Se inicia la partida

        //Se carga la pantalla y se introduce en el Stage
        Parent root = FXMLLoader.load(Partida.class.getResource("ui/fxml/ventanaPartidaPrincipal.fxml"));
        ventanaAct.setScene(new Scene(root));

        //Se activa el boolean que indica que existe una partida activa
        partidaActiva = true;

        //Se muestra el stage una vez cargado
        ventanaAct.show();
    }

    public void finalizarPartida (boolean pVictoria)
    {
        //Pre: Un boolean indicando si el jugador a ganado o no
        //Post: Se ha finalizado la partida

        //Se para el contador
        Contador.getContador().parar();

        //Se indica que no hay partidas activas
        partidaActiva = false;

        //Se avisa a los observers que ha finalizado la partida y cual ha sido el resultado
        lObservers.firePropertyChange("estadoPartida", null, pVictoria);
    }

    //Metodos publicos de la clase para administrar los atributos:
    public int getDificultad ()
    {
        //Pre:
        //Post: Devuelve el nivel de dificultad
        return dificultad;
    }

    public boolean hayPartidaActiva ()
    {
        //Pre:
        //Post: Devuelve un boolean indicando si hay una partida activa o no
        return partidaActiva;
    }

    public void addObserver (PropertyChangeListener pObserver)
    {
        //Pre: Un observer
        //Post: Se ha añadido el observer a la lista de observers
        lObservers.addPropertyChangeListener(pObserver);
    }
}
