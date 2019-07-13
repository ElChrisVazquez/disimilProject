package secciones;

import arbol.Arbol;
import colores.Colores;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

public class TreePanel extends JPanel {

    private final int ancho = 230;
    private Colores colores;
    private Arbol arbol;

    public TreePanel(int alto, File ruta) throws IOException {
        this.setSize(ancho, alto);
        this.setLayout(null);

        // Inicializa colores
        colores = new Colores();

        // Aplica color de fondo
        this.setBackground(colores.getBackgroundTree());

        arbol = new Arbol(ruta, false, ancho, alto);

        this.add(arbol);
    }

}
