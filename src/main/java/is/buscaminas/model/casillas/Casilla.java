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

    // ------------------------------------------------------------------------------------------------------------------------------------
    // Métodos incluidos en esta clase abstracta que son usados para convertir una casillaTemp en casillaMina o casillaNum:
    // ------------------------------------------------------------------------------------------------------------------------------------

    public Casilla convertirEnMina(VistaCasilla pVista) {
        // Pre: La vista de una casilla
        // Post: Se crea una casillaMina con el mismo estado y la misma vista que la casilla actual
        this.eliminarListener(pVista);
        CasillaMina nuevaCasilla = new CasillaMina(pVista);
        nuevaCasilla.cambiarEstado(estadoAct);
        return nuevaCasilla;
    }

    public Casilla convertirEnNum (int pNum,VistaCasilla pVista)
    {
        // Pre: La vista de una casilla y el num. de minas adyacentes
        // Post: Se crea una casillaNum con el mismo estado y la misma vista que la casilla actual
        this.eliminarListener(pVista);
        CasillaNum nuevaCasilla = new CasillaNum(pNum,pVista);
        nuevaCasilla.cambiarEstado(estadoAct);
        return nuevaCasilla;
    }

    public void eliminarListener(VistaCasilla pCasilla)
    {
        //Pre: Una casilla (de la vista)
        //Post: Se ha eliminado de listener la casilla (de la vista) de esta casilla

        lObservers.removePropertyChangeListener(pCasilla);
    }
}