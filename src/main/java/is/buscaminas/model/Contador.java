package is.buscaminas.model;

import is.buscaminas.view.VistaContador;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Contador {

    //Atributos
    private static Contador mContador;
    private int seconds;
    private Timer timer;
    private PropertyChangeSupport lObservers; //lista de observers

    //Constructora
    private Contador ()
    {
        lObservers = new PropertyChangeSupport(this);
        timer = new Timer(true); //Con el is Daemon se indica que el hilo que generará esa clase se puede finalizar sin problemas al cerrar la aplicación
    }

    //Metodo get del Singleton
    public static Contador getContador ()
    {
        if (mContador == null) {
            mContador = new Contador();
        }
        return mContador;
    }

    //Métodos:

    public void addObserver (PropertyChangeListener pObserver)
    {
        //Pre: Un observer
        //Post: Se ha añadido el observer a la lista de observers

        lObservers.addPropertyChangeListener(pObserver);
    }

    public void inicio ()
    {
        //Pre:
        //Post: Se ha iniciado el conteo. Cada segundo se notificará a los observers

        /*
         * Para implementar el timer se usa la clase Timer.
         * Esta clase permite ejecutar tareas en momentos concretos. En este caso se ha empleado la opción de ejecutar tareas periódicamente.
         * Para ello se pasa al método scheduleAtFixedRate() la tarea que se desea ejecutar (TimerTask)
         * y el tiempo hasta la primera ejecución y el tiempo entre las ejecuciones posteriores (el periodo). (Ambos en milisegundos)
         */

        seconds = -1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run ()
            {
                lObservers.firePropertyChange("seconds", seconds, ++seconds);
            }
        }, 0, 1000);
    }

    public void parar ()
    {
        //Pre:
        //Post: Se ha parado el contador

        timer.cancel(); //Elimina la tarea que se está ejecutando en estos momentos
        timer.purge();  //Elimina el resto de tareas de la cola
    }


    /*
    Codigo alternativo empleando los Thread:

    @Override
    public void run ()
    {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Thread.sleep(1000);
                lObservers.firePropertyChange("seconds", seconds, ++seconds);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     */
}
