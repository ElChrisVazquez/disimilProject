package secciones;

import botones.BtnSound;
import colores.Colores;
import extra.Pan;
import extra.Vol;
import font.Fuente;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SoundPanel extends JPanel {

    private final int ancho = 500;
    private final int alto = 100;
    private ImageIcon iimute, iimute_pressed, iisolo, iisolo_pressed, iipatron,
            iipatron_pressed, iipatron_relased, iisound, iisound_pressed,
            iiselected, iiselected_none;
    private JToggleButton[] btnpatron;
    private JToggleButton btnselected, btnmute, btnsolo;
    private String nombre;
    private Colores colores;
    private Fuente fuente;
    private BtnSound btnSound;
    private Vol volumen;
    private Pan paneo;

    public SoundPanel(String nombre) throws FontFormatException, IOException {
        this.nombre = nombre;
        this.setSize(ancho, alto);
        this.setLayout(null);

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
            btnpatron[i] = new JToggleButton();
            btnpatron[i].setIcon(iipatron);
            btnpatron[i].setSelectedIcon(iipatron_relased);
            btnpatron[i].setBorder(null);
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
}
