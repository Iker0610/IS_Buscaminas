package is.buscaminas.model.ranking;

import is.buscaminas.model.Contador;
import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ranking {

    // Atributos
    private static Ranking mRanking;
    private OrderedDoubleLinkedList<JugadorRanking>[] lJugadoresPorDificultad;

    // Constructora
    private Ranking ()
    {
        lJugadoresPorDificultad = new OrderedDoubleLinkedList[2];
        for (int i = 0; i < lJugadoresPorDificultad.length; i++) lJugadoresPorDificultad[i] = new OrderedDoubleLinkedList<>();
        cargarRanking();
    }

    // Singleton
    public static Ranking getRanking ()
    {
        if (mRanking == null) mRanking = new Ranking();
        return mRanking;
    }

    // Métodos
    private void cargarRanking()
    {
        File archivo;
        FileReader fr;
        String linea;
        int fich = 0;   //Leeremos 3 ficheros (0,1 y 2)
        String[] usuario = new String[1];
        while (fich != 2)
        {
            try
            {
                if (fich == 0)
                {
                    archivo = new File("ui.rankings.ranking1.tsv");
                    fich++;
                }
                else if (fich == 1)
                {
                    archivo = new File("ui.rankings.ranking2.tsv");
                    fich++;
                }
                else
                {
                    archivo = new File("ui.rankings.ranking3.tsv");
                }

                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                // Mientras queden usuarios por leer en el fichero
                while ((linea = br.readLine()) != null)
                {
                    //Obtenemos la información de cada jugador y la añadimos a la lista correspondiente
                    usuario = linea.split("\t");
                    JugadorRanking jugador = new JugadorRanking(usuario[0], Integer.parseInt(usuario[1]));
                    lJugadoresPorDificultad[fich].add(jugador);
                }
                //Cerramos el fichero
                fr.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void actualizarRanking ()
    {
        for(int i = 0; i < 3; i++)
        {
            OrderedDoubleLinkedList ranking = lJugadoresPorDificultad[i];
            while (ranking.size() > 10)
            {
                ranking.removeLast();
            }
        }
    }

    public void addJugadorRanking (int pDificultad, String pNombre)
    {
        int puntuacionJugador = calcularPuntuacion(pDificultad);
        JugadorRanking jugador = new JugadorRanking(pNombre, puntuacionJugador);
        lJugadoresPorDificultad[pDificultad-1].add(jugador);
        actualizarRanking();
    }

    public JugadorRanking[] obtenerRanking(){
        // Devuelve los **10 primeros jugadores (si los hay)**
        // No sé si se debería hacer así o con observer, hay que preguntar a iñigo

        return new  JugadorRanking[10];
    }

    private int calcularPuntuacion (int pDificultad)
    {
        return (1/Contador.getContador().getSeconds())*pDificultad*2000;
    }
}