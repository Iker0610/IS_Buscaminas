package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Despejado;
import is.buscaminas.model.casillas.estados.IEstadoCasilla;
import is.buscaminas.view.VistaCasilla;

public class CasillaNum extends Casilla {

    //Atributos
    private int minasAdyacentes;

    //Constructora
    public CasillaNum (int pMinasAdyacentes, VistaCasilla pVistaCasilla)
    {
        super(pVistaCasilla);
        minasAdyacentes = pMinasAdyacentes;
    }

    public CasillaNum (int pMinasAdyacentes, CasillaTemp pCasilla){
        //  Transforma la casilla temporal en una casilla numérica
        super(pCasilla);
        minasAdyacentes = pMinasAdyacentes;
    }

    //Metodos
    public boolean estaDespejado ()
    {
        //Pre:
        //Post: Si no hay minas adyacentes devuelve true, si no false

        return (minasAdyacentes == 0);
    }

    @Override
    public void cambiarEstado (IEstadoCasilla pEstado)
    {
        //Pre: Un estado
        //Post: Se ha cambiado de estado, si el nuevo estado es Despejado se indica el número de minas adyacentes

        if (pEstado instanceof Despejado) { cambiarEstado(pEstado, minasAdyacentes); }
        else { super.cambiarEstado(pEstado); }
    }
}
