package is.buscaminas.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Contador {
    private static Contador mContador;
    private int seconds;
    private Timer timer;
    private PropertyChangeSupport lObservers; //lista de observers


    private Contador ()
    {
        lObservers = new PropertyChangeSupport(this);
        timer = new Timer(true);
    }

    public static Contador getContador ()
    {
        if (mContador == null) {
            mContador = new Contador();
        }
        return mContador;
    }

    public void addObserver (PropertyChangeListener pObserver)
    {
        lObservers.addPropertyChangeListener(pObserver);
    }

    public void inicio ()
    {
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
        timer.cancel();
        timer.purge();
    }

    /*
    Codigo alternativo empleando los Thread
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
