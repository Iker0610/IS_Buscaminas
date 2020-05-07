package is.buscaminas.view;

import is.buscaminas.model.ranking.JugadorRanking;
import is.buscaminas.model.ranking.Ranking;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URISyntaxException;

public class VistaRanking extends GridPane implements PropertyChangeListener {

    public VistaRanking ()
    {
        super();
        //Se añade esta instancia como observer del contador
        Ranking.getRanking().addObserver(this);

        this.setPadding(new Insets(50));
        this.setHgap(25);
        this.setVgap(20);

        for (int i = 1; i <= 10; i++) {
            int size = 30;
            Pane numPosicion = new Pane();
            numPosicion.setMinHeight(size);
            numPosicion.setMinWidth(size);
            numPosicion.setMaxHeight(size);
            numPosicion.setMaxWidth(size);

            Image imagenPosicion = new Image(new File("src/main/resources/is/buscaminas/ui/assets/ranking/"+ i + ".png").toURI().toString());
            BackgroundSize backgroundSize = new BackgroundSize(size, size, false, false, false, false);
            numPosicion.setBackground(new Background(new BackgroundImage(imagenPosicion, null, null, null, backgroundSize)));

            this.add(numPosicion, 0, (i - 1));
        }

    }

    @Override
    public void propertyChange (PropertyChangeEvent pEvento)
    {
        cambiarRanking((JugadorRanking[]) pEvento.getNewValue());
    }

    private void cambiarRanking (JugadorRanking[] pLJugadores)
    {
        int pos = 0;
        for (JugadorRanking jugador : pLJugadores) {
            String nombre = "------";
            String puntuacion = "------";
            if (jugador != null) {
                nombre = jugador.getNombre();
                puntuacion = Integer.toString(jugador.getPuntuacion());
            }
            // Por cada usuario, insertamos la imagen de su posición, su nombre y puntuación.
            this.add(new Label(nombre), 1, pos);
            this.add(new Label(puntuacion), 2, pos++);
        }
    }
}
