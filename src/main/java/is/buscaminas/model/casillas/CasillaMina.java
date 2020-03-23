package is.buscaminas.model.casillas;

import is.buscaminas.model.casillas.estados.Oculto;
import is.buscaminas.view.BotonCasilla;

public class CasillaMina extends Casilla {

    //Constructora
    public CasillaMina(BotonCasilla pBotonCasilla){
        super(pBotonCasilla);
        cambiarEstado(new Oculto());
    }
}
