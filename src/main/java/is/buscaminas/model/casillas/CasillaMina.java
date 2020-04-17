package is.buscaminas.model.casillas;

import is.buscaminas.view.VistaCasilla;

public class CasillaMina extends Casilla {

    //Constructora
    public CasillaMina (VistaCasilla pVistaCasilla){
        super(pVistaCasilla);
    }

    public CasillaMina (CasillaTemp pCasilla){
        //  Transforma la casilla temporal en una casilla mina
        super(pCasilla);
    }
}
