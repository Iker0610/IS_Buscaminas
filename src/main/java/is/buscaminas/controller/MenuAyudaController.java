package is.buscaminas.controller;


import is.buscaminas.view.VistaAyuda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

public class MenuAyudaController {

    // Atributos
    private int pagTotal;
    private int pagAct;

    // Elementos FXML
    @FXML
    private VistaAyuda panelAyuda;

    @FXML
    private Text paginacion;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrev;

    // Construtora
    @FXML
    private void initialize ()
    {
        pagAct = 0;
        pagTotal = getNumPagTotal();
        btnPrev.setDisable(true);
        if (pagTotal <= 1) btnNext.setDisable(true);
        if (pagTotal > 0) {
            pagAct = 1;
            paginacion.setText(pagAct + "/" + pagTotal);
            panelAyuda.cambiarPaginaAyuda(pagAct);
        }
    }

    // Metodos
    private int getNumPagTotal ()
    {
        //Pre:
        //Post: Devuelve el número de páginas de ayuda que tenemos

        File[] paginas = new File("src/main/resources/is/buscaminas/ui/ayuda/").listFiles();
        return paginas != null ? paginas.length : 0;
    }

    // Metodos de eventos
    @FXML
    private void nextAyuda ()
    {
        pagAct++;
        paginacion.setText(pagAct + "/" + pagTotal);

        panelAyuda.cambiarPaginaAyuda(pagAct);
        if (pagAct == pagTotal) btnNext.setDisable(true);
        if (pagAct > 1) btnPrev.setDisable(false);
    }

    @FXML
    private void prevAyuda ()
    {
        pagAct--;
        paginacion.setText(pagAct + "/" + pagTotal);

        panelAyuda.cambiarPaginaAyuda(pagAct);
        if (pagAct == 1) btnPrev.setDisable(true);
        if (pagAct < pagTotal) btnNext.setDisable(false);
    }
}
