package is.buscaminas.view;

import is.buscaminas.model.Contador;
import javafx.scene.layout.GridPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VistaContador extends GridPane implements PropertyChangeListener {
    VistaCifraContador cifra1;
    VistaCifraContador cifra2;
    VistaCifraContador cifra3;

    public VistaContador ()
    {
        cifra1 = new VistaCifraContador();
        cifra2 = new VistaCifraContador();
        cifra3 = new VistaCifraContador();

        this.add(cifra3, 0, 0);
        this.add(cifra2, 1, 0);
        this.add(cifra1, 2, 0);

        Contador.getContador().addObserver(this);
    }

    @Override
    public void propertyChange (PropertyChangeEvent pEvento)
    {
        actualizarContador((Integer) pEvento.getNewValue());
    }

    private void actualizarContador (int pTiempo)
    //Pre: Un entero indicando el tiempo
    //Post: Se ha actualizado el valor del contador
    {
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