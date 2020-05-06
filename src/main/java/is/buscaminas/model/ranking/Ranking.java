package is.buscaminas.model.ranking;

import is.buscaminas.model.estructurasDatos.OrderedDoubleLinkedList;

public class Ranking {

    // Atributos
    private static Ranking mRanking;
    private OrderedDoubleLinkedList<JugadorRanking>[] lJugadoresPorDificultad;

    // Constructora
    private Ranking ()
    {
        lJugadoresPorDificultad = new OrderedDoubleLinkedList[3];
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
    private void cargarRanking ()
    {
        // Carga de fichero
        // TODO
    }

    public void actualizarRanking ()
    {
        // Guarda en el fichero **los 10 primeros**
        //TODO
    }

    public void addJugadorRanking (int pDificultad, String pNombre)
    {
        int puntuacionJugador = calcularPuntuacion(pDificultad);
        JugadorRanking jugador = new JugadorRanking(pNombre, puntuacionJugador);
        lJugadoresPorDificultad[pDificultad].add(jugador);
        actualizarRanking();
    }

    public JugadorRanking[] obtenerRanking ()
    {
        // Devuelve los **10 primeros jugadores (si los hay)**
        // No sé si se debería hacer así o con observer, hay que preguntar a iñigo

        return new  JugadorRanking[10];
    }

    private int calcularPuntuacion (int pDificultad)
    {
        //TODO
        return 0;
    }
}
