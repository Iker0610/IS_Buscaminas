package is.buscaminas.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Contador {
    private static Contador miContador;
    private int seconds,preSeconds;
    private Timer timer;
    private PropertyChangeSupport observers; //lista de observers


    private Contador(){
        observers = new PropertyChangeSupport(this);
        timer = new Timer(true);
    }
    public static Contador getContador ()
    {
        if (miContador==null) {
            miContador = new Contador();
        }
        return miContador;
    }

    public Contador (PropertyChangeListener pObserver)
    {
        observers = new PropertyChangeSupport(this);
        observers.addPropertyChangeListener(pObserver);
        timer = new Timer(true);
    }

    public void addObserver (PropertyChangeListener pObserver)
    {
        observers.addPropertyChangeListener(pObserver);
    }

    public void start ()
    {
        seconds = -1;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run () {
                preSeconds=seconds;
                seconds++;
                if (seconds < 10000) {
                    observers.firePropertyChange("seconds", preSeconds, seconds);
                }
            }
        }, 1000L, 1000L);
    }

    public void stop ()
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
                pcs.firePropertyChange("seconds", seconds, ++seconds);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     */
}
