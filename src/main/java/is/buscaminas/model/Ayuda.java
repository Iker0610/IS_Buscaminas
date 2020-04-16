package is.buscaminas.model;

import is.buscaminas.view.VistaAyuda;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Ayuda {
    //Atributos
    private static Ayuda mAyuda;
    private int ayudaAct = 1;
    private PropertyChangeSupport lObservers; //lista de observers

    //constructora
    private Ayuda (){
        lObservers = new PropertyChangeSupport(this);
    }

    public static Ayuda getAyuda(){
        if (mAyuda == null) {
            mAyuda = new Ayuda();
        }
        return mAyuda;
    }



    //metodos

    public void siguiente() {
        //Pre:
        //Post: Se aumenta en uno el número de página de ayuda. Si el número antes de aumentar era el máximo, se vuelve a la 1.
        if (ayudaAct==numImagenes()) ayudaAct=1;
        else ayudaAct++;

        //Notifico a la vista de la nueva página a mostrar
        lObservers.firePropertyChange("numPagAyuda", null, ayudaAct);
    }


    public void anterior() {
        //Pre:
        //Post: Se disminuye en uno el número de página de ayuda. Si el número antes de disminuir era el mínimo, se va a la última pag.
        if (ayudaAct==1) ayudaAct=numImagenes();
        else ayudaAct--;

        //Notifico a la vista de la nueva página a mostrar
        lObservers.firePropertyChange("numPagAyuda", null, ayudaAct);
    }


    private int numImagenes(){
        //Pre:
        //Post: Devuelve el número de páginas de ayuda que tenemos

        File carpeta = new File("src/main/resources/is/buscaminas/ui/assets/ayuda");
        return carpeta.listFiles().length;
    }

    //patron observer
    public void addObserver (PropertyChangeListener pObserver)
    {
        //Pre: Un observer
        //Post: Se ha añadido el observer a la lista de observers

        lObservers.addPropertyChangeListener(pObserver);
        lObservers.firePropertyChange("numPagAyuda", null, ayudaAct); //para que al crear el menú de ayuda tengamos la primera página
    }
}
