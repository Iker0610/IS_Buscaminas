package is.buscaminas.view;

import is.buscaminas.model.Contador;
import javafx.scene.layout.GridPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VistaContador extends GridPane implements PropertyChangeListener {
    /*
     * Esta clase es un elemento de la vista
     * Extiende del GridPane lo que significa que se puede considerar un GridPane con finciones especiales
     * Se ha optado por un GridPane pues este nos permitía poner elementos en el ordenadamente
     *
     * Este GridPane tiene 3 cifras, estos son elementos de Vista personalizados (comoe este).
     */

    //Atributos
    VistaCifraContador cifra1;
    VistaCifraContador cifra2;
    VistaCifraContador cifra3;

    //Constructora
    public VistaContador ()
    {
        super();

        //Se inicializan las 3 cifras
        cifra1 = new VistaCifraContador();
        cifra2 = new VistaCifraContador();
        cifra3 = new VistaCifraContador();

        //Se añade cada una en una columna del GridPane
        this.add(cifra3, 0, 0);
        this.add(cifra2, 1, 0);
        this.add(cifra1, 2, 0);

        //Se añade esta instancia como observer del contador
        Contador.getContador().addObserver(this);
    }

    //Metodos
    @Override
    public void propertyChange (PropertyChangeEvent pEvento)
    {
        actualizarContador((Integer) pEvento.getNewValue());
    }

    private void actualizarContador (int pTiempo)
    {
        //Pre: Un entero indicando la cuenta actual
        //Post: Se ha actualizado el display del contador (cifra a cifra)

        if (pTiempo < 1000) {
            int cifraAct;

            cifraAct = pTiempo % 10;
            cifra1.cambiarCifra(cifraAct);

            cifraAct = (pTiempo % 100) / 10;
            cifra2.cambiarCifra(cifraAct);

            cifraAct = (pTiempo % 1000) / 100;
            cifra3.cambiarCifra(cifraAct);
        }
    }
}