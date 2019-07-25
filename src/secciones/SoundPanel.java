package secciones;

import botones.BtnSound;
import colores.Colores;
import extra.Pan;
import extra.Vol;
import font.Fuente;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class SoundPanel extends JPanel {

    private final int ancho = 500;
    private final int alto = 100;
    private ImageIcon iimute, iimute_pressed, iisolo, iisolo_pressed, iipatron,
            iipatron_pressed, iipatron_relased, iipatron_black, iisound,
            iisound_pressed, iiselected, iiselected_none;
    private JToggleButton[] btnpatron;
    private JToggleButton btnselected, btnmute, btnsolo;
    private File sound;
    private String nombre;
    private Colores colores;
    private Fuente fuente;
    private BtnSound btnSound;
    private Vol volumen;
    private Pan paneo;

    private JPopupMenu jpop_menu, jpop_patron;
    private JMenuItem jmielminiar, jmi2, jmi4, jmi8, jmi1, jmi16;

    private ActionListener al_patron;

    public SoundPanel(File sound, String nombre) throws FontFormatException, IOException {
        this.sound = sound;
        this.nombre = nombre;
        this.setSize(ancho, alto);
        this.setLayout(null);
        this.setName(nombre);

        // Inicializa colores
        colores = new Colores();

        // Cambia color de fondo
        this.setBackground(colores.getSoundPanel());

        // Inicializa la fuente
        fuente = new Fuente();

        // Inicializando las imagenes
        iimute = new ImageIcon("src/img/btn_mute.png");
        iimute_pressed = new ImageIcon("src/img/btn_mute_pressed.png");
        iisolo = new ImageIcon("src/img/btn_solo.png");
        iisolo_pressed = new ImageIcon("src/img/btn_solo_pressed.png");
        iipatron = new ImageIcon("src/img/btn_patron.png");
        iipatron_pressed = new ImageIcon("src/img/btn_patron_pressed.png");
        iipatron_relased = new ImageIcon("src/img/btn_patron_relased.png");
        iipatron_black = new ImageIcon("src/img/btn_patron_black.png");
        iiselected = new ImageIcon("src/img/btn_selected.png");
        iiselected_none = new ImageIcon("src/img/btn_selected_none.png");
        iisound = new ImageIcon("src/img/btn_sound.png");
        iisound_pressed = new ImageIcon("src/img/btn_sound_pressed.png");

        // Inicializando botones
        btnmute = new JToggleButton(iimute);
        btnmute.setSelectedIcon(iimute_pressed);
        btnmute.setBorder(null);
        btnpatron = new JToggleButton[16];
        for (int i = 0; i < btnpatron.length; i++) {
            if ((i > 3 && i < 8) || (i > 11 && i < 16)) {
                btnpatron[i] = new JToggleButton();
                btnpatron[i].setIcon(iipatron_black);
                btnpatron[i].setSelectedIcon(iipatron_relased);
                btnpatron[i].setBorder(null);
            } else {
                btnpatron[i] = new JToggleButton();
                btnpatron[i].setIcon(iipatron);
                btnpatron[i].setSelectedIcon(iipatron_relased);
                btnpatron[i].setBorder(null);
            }

        }
        btnsolo = new JToggleButton(iisolo);
        btnsolo.setSelectedIcon(iisolo_pressed);
        btnsolo.setBorder(null);
        btnSound = new BtnSound(iisound, iisound_pressed, nombre);
        btnselected = new JToggleButton(iiselected_none);
        btnselected.setSelectedIcon(iiselected);
        btnselected.setBorder(null);

        // Inicializa volumen
        volumen = new Vol();

        // Inicializa paneo
        paneo = new Pan();

        // Inicializa popmenu eliminar
        jpop_menu = new JPopupMenu();
        jmielminiar = new JMenuItem("Eliminar");
        jpop_menu.add(jmielminiar);

        // Inicializa popmenu patrones
        jpop_patron = new JPopupMenu();
        jmi2 = new JMenuItem("Cada 2", 0);
        jmi2.setName("2");
        jmi4 = new JMenuItem("Cada 4", 0);
        jmi4.setName("4");
        jmi8 = new JMenuItem("Cada 8", 0);
        jmi8.setName("8");
        jmi1 = new JMenuItem("Todos", 0);
        jmi1.setName("1");
        jmi16 = new JMenuItem("Limpiar", 0);
        jmi16.setName("16");
        jpop_patron.add(jmi2);
        jpop_patron.add(jmi4);
        jpop_patron.add(jmi8);
        jpop_patron.add(jmi1);
        jpop_patron.add(jmi16);

        //Action listener para pintar los patrones
        al_patron = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                int modulo = Integer.valueOf(jmi.getName());
                for (int i = 0; i < btnpatron.length; i++) {
                    if (i % modulo == 0) {
                        btnpatron[i].setSelected(true);
                    }else{
                        btnpatron[i].setSelected(false);
                    }
                }
            }
        };

        // Añade el action a los jmi
        jmi2.addActionListener(al_patron);
        jmi4.addActionListener(al_patron);
        jmi8.addActionListener(al_patron);
        jmi1.addActionListener(al_patron);
        jmi16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < btnpatron.length; i++) {
                    btnpatron[i].setSelected(false);
                }
            }
        });

        btnSound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    jpop_patron.show(btnSound, e.getX(), e.getY());
                }
            }

        });

        // Posiciona los botones
        btnsolo.setBounds(20, 40, 20, 20);
        btnmute.setBounds(40, 40, 20, 20);
        btnSound.setBounds(140, 35, 85, 30);
        btnselected.setBounds(230, 40, 20, 20);
        for (int i = 0, j = 0; i < btnpatron.length; i++, j += 15) {
            btnpatron[i].setBounds(250 + j, 40, 15, 20);
        }

        // Posiciona vol y pan
        volumen.setLocation(75, 30);
        paneo.setLocation(75, 60);

        //Crea borde
        this.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 5, colores.getBackPanel()));

        // Evento mouse Adapter para eliminar
        // Añade elementos
        this.add(btnSound);
        this.add(btnsolo);
        this.add(btnmute);
        this.add(volumen);
        this.add(paneo);
        this.add(btnselected);
        for (int i = 0; i < btnpatron.length; i++) {
            this.add(btnpatron[i]);
        }
    }

    public JToggleButton[] getBtnpatron() {
        return btnpatron;
    }

    public File getSound() {
        return sound;
    }

    public Vol getVolumen() {
        return volumen;
    }

    public Pan getPaneo() {
        return paneo;
    }

    public JMenuItem getJmielminiar() {
        return jmielminiar;
    }

    public JToggleButton getBtnmute() {
        return btnmute;
    }

    public JToggleButton getBtnsolo() {
        return btnsolo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
