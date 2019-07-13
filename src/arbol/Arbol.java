package arbol;

import colores.Colores;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Arbol extends JPanel {

    private FileTreeModel ftmModelo;
    private JScrollPane spArbol;
    private JTree arbol;
    private Colores colores;

    public Arbol(File ruta, boolean esBiblioteca, int x, int y) throws IOException {
        this.setSize(x, y);
        this.setLayout(new BorderLayout());
        putClientProperty("JInternalFrame.isPalette", true);
        colores = new Colores();
        ftmModelo = new FileTreeModel(ruta);
        arbol = new JTree(ftmModelo);
        arbol.setBackground(colores.getBackgroundTree());

        if (esBiblioteca) {
            Image nota = ImageIO.read(getClass().getResource("src/img/nota.png"));
            arbol.setCellRenderer(new DefaultTreeCellRenderer() {

                @Override
                public Component getTreeCellRendererComponent(JTree tree,
                        Object value,
                        boolean bSelected,
                        boolean bExpanded,
                        boolean bLeaf,
                        int iRow,
                        boolean bHasFocus) {
                    File f = (File) value;
                    String text = f.getName();
                    Component c = super.getTreeCellRendererComponent(tree, text,
                            bSelected, bExpanded, bLeaf, iRow, bHasFocus);
                    if(bLeaf){
                        setIcon(new ImageIcon(nota));
                    }
                    return c;
                }
            });

        } else {
            arbol.setCellRenderer(new DefaultTreeCellRenderer() {

                @Override
                public Component getTreeCellRendererComponent(JTree tree,
                        Object value,
                        boolean bSelected,
                        boolean bExpanded,
                        boolean bLeaf,
                        int iRow,
                        boolean bHasFocus) {
                    File f = (File) value;
                    String text = f.getName();
                    Component c = super.getTreeCellRendererComponent(tree, text,
                            bSelected, bExpanded, bLeaf, iRow, bHasFocus);

                    return c;
                }
            });
        }
        if (arbol.getCellRenderer() instanceof DefaultTreeCellRenderer) {
            final DefaultTreeCellRenderer renderer
                    = (DefaultTreeCellRenderer) (arbol.getCellRenderer());
            renderer.setBackgroundNonSelectionColor(colores.getTreeChild());
            renderer.setBackgroundSelectionColor(colores.getTreeFather());
            renderer.setTextNonSelectionColor(colores.getTextColor());
            renderer.setTextSelectionColor(colores.getTextColor());
        }
        spArbol = new JScrollPane(arbol);
        spArbol.setBorder(null);
        add(spArbol, BorderLayout.CENTER);
    }

    public JScrollPane getSpArbol() {
        return spArbol;
    }

    public JTree getArbol() {
        return arbol;
    }
}
