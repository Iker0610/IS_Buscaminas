package is.buscaminas.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class ReproductorMusica {
    private static MediaPlayer reproductorMusica;

    //Constructora
    public ReproductorMusica ()
    {
        Media h = new Media(new File("src/resources/SuperMarioBrosMedley.mp3").toURI().toString());
        reproductorMusica = new MediaPlayer(h);
        reproductorMusica.seek(Duration.ZERO);
        reproductorMusica.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void reproducir ()
            //Pre:
            //Post: Se ha reproducido la música
    {
        reproductorMusica.play();
    }

    public void parar ()
            //Pre:
            //Post: Se ha detenido la música
    {
        reproductorMusica.pause();
    }

    //TODO: finalizar la implementacion y añadir los SFX
}
