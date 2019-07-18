package secciones;

import arbol.Arbol;
import colores.Colores;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

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

        // Seleccion de arbol
        arbol.getArbol().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                TreePath tp = arbol.getArbol().getSelectionPath();
                if (tp != null) {
                    Object filePathToAdd = tp.getLastPathComponent();
                    if (filePathToAdd instanceof File) {
                        File node = (File) filePathToAdd;
                        if (node.isFile() && node.getPath().endsWith(".wav")) {
                            System.out.println(node.getPath());
                            arbol.getArbol().clearSelection();
                        } else if (node.isDirectory()) {
                        }
                    }
                }
            }
        });
        this.add(arbol);
    }

}


