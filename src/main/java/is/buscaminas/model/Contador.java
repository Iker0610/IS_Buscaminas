package is.buscaminas.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class Contador {

    private int seconds;
    private Timer timer;
    private PropertyChangeSupport pcs;

    public Contador ()
    {
        pcs = new PropertyChangeSupport(this);
        timer = new Timer(true);
    }

    public Contador (PropertyChangeListener pObserver)
    {
        pcs = new PropertyChangeSupport(this);
        pcs.addPropertyChangeListener(pObserver);
        timer = new Timer(true);
    }

    public void addObserver (PropertyChangeListener pObserver)
    {
        pcs.addPropertyChangeListener(pObserver);
    }

    public void start ()
    {
        seconds = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run ()
            {
                pcs.firePropertyChange("seconds", seconds, ++seconds);
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
