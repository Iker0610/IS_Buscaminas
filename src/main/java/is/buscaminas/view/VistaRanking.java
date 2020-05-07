package is.buscaminas.view;

import is.buscaminas.model.ranking.JugadorRanking;
import is.buscaminas.model.ranking.Ranking;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class VistaRanking extends GridPane implements PropertyChangeListener {

    public VistaRanking ()
    {
        super();
        //Se añade esta instancia como observer del contador
        Ranking.getRanking().addObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pEvento) {
        cambiarRanking((Integer) pEvento.getNewValue());
    }

    public void cambiarRanking(int nivel){
        GridPane gridPane = new GridPane();
        JugadorRanking[] l = Ranking.getRanking().obtenerRanking(nivel);
        int i = 0;
        int pos = 1;
        while (l[i] != null) {
            // Por cada usuario, insertamos la imagen de su posición, su nombre y puntuación.
            Image imagenPosicion = new Image(new File("src/main/resources/is/buscaminas/ui/assets/ranking/" + pos + ".png").toURI().toString());
            gridPane.add(new ImageView(imagenPosicion), 0, i);
            // gridPane.add(new Label(l[i].getNombre()), 1, i);
            // gridPane.add(new Label(Integer.toString(l[i].getPuntuacion())), 2, i);
            i++;
            pos++;
        }
        //ACTUALIZAR VISTA ??
    }
}
