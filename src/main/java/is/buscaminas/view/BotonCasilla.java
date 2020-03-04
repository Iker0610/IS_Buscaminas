package is.buscaminas.view;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class BotonCasilla extends Button implements PropertyChangeListener {
    //Descripción:
    // - Elemento UI dedicado a las casillas

    //Atributos
    private final int size = 30;
    private ImageView imagenCasilla;

    //Constructora
    public BotonCasilla ()
    {
        //Se configura el boton
        super();
        setText(null);
        setPrefHeight(30);
        setPrefWidth(30);
        setStyle("-fx-background-color: transparent;");

        //Se configura la apariencia del boton
        imagenCasilla = new ImageView();
        imagenCasilla.setPreserveRatio(false);
        imagenCasilla.fitWidthProperty().bind(this.widthProperty());
        imagenCasilla.fitHeightProperty().bind(this.heightProperty());

        this.getChildren().add(imagenCasilla);
        super.setGraphic(imagenCasilla);

        //Se carga y se aplica
        Image aspecto = new Image(new File("is/buscaminas/ui/assets/casillas/.keep.gif").toURI().toString());
        imagenCasilla.setImage(aspecto);

        //Se carga y se aplica
        cambiarApariencia("oculto");
    }

    //Metodos
    //Aquí van los métodos para actualizar el botón, los eventos NO VAN AQUÍ, se cargan en el controller

    //Metodo del patrón observer
    @Override
    public void propertyChange (PropertyChangeEvent propertyChangeEvent)
    {
        //TODO: Finalizar el patrón Observer
    }

    //Metodo para cambiar el aspecto de la casilla:
    private void cambiarApariencia (String pString)
    {
        Image aspecto = new Image(new File("is/buscaminas/ui/assets/casillas/" + pString + ".gif").toURI().toString());
        imagenCasilla.setImage(aspecto);
    }
}
