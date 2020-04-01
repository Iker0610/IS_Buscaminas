package is.buscaminas.controller;

import is.buscaminas.Partida;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.io.IOException;


public class ventanaAccesoController {

    private ToggleGroup dificultadGroup;
    //Atributos FXML
    @FXML
    private TextField nombreTextField;
    @FXML
    private RadioButton dificultad1;
    @FXML
    private RadioButton dificultad2;
    @FXML
    private RadioButton dificultad3;


    //Constructora
    @FXML
    public void initialize(){
        dificultadGroup = new ToggleGroup();
        dificultad1.setToggleGroup(dificultadGroup);
        dificultad2.setToggleGroup(dificultadGroup);
        dificultad3.setToggleGroup(dificultadGroup);
    }

    @FXML
    public void pulsarAceptar(Event pEvent) throws IOException {
        if(!nombreTextField.getText().equals("")){
            //Guardamos el nombre de jugador
            Partida.getPartida().setNombre(nombreTextField.getText());
            //Ponemos la dificultad seleccionada
            String dificultad = ((Node)dificultadGroup.getSelectedToggle()).getId();
            int numDificultad;
            try
            {
                numDificultad = Integer.parseInt(dificultad);
            }
            catch(Exception e)
            {
                numDificultad = 1;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ERROR (introducir dificultad)", ButtonType.YES, ButtonType.NO);
            }
            Partida.getPartida().setDificultad(numDificultad);
            Partida.getPartida().iniciarPartida();

        }
    }
}
