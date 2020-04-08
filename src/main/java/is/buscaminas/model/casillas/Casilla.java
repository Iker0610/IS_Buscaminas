package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.IEstadoCasilla;
import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;

public abstract class Casilla {

    //Atributos
    private IEstadoCasilla estadoAct;
    private PropertyChangeSupport lObservers;

    // Gets para subtipos de casillas
    protected Casilla (VistaCasilla pVistaCasilla)
    {
        estadoAct = new Oculto();
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pVistaCasilla);
    }

    protected Casilla (Casilla pCasilla)
    {
        this.estadoAct = pCasilla.estadoAct;
        this.lObservers = pCasilla.lObservers;
    }


    //Metodos
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