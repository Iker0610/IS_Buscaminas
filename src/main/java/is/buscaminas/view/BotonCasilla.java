package is.buscaminas.view;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

//TODO: Añadir el patrón Observer
public class BotonCasilla extends Button {
    //Descripción:
    // - Elemento UI dedicado a las casillas

    //Atributos
    private final int size = 20;
    private ImageView imagenCasilla;

    //Constructora
    public BotonCasilla ()
    {
        //Se configura el boton
        super();
        setText(null);
        setPrefHeight(Button.USE_COMPUTED_SIZE);
        setPrefWidth(Button.USE_COMPUTED_SIZE);
        setStyle("-fx-background-color: transparent;");

        //Se configura la apariencia del boton
        imagenCasilla = new ImageView();
        imagenCasilla.fitWidthProperty().bind(((Button) imagenCasilla.getParent()).widthProperty());
        imagenCasilla.fitHeightProperty().bind(((Button) imagenCasilla.getParent()).heightProperty());

        this.getChildren().add(imagenCasilla);
        super.setGraphic(imagenCasilla);

        //Se carga y se aplica
        Image skin = new Image(new File("src/resources/hidden.jpg").toURI().toString());
        imagenCasilla.setImage(skin);
    }

    //Metodos
    //Aquí van los métodos para actualizar el botón, los eventos NO VAN AQUÍ, se cargan en el controller
}
