package is.buscaminas.view;

import is.buscaminas.model.Ayuda;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class VistaAyuda extends Pane implements PropertyChangeListener {

    //Atributos
    private static double anch = imgAnch(1); //tamaño de la primera imagen (todas las imagenes tendrán el mismo tamaño)
    private static double alt = imgAlt(1);



    // La Clase que muestra las páginas de ayuda
    public VistaAyuda(){
        super();
        //tamaño del pane
        setMinSize(anch,alt);

        //añadimos esta vista como observer de Ayuda
        Ayuda.getAyuda().addObserver(this::cambiarPanel);

        //Ponemos la primera imagen
        cambiarImagen(1);
    }



    //para obtener tamaños de imagenes:

    private static double imgAnch(int i) {
        Image imagen = new Image(new File("src/main/resources/is/buscaminas/ui/assets/ayuda/" + i + ".png").toURI().toString());
        return imagen.getWidth();
    }


    private static double imgAlt(int i) {
        Image imagen = new Image(new File("src/main/resources/is/buscaminas/ui/assets/ayuda/" + i + ".png").toURI().toString());
        return imagen.getHeight();
    }

    //más metodos

    private void cambiarPanel (PropertyChangeEvent propertyChangeEvent) {
        //Pre: Un evento con un número
        //Post: Se ha cambia el menu de ayuda actual dependiendo del int recibido

        int pagAct = (int) propertyChangeEvent.getNewValue();
        cambiarImagen(pagAct);

    }

    private void cambiarImagen(int pPag){
        //Pre: un numero
        //Post: Se carga la pagina correspondiente a ese numero

        //cargamos la imagen
        Image imagenCasilla = new Image(new File("src/main/resources/is/buscaminas/ui/assets/ayuda/" + pPag + ".png").toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(anch, alt, false, false, false, false);
        setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize )));
    }

    //método necesario para el observer
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) { }

}
