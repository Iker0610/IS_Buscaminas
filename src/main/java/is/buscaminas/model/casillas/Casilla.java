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

    // Gets para subtipos de casillas
    protected Casilla (VistaCasilla pVistaCasilla)
    {
        estadoAct = new Oculto();
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pVistaCasilla);
    }
    protected IEstadoCasilla getEstado ()
    {
        // Pre: -
        // Post: devuelve el estado actual de la casilla
        return this.estadoAct;
    }

    protected PropertyChangeSupport getObservers ()
    {
        // Pre: -
        // Post: devuelve la lista de observers de la casilla
        return this.lObservers;
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

    //Métodos incluidos en esta clase abstracta únicamente para poder trabajar con ellos en CasillaTemp teniendo un objeto Casilla.
    public Casilla convertirEnMina(VistaCasilla vistaCasilla) {
        return null;
    }
    public Casilla convertirEnNum(int i, VistaCasilla vistaCasilla) {
        return null;
    }
}