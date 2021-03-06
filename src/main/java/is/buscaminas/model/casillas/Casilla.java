package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Despejado;
import is.buscaminas.model.casillas.estados.IEstadoCasilla;
import is.buscaminas.model.casillas.estados.Marcado;
import is.buscaminas.model.casillas.estados.Oculto;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Casilla {

    //Atributos
    private IEstadoCasilla estadoAct;
    private PropertyChangeSupport lObservers;

    // Gets para subtipos de casillas
    protected Casilla (PropertyChangeListener pVistaCasilla)
    {
        estadoAct = new Oculto();
        lObservers = new PropertyChangeSupport(this);
        lObservers.addPropertyChangeListener(pVistaCasilla);
    }

    protected Casilla (Casilla pCasilla)
    {
        // Copia los valores de la casilla paramétro y se genera con ellos
        this.estadoAct = pCasilla.estadoAct;
        this.lObservers = new PropertyChangeSupport(this);
        for (PropertyChangeListener observer : pCasilla.lObservers.getPropertyChangeListeners()) { lObservers.addPropertyChangeListener(observer); }
    }


    //Metodos
    public int despejar ()
    {
        //Pre:
        //Post: Un int indicando qué acción debe realizar la tabla
        return estadoAct.despejar(this);
    }

    public Pair<Boolean, Boolean> marcar ()
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

    public boolean estaMarcada ()
    {
        //Pre:
        //Post: Se devuelve true si esta casilla está marcada
        return (estadoAct instanceof Marcado);
    }

    public boolean estaDespejada ()
    {
        //Pre:
        //Post: Se devuelve true si esta casilla está despejada
        return (estadoAct instanceof Despejado);
    }

    protected void cambiarEstado (IEstadoCasilla pEstado, int pNum)
    {
        //Pre: Un estado y un número entero indicando las minas adyacentes
        //Post:Se ha cambiado el estado y avisado a los observers con el nuevo valor

        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null, pEstado.getClass().getSimpleName() + pNum);
    }

    public void verMinas ()
    {
        estadoAct.verMinas(this);
    }
}
