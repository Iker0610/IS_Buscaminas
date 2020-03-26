package is.buscaminas.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.File;

public class VistaCifraContador extends Pane {
    /*
     * Esta clase es un elemento de la vista
     * Extiende del Pane pues es el elemento más simple y la única función es mostrar una imagen.
     *
     * Su función es mostrar una cifra
     */

    //Atributos
    private int cifraAct; //La cifra que está mostrando actualmente
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
    {
        //Pre: Un entero indicando la nueva cifra
        //Post: Si la cifra es diferente se muestra el nuevo valor

        //Se actualizará en caso de que la nueva cifra sea diferente a la actual
        if (cifraAct != pCifra) {

            //Se actualiza el valor de la cifra
            cifraAct = pCifra;

            //Se carga la imagen adecuada
            Image imagenCasilla = new Image(new File("src/main/resources/is/buscaminas/ui/assets/timer/time" + pCifra + ".gif").toURI().toString());
            BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
            setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
        }
    }
}
