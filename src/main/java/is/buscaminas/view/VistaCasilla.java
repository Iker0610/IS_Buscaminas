package is.buscaminas.view;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class VistaCasilla extends Button implements PropertyChangeListener {
    //Descripción:
    // - Elemento UI dedicado a las casillas

    //Atributos
    private static final int size = 30;

    public VistaCasilla ()
    {
        super();
        //Se configura el tamaño
        setMinHeight(size);
        setMinWidth(size);
        setMaxHeight(size);
        setMaxWidth(size);

        cambiarApariencia("Oculto");
    }

    //Metodo del patrón observer
    @Override
    public void propertyChange (PropertyChangeEvent pNuevoEstado)
    {
        String nuevaEstado = (String) pNuevoEstado.getNewValue();
        cambiarApariencia(nuevaEstado);
    }

    //Metodo para cambiar el aspecto de la casilla:
    private void cambiarApariencia (String pString)
    {
        //Se carga y se aplica
        Image imagenCasilla = new Image(new File("is/buscaminas/ui/assets/casilla/" + pString + ".gif").toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(size, size, false, false, false, false);
        setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
    }
}
