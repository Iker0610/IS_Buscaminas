package is.buscaminas.model.casillas;

import is.buscaminas.view.VistaCasilla;

public class CasillaTemp extends Casilla{

    public CasillaTemp(VistaCasilla pVistaCasilla) {
        super(pVistaCasilla);
    }

    public Casilla convertirEnMina (VistaCasilla pVista)
    {
        // Pre: La vista de una casilla
        // Post: Se crea una casilla mina con el mismo estado y la misma vista, habiendola retirado de la casilla temporal actual
        this.eliminarListener(pVista);
        CasillaMina nuevaCasilla = new CasillaMina(pVista);
        nuevaCasilla.cambiarEstado(super.getEstado());
        return nuevaCasilla;
    }

    public Casilla convertirEnNum (int pNum,VistaCasilla pVista)
    {
        // Pre: La vista de una casilla y el num. de minas adyacentes
        // Post: Se crea una casillaNum con el mismo estado y la misma vista, retiradola esta ultima de la casilla temporal actual
        this.eliminarListener(pVista);
        CasillaNum nuevaCasilla = new CasillaNum(pNum,pVista);
        nuevaCasilla.cambiarEstado(super.getEstado());
        return nuevaCasilla;
    }

    public void eliminarListener(VistaCasilla pCasilla)
    {
        //Pre: Una casilla de la vista
        //Post: Se ha eliminado la casilla de la vista de listener de esta casilla

        super.getObservers().removePropertyChangeListener(pCasilla);
    }
}
