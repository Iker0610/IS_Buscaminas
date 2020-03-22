package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.EstadoBoton;
import is.buscaminas.view.BotonCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeSupport;

public abstract class Casilla {
    //Atributos
    private EstadoBoton estadoAct;
    private PropertyChangeSupport lObservers;

    protected Casilla (BotonCasilla pBotonCasilla)
    {
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pBotonCasilla);
    }

    public abstract Pair<Boolean, Boolean> despejar ();

    protected void cambiarEstado (EstadoBoton pEstado){
        estadoAct = pEstado;
    }

}
