package secciones;

import colores.Colores;
import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class PrincipalPanel extends JPanel {

    private final int ancho = 540;
    private int alto;
    private JScrollPane jspcaja;
    private Colores colores;
    private ArrayList<SoundPanel> splista;
    private SoundPanel sounPanel;
    private JPanel jpinterno;
    private BoxLayout boxLayout;

    public PrincipalPanel(int alto) {
        this.alto = alto;
        this.setSize(ancho, alto);
        this.setLayout(null);

        // Inicializa colores
        colores = new Colores();
        this.setBackground(colores.getBackPanel());

        // Inicializa panel interno
        jpinterno = new JPanel();
        jpinterno.setSize(540, 120);
        jpinterno.setBackground(colores.getBackPanel());
        boxLayout = new BoxLayout(jpinterno, BoxLayout.X_AXIS);
        jpinterno.setLayout(boxLayout);
        jpinterno.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Inicializa el scrollpane
        jspcaja = new JScrollPane();
        jspcaja.setLayout(null);
        jspcaja.setBounds(0, 0, ancho, alto);
        jspcaja.setBorder(null);
        jspcaja.setBackground(colores.getBackPanel());

        splista = new ArrayList<>();

        create("", "lorem");

        if (!splista.isEmpty()) {
            splista.forEach((sound) -> jpinterno.add(sound));
        }

        jspcaja.add(jpinterno);
        this.add(jspcaja);
    }

    public void create(String path, String name) {
        sounPanel = new SoundPanel(name);
        splista.add(sounPanel);
    }
}
