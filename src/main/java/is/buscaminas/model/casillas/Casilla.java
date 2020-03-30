package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.IEstadoCasilla;
import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeSupport;

public abstract class Casilla {

    //Atributos
    private IEstadoCasilla estadoAct;
    private PropertyChangeSupport lObservers;

    protected Casilla (VistaCasilla pVistaCasilla)
    {
        estadoAct = new Oculto();
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pVistaCasilla);
    }

    public Pair<Boolean, Boolean> despejar ()
    {
        //Pre:
        //Post: Un par de booleans indicando qué acción debe realizar la tabla

        return estadoAct.despejar(this);
    }

    public Pair<Boolean, Boolean> marcar()
    {
        //Pre:
        //Post: Se actualiza el estado

        return estadoAct.marcar(this);
    }

    public void cambiarEstado (IEstadoCasilla pEstado)
    {
        //Pre: Un estado
        //Post: Se ha cambiado el estado y avisado a los observers con el nuevo valor

        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null, pEstado.getClass().getSimpleName());
    }

    protected void cambiarEstado (IEstadoCasilla pEstado, int pNum)
    {
        //Pre: Un estado y un número entero indicando las minas adyacentes
        //Post:Se ha cambiado el estado y avisado a los observers con el nuevo valor

        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null, pEstado.getClass().getSimpleName() + pNum);
    }

}