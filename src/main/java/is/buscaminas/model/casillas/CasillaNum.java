package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Despejado;
import is.buscaminas.model.casillas.estados.IEstadoBoton;
import is.buscaminas.view.BotonCasilla;

public class CasillaNum extends Casilla {
    private int minasAdyacentes;

    public CasillaNum (int pMinasAdyacentes, BotonCasilla pBotonCasilla)
    {
        super(pBotonCasilla);
        minasAdyacentes = pMinasAdyacentes;
    }

    public boolean hayMinasAdyacentes ()
    {
        return (minasAdyacentes == 0);
    }

    @Override
    public void cambiarEstado (IEstadoBoton pEstado)
    {
        if (pEstado instanceof Despejado) { cambiarEstado(pEstado, minasAdyacentes); }
        else { cambiarEstado(pEstado); }
    }
}
