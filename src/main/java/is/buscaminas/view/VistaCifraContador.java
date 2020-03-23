package is.buscaminas.view;

import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import java.io.File;

public class VistaCifraContador  extends Control {
    //Atributos
    private static final int height = 30;
    private static final int width = 30;

    public VistaCifraContador ()
    {
        super();
        //Se configura el tamaño
        setMinHeight(height);
        setMinWidth(width);
        setMaxHeight(height);
        setMaxWidth(width);

        cambiarCifra(0);
    }

    private void cambiarCifra (int pCifra)
    {
        //Se carga y se aplica
        Image imagenCasilla = new Image(new File("is/buscaminas/ui/assets/timer/time" + pCifra + ".gif").toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
    }
}
