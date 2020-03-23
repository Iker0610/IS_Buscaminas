package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.BotonCasilla;

public class CasillaNum extends Casilla {
    private int minasAdyacentes;

    public CasillaNum (int pMinasAdyacentes, BotonCasilla pBotonCasilla)
    {
        super(pBotonCasilla);
        minasAdyacentes = pMinasAdyacentes;
        cambiarEstado(new Oculto());
    }

    public boolean hayMinasAdyacentes ()
    {
        return (minasAdyacentes == 0);
    }
}
