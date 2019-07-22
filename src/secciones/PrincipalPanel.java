package secciones;

import colores.Colores;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class PrincipalPanel extends JPanel {

    private final int ancho = 540;
    private final int boxsize = 120;
    private final int boxempty = 55;
    private int alto, max_size, id_soundpanel, hash_to_delete;

    private JPanel[] desplazamiento;

    private JScrollPane jspcaja;
    private Colores colores;
    private ArrayList<SoundPanel> splista;
    private SoundPanel sounPanel;
    private JPanel jpinterno;
    private BoxLayout boxLayout;

    private JPopupMenu jpop_menu;
    private JMenuItem jmielminiar;
    private ActionListener action_delete;

    public PrincipalPanel(int max_size, int id_soundpanel) throws FontFormatException, IOException {
        this.id_soundpanel = id_soundpanel;
        this.max_size = max_size;
        alto = boxempty;
        this.setSize(ancho, alto + 25);
        this.setLayout(null);

        // Inicializa nativos
        hash_to_delete = 0;

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

        // Inicializa popmenu
        jpop_menu = new JPopupMenu();
        jmielminiar = new JMenuItem("Eliminar");
        jpop_menu.add(jmielminiar);
        jmielminiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index_to_delete = 0;
                for (int i = 0; i < splista.size(); i++) {
                    if (hash_to_delete == splista.get(i).hashCode()) {
                        index_to_delete = i;
                    }
                }
                delete(index_to_delete);
            }
        });

        jspcaja.add(jpinterno);
        this.add(jspcaja);
    }

    public void create(File sound, String nombre) throws FontFormatException, IOException {
        sounPanel = new SoundPanel(sound, nombre);
        sounPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    jpop_menu.show(e.getComponent(), e.getX(), e.getY());
                    hash_to_delete = e.getComponent().hashCode();
                }
            }
        });
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
            for (int i = 0; i < desplazamiento.length; i++) {
                desplazamiento[i].setLocation(desplazamiento[i].getX(), alto + 15);
            }
        }
    }

    public void delete(int index) {
        splista.remove(index);
        jpinterno.remove(index);
        for (int i = 0; i < jpinterno.getComponentCount(); i++) {
            jpinterno.getComponent(i).setLocation(0, 0);
        }
 
    }

    /**
     * La altura vuelve a 55 Limpia la lista Establece altura de los componentes
     * internos.
     */
    public void deleteAll() {
        alto = boxempty;
        splista.removeAll(splista);
        setSize(ancho, alto + 25);
        jpinterno.removeAll();
        jpinterno.setSize(ancho, alto);
        jspcaja.setSize(ancho, alto);
        for (int i = 0; i < desplazamiento.length; i++) {
            desplazamiento[i].setLocation(desplazamiento[i].getX(), alto + 15);
        }
    }

    public ArrayList<SoundPanel> getSplista() {
        return splista;
    }

    public JPanel[] getDesplazamiento() {
        return desplazamiento;
    }

    public SoundPanel getSounPanel() {
        return sounPanel;
    }
}
