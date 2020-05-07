package is.buscaminas.model.ranking;

import is.buscaminas.Partida;
import is.buscaminas.model.Contador;
import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;
import is.buscaminas.view.VistaRanking;
import javafx.scene.Node;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Ranking {

    // Atributos
    private static Ranking mRanking;
    private OrderedDoubleLinkedList[] lJugadoresPorDificultad;
    private PropertyChangeSupport lObservers; //lista de observers
    private VistaRanking gridPane;

    // Constructora
    private Ranking ()
    {
        lJugadoresPorDificultad = new OrderedDoubleLinkedList[3];
        for (int i = 0; i < lJugadoresPorDificultad.length; i++) lJugadoresPorDificultad[i] = new OrderedDoubleLinkedList<>();
        lObservers = new PropertyChangeSupport(this);
        cargarRanking();
    }

    // Singleton
    public static Ranking getRanking ()
    {
        if (mRanking == null) mRanking = new Ranking();
        return mRanking;
    }

    // Métodos
    public void addObserver (PropertyChangeListener pObserver)
    {
        //Pre: Un observer
        //Post: Se ha añadido el observer a la lista de observers
        lObservers.addPropertyChangeListener(pObserver);
    }

    private void cargarRanking ()
    {
        String linea;
        int dificultadAct = 1;   //Leeremos 3 ficheros (0, 1 y 2)
        while (dificultadAct <= 3) {
            try {
                File archivoRanking = new File(String.valueOf(this.getClass().getResource("is/buscaminas/rankings/ranking" + dificultadAct + ".tsv")));

                FileReader fr = new FileReader(archivoRanking);
                BufferedReader br = new BufferedReader(fr);

                // Mientras queden usuarios por leer en el fichero
                while ((linea = br.readLine()) != null) {
                    //Obtenemos la información de cada jugador y la añadimos a la lista correspondiente
                    String[] datosJugador = linea.split("\t");
                    JugadorRanking jugador = new JugadorRanking(datosJugador[0], Integer.parseInt(datosJugador[1]));
                    lJugadoresPorDificultad[dificultadAct].add(jugador);
                }
                //Cerramos el fichero
                fr.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            dificultadAct++;
        }
    }

    public void actualizarRanking (int pDificultad)
    {
        Object[] top10 = lJugadoresPorDificultad[pDificultad].getTop10();
        //Escribir en el fichero
        try {
            File archivoRanking = new File(String.valueOf(this.getClass().getResource("is/buscaminas/rankings/ranking" + pDificultad + ".tsv")));
            FileWriter fileWritter = new FileWriter(archivoRanking, false);

            for (Object elem : top10) {
                if (elem instanceof JugadorRanking) {
                    JugadorRanking jugador = (JugadorRanking) elem;
                    fileWritter.write(jugador.getNombre() + "\t" + jugador.getPuntuacion());
                }
            }

            fileWritter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // notificamos a los observers de que hay que mostrar el ranking de otro nivel
        lObservers.firePropertyChange("numRanking", null, pDificultad);
    }

    public void addJugadorRanking (int pDificultad, String pNombre)
    {
        int puntuacionJugador = calcularPuntuacion(pDificultad);
        JugadorRanking jugador = new JugadorRanking(pNombre, puntuacionJugador);
        lJugadoresPorDificultad[pDificultad - 1].add(jugador);
        actualizarRanking(pDificultad);
    }

    public JugadorRanking[] obtenerRanking (int pDificultad)
    {
        // Devuelve los **10 primeros jugadores (si los hay)**
        // No sé si se debería hacer así o con observer, hay que preguntar a iñigo
        JugadorRanking[] lista = new JugadorRanking[9];
        Object elem;
        for (int i = 0; i < 10; i++) {
            elem = lJugadoresPorDificultad[pDificultad].getTop10()[i];
            if (elem instanceof JugadorRanking) {
                lista[i] = (JugadorRanking) elem;
            }
        }
        return lista;
    }

    private int calcularPuntuacion (int pDificultad)
    {
        return (1 / Contador.getContador().getSeconds()) * pDificultad * 2000;
    }
}