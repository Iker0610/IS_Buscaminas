package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Despejado;
import is.buscaminas.model.casillas.estados.IEstadoBoton;
import is.buscaminas.view.VistaCasilla;

public class CasillaNum extends Casilla {
    private int minasAdyacentes;

    //Constructora
    public CasillaNum (int pMinasAdyacentes, VistaCasilla pVistaCasilla)
    {
        super(pVistaCasilla);
        minasAdyacentes = pMinasAdyacentes;
    }

    public boolean estaDespejado ()
            //Pre:
            //Post: Si no hay minas adyacentes devuelve true, si no false
    {
        return (minasAdyacentes == 0);
    }

    @Override
    public void cambiarEstado (IEstadoBoton pEstado)
            //Pre: Un estado
            //Post: Se ha cambiado de estado
    {
        if (pEstado instanceof Despejado) { cambiarEstado(pEstado, minasAdyacentes); }
        else { cambiarEstado(pEstado); }
    }
}
