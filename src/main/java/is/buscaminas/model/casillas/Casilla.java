package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.IEstadoBoton;
import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeSupport;

public abstract class Casilla {
    //Atributos
    private IEstadoBoton estadoAct;
    private PropertyChangeSupport lObservers;

    protected Casilla (VistaCasilla pVistaCasilla)
    {
        estadoAct = new Oculto();
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pVistaCasilla);
    }

    public Pair<Boolean, Boolean> despejar ()
            //Pre:
            //Post: Un par de booleans indicando qué acción se realizará
    {

        return estadoAct.despejar(this);
    }

    public void cambiarEstado (IEstadoBoton pEstado)
            //Pre: Un estado
            //Post: Se ha cambiado el estado
    {
        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null,
                pEstado.getClass().getSimpleName());
    }

    protected void cambiarEstado (IEstadoBoton pEstado, int pNum)
            //Pre: Un estado y un número entero indicando las casillas adyacentes
            //Post:Se ha cambiado el estado
    {
        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null,
                pEstado.getClass().getSimpleName() + pNum);
    }

}