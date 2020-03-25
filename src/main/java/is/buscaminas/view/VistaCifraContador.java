package is.buscaminas.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.File;

public class VistaCifraContador extends Pane {
    //TODO: documentar

    //Atributos
    private int cifraAct;
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

        //Se configura la cifra
        cifraAct = -1;
        cambiarCifra(0);
    }

    public void cambiarCifra (int pCifra)
    //Pre: Un entero
    //Post: Se ha cambiado la cifra
    {
        //Se carga y se aplica si es diferente
        if (cifraAct != pCifra) {

            //Se actualiza el valor de la cifra
            cifraAct = pCifra;

            //Se carga la imagen adecuada
            Image imagenCasilla = new Image(new File(
                    "src/main/resources/is/buscaminas/ui/assets/timer/time" + pCifra + ".gif").toURI().toString());
            BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
            setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
        }
    }
}
