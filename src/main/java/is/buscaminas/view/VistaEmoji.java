package is.buscaminas.view;

import is.buscaminas.Partida;
import is.buscaminas.model.Contador;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class VistaEmoji extends Button implements PropertyChangeListener {
    //Descripción:
    // - Elemento UI dedicado a las casillas

    //Atributos
    private static final int size = 35;

    public VistaEmoji ()
    {
        super();
        //Se configura el tamaño
        setMinHeight(size);
        setMinWidth(size);
        setMaxHeight(size);
        setMaxWidth(size);

        Contador.getContador().addObserver(this::actualizarEmojiTiempo);
        Partida.getPartida().addObserver(this::actualizarEmojiFinPartida);

        cambiarEmoji("estandar");
    }


    //Metodo del patrón observer
    @Override
    public void propertyChange (PropertyChangeEvent pNuevoEstado) {}

    private void actualizarEmojiFinPartida (PropertyChangeEvent propertyChangeEvent)
            //Pre:
            //Post:Se ha actualizado el emoji al correspondiente según si el jugador ha ganado la partida o no
    {
        if ((Boolean)propertyChangeEvent.getNewValue()){
            cambiarEmoji("victoria");
        }
        else {
            cambiarEmoji("derrota");
        }
    }

    private void actualizarEmojiTiempo (PropertyChangeEvent propertyChangeEvent)
            //Pre:
            //Post: Se ha a actualizado el emoji
    {
        if ((Integer) propertyChangeEvent.getNewValue() == 999) {
            cambiarEmoji("tiempo");
        }
    }

    //Metodo para cambiar el aspecto de la casilla:
    private void cambiarEmoji (String pString)
    //Pre: Un string
    //Post: Se ha cambiado el emoji correctamente
    {
        //Se carga y se aplica
        Image imagenCasilla = new Image(new File(
                "src/main/resources/is/buscaminas/ui/assets/emoji/" + pString + ".gif").toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(size, size, false, false, false, false);
        setBackground(new Background(new BackgroundImage(imagenCasilla, null, null, null, backgroundSize)));
    }
}
