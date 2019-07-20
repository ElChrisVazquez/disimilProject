package secciones;

import colores.Colores;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PrincipalPanel extends JPanel {

    private final int ancho = 540;
    private final int boxsize = 120;
    private final int boxempty = 55;
    private int alto, max_size;

    private JPanel[] desplazamiento;

    private JScrollPane jspcaja;
    private Colores colores;
    private ArrayList<SoundPanel> splista;
    private SoundPanel sounPanel;
    private JPanel jpinterno;
    private BoxLayout boxLayout;

    public PrincipalPanel(int max_size) throws FontFormatException, IOException {
        this.max_size = max_size;
        alto = boxempty;
        this.setSize(ancho, alto + 25);
        this.setLayout(null);

        // Inicializa colores
        colores = new Colores();
        this.setBackground(colores.getBackPanel());

        // Inicializa panel interno
        jpinterno = new JPanel();
        jpinterno.setSize(ancho, alto);
        jpinterno.setBackground(colores.getBackPanel());
        boxLayout = new BoxLayout(jpinterno, BoxLayout.Y_AXIS);
        jpinterno.setLayout(boxLayout);
        jpinterno.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Inicializa el scrollpane
        jspcaja = new JScrollPane();
        jspcaja.setLayout(null);
        jspcaja.setBounds(0, 0, ancho, alto);
        jspcaja.setBorder(null);
        jspcaja.setBackground(colores.getBackPanel());

        // Inicializa la lista de sonidos
        splista = new ArrayList<>();

        // Inicializa los botones de desplazamiento
        desplazamiento = new JPanel[16];
        for (int i = 0, j = 0; i < desplazamiento.length; i++, j += 15) {
            desplazamiento[i] = new JPanel();
            desplazamiento[i].setBounds(270 + j, alto + 15, 15, 10);
            desplazamiento[i].setBackground(colores.getBackRepro());
            this.add(desplazamiento[i]);
        }

        jspcaja.add(jpinterno);
        this.add(jspcaja);
    }

    public void create(File sound, String nombre) throws FontFormatException, IOException {
        sounPanel = new SoundPanel(sound, nombre);
        splista.add(sounPanel);
        refresh();
    }

    public void refresh() {
        if (!splista.isEmpty()) {
            splista.forEach((sound) -> jpinterno.add(sound));
            alto = boxsize * splista.size();
            setSize(ancho, alto + 25);
            jpinterno.setSize(ancho, alto);
            jspcaja.setSize(ancho, alto);
            System.out.println(alto);
            repaint();
        }
    }

    /**
     * La altura vuelve a 55 Limpia la lista Establece altura de los componentes
     * internos.
     */
    public void deleteAll() {
        alto = boxempty;
        splista.removeAll(splista);
        setSize(ancho, alto);
        jpinterno.setSize(ancho, alto);
        jspcaja.setSize(ancho, alto);
    }

    public ArrayList<SoundPanel> getSplista() {
        return splista;
    }

    public JPanel[] getDesplazamiento() {
        return desplazamiento;
    }
    
    

}
