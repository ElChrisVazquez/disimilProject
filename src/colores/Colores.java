package colores;

import java.awt.Color;

public class Colores {

    private Color background, barColor, textColor, backgroundTree, treeFather, treeSon,
            volPan, panel, backPanel, backRepro, barChild;

    public Colores() {
        background = Color.decode("#646c74");
        barColor = Color.decode("#596267");
        barChild = Color.decode("#5b686e");
        textColor = Color.decode("#d8e0e4");
        backgroundTree = Color.decode("#424c51");
        treeFather = Color.decode("#283136");
        treeSon = Color.decode("#172025");
        volPan = Color.decode("#d99259");
        panel = Color.decode("#5f686d");
        backPanel = Color.decode("#3f484d");
        backRepro = Color.decode("#323b3f");
    }

    /**
     * Fondo
     * @return #646c74
     */
    public Color getBackground() {
        return background;
    }

    public Color getBarColor() {
        return barColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundTree() {
        return backgroundTree;
    }

    public Color getTreeFather() {
        return treeFather;
    }

    public Color getTreeSon() {
        return treeSon;
    }

    public Color getVolPan() {
        return volPan;
    }

    public Color getPanel() {
        return panel;
    }

    public Color getBackPanel() {
        return backPanel;
    }

    public Color getBackRepro() {
        return backRepro;
    }

    public Color getBarChild() {
        return barChild;
    }
}
