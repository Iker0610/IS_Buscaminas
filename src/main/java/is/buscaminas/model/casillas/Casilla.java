package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.IEstadoBoton;
import is.buscaminas.view.BotonCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeSupport;

public abstract class Casilla {
    //Atributos
    private IEstadoBoton estadoAct;
    private PropertyChangeSupport lObservers;

    protected Casilla (BotonCasilla pBotonCasilla)
    {
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pBotonCasilla);
    }

    public Pair<Boolean, Boolean> despejar ()
    {
        return estadoAct.despejar(this);
    }

    public void cambiarEstado (IEstadoBoton pEstado)
    {
        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null,
                this.getClass().getSimpleName() + pEstado.getClass().getSimpleName());
    }

}
