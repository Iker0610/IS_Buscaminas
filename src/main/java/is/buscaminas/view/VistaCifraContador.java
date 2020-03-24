package is.buscaminas.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import java.io.File;

public class VistaCifraContador  extends Button {
    //Atributos
    private static final int height = 35;
    private static final int width = 20;

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

    public void cambiarCifra (int pCifra)
    {
        //Se carga y se aplica
        Image imagenCasilla = new Image(new File("src/main/resources/is/buscaminas/ui/assets/timer/time" + pCifra + ".gif").toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
    }
}
