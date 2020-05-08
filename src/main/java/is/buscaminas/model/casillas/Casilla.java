package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.IEstadoCasilla;
import is.buscaminas.model.casillas.estados.Marcado;
import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.VistaCasilla;
import javafx.util.Pair;

import java.beans.PropertyChangeListener;
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

    protected Casilla (Casilla pCasilla)
    {
        // Copia los valores de la casilla paramétro y se genera con ellos
        this.estadoAct = pCasilla.estadoAct;
        this.lObservers = new PropertyChangeSupport(this);
        for (PropertyChangeListener observer:pCasilla.lObservers.getPropertyChangeListeners()) lObservers.addPropertyChangeListener(observer);
    }


    //Metodos

    public int despejar ()
    {
        //Pre:
        //Post: Un int indicando qué acción debe realizar la tabla

        /*
         *   IMPORTANTE: en caso de que el número sea un 4 (lo que significa que hay que despejar las adyacentes):
         *   Se va a devolver 4 + el número de minas adyacentes a esta casilla.
         */
        int opcion = estadoAct.despejar(this);
        if (this instanceof CasillaNum && (opcion == 4))        // En caso de que haya que ejecutarse la opción 4, se devuelve 4 + minas adyacentes
        {
            return (((CasillaNum) this).hallarCombinacion());   // Lo calcula un método de CasillaNum
        }
        return (opcion);                                        // En cualquier otro caso, se devuelve la orden inicial
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

    public boolean estaMarcada()
    {
        //Pre:
        //Post: Se devuelve true si esta casilla está marcada

        //TODO pensar sobre alternativas a este método que no atenten contra la ética moral de los principios de la ocultación de información. O dejarlo así.
        return (estadoAct instanceof Marcado);
    }

    protected void cambiarEstado (IEstadoCasilla pEstado, int pNum)
    {
        //Pre: Un estado y un número entero indicando las minas adyacentes
        //Post:Se ha cambiado el estado y avisado a los observers con el nuevo valor

        estadoAct = pEstado;
        lObservers.firePropertyChange("estado", null, pEstado.getClass().getSimpleName() + pNum);
    }
}