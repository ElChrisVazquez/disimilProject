package interfaz;

import avanzado.Save;
import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import colores.Colores;// Importa clase de colores
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.tree.TreePath;
import secciones.Controlbar;
import secciones.PrincipalPanel;
import secciones.SoundPanel;
import secciones.Titlebar;// Importa el titlebar
import secciones.TreePanel; // Importa el panel de arbol

public class Interfaz extends JFrame {

    private final int ancho = 1000;//1366
    private final int alto = 500;//768
    private final double bpm_time = 240.00;
    private double time, time_add;
    private int time_per_sample;
    private int beat = 0;
    private File file_selected;

    private Colores colores;
    private ImageIcon iilogo, iiminipanel, iiico;
    private JLabel lblogo, lbminipanel;
    private Timer timer_minipanel, timer_play;
    private JPopupMenu pop_arbol;
    private JMenuItem jmiagregar;
    private JFileChooser jfc_open, jfc_save;

    private Titlebar titlebar;
    private Controlbar controlbar;
    private TreePanel treepanel;
    private PrincipalPanel principal;
    private SoundPanel soundpanel;
    private Save guardar;

    public Interfaz(String path) throws HeadlessException, FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);

        // Inicializa nativos
        time_per_sample = 125;
        time = 0;
        time_add = 0;

        // Se inicializa el file que guarda la seleccion del arbol
        file_selected = null;

        //inicializa  clase colores
        colores = new Colores();

        //Pinta el fondo del frame
        this.getContentPane().setBackground(colores.getBackground());

        // Carga logotipo
        iilogo = new ImageIcon("src/img/logo.png");
        iiico = new ImageIcon("src/img/icono.png");
        this.setIconImage(iiico.getImage());

        // Carga imagen para animacion
        iiminipanel = new ImageIcon("src/img/mini2.png");

        // Inicializa label para animación
        lbminipanel = new JLabel(iiminipanel);
        lbminipanel.setBounds(0, 0, 75, 15);
        lbminipanel.setVisible(false); // Se oculta al inicializar

        // Inicializa logotipo
        lblogo = new JLabel(iilogo);
        lblogo.setBounds(ancho - 350, alto - 219, 350, 219);

        // Inicializa la barra de titulo
        titlebar = new Titlebar(ancho, "Sin titulo");
        titlebar.setLocation(0, 0);

        //Inicializa la barra de controles
        controlbar = new Controlbar();
        controlbar.setLocation(4, 55);

        // Inicializa el panel del arbol
        treepanel = new TreePanel(alto, new File(path));
        treepanel.setLocation(4, titlebar.getHeight());

        // Inicializa el panel principal 
        principal = new PrincipalPanel(alto, 0);
        principal.setLocation(treepanel.getWidth() + 4, titlebar.getHeight());

        // Inicializa el popmenu
        pop_arbol = new JPopupMenu();
        jmiagregar = new JMenuItem("Aregar");
        pop_arbol.add(jmiagregar);

        // Inicializa JChosse
        jfc_save = new JFileChooser();
        jfc_save.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc_open = new JFileChooser();
        jfc_open.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Crea animación de drop&drag
        timer_minipanel = new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point location_mini = MouseInfo.getPointerInfo().getLocation();
                lbminipanel.setLocation(
                        (int) (location_mini.getX() - getX()),
                        (int) (location_mini.getY() - getY())
                );
                lbminipanel.setVisible(true);
                lbminipanel.repaint();
            }
        });

        // Evento Arbol drag and drop y click derecho
        treepanel.getArbol().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = treepanel.getArbol().getClosestRowForLocation(e.getX(), e.getY());
                    treepanel.getArbol().setSelectionRow(row);
                    TreePath tp = treepanel.getArbol().getSelectionPath();
                    if (tp != null) {
                        Object filePathToAdd = tp.getLastPathComponent();
                        if (filePathToAdd instanceof File) {
                            File node = (File) filePathToAdd;
                            if (node.isFile() && node.getPath().endsWith(".wav")) {
                                pop_arbol.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                TreePath tp = treepanel.getArbol().getSelectionPath();
                if (tp != null) {
                    Object filePathToAdd = tp.getLastPathComponent();
                    if (filePathToAdd instanceof File) {
                        File node = (File) filePathToAdd;
                        if (node.isFile() && node.getPath().endsWith(".wav")) {
                            file_selected = node;
                            timer_minipanel.start();
                            lbminipanel.setVisible(true);
                            playSamplePreview(node);
                        }
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Rectangle rec_pointer = new Rectangle(
                        (int) (MouseInfo.getPointerInfo().getLocation().getX() - getX()),
                        (int) (MouseInfo.getPointerInfo().getLocation().getY() - getY()),
                        1,
                        1);
                Rectangle rec_panel = new Rectangle(principal.getBounds());
                timer_minipanel.stop();
                lbminipanel.setVisible(false);
                treepanel.getArbol().clearSelection();
                if (rec_pointer.intersects(rec_panel) && file_selected != null) {
                    try {
                        principal.create(
                                file_selected,
                                file_selected.getName()
                        );
                    } catch (FontFormatException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    file_selected = null;
                }
            }
        });

        // Timer de reproduccion
        timer_play = new Timer((int) time_per_sample, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (beat < 16) {
                    time_add += time;
                    controlbar.getLbvalor_tiempo().setText(String.format("%.02f", time_add / 1000));
                    controlbar.getLbvalor_tiempo().repaint();
                    for (int i = 0; i < principal.getSplista().size(); i++) {
                        if (principal.getSplista().get(i).getBtnpatron()[beat].isSelected()) {
                            playSample(
                                    principal.getSplista().get(i).getSound(),
                                    (int) principal.getSplista().get(i).getVolumen().getValor(),
                                    (int) principal.getSplista().get(i).getPaneo().getValor());
                        }
                        principal.getDesplazamiento()[beat].setBackground(colores.getVolPan());
                        if (beat == 0) {
                            principal.getDesplazamiento()[15].setBackground(colores.getBackRepro());
                        }
                        if (beat > 0) {
                            principal.getDesplazamiento()[beat - 1].setBackground(colores.getBackRepro());
                        }
                    }
                }
                time_per_sample = (int) (((240 / controlbar.getBpm()) * 1000) / 16);
                timer_play.setDelay(time_per_sample);
                beat++;
                // Si está desactivado el loop se detiene
                if (beat == 16) {
                    if (controlbar.getBtnloop().isSelected()) {
                        beat = 0;
                        time_add = 0;
                    } else {
                        timer_play.stop();
                        principal.getDesplazamiento()[beat - 1].setBackground(colores.getBackRepro());
                        stop();
                        time_add = 0;
                    }
                }
            }
        });

        // Actionlistener botón de play
        controlbar.getBtnplay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time_per_sample = (int) (((240 / controlbar.getBpm()) * 1000) / 16);
                time = (int) (((240 / controlbar.getBpm()) * 1000) / 16);
                timer_play.start();
            }
        });

        // Action listener de boton stop
        controlbar.getBtnstop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        // Action listener de boton nuevo
        controlbar.getBtnnuevo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!principal.getSplista().isEmpty()) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            null,
                            "Desea descartar los cambios?",
                            "Advertencia!",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (respuesta == JOptionPane.YES_OPTION) {
                        principal.deleteAll();
                    }
                }
            }
        });

        // Action listener pop agregar
        jmiagregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (file_selected != null) {
                    try {
                        principal.create(
                                file_selected,
                                file_selected.getName()
                        );
                    } catch (FontFormatException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    file_selected = null;
                }
            }
        });

        // Action de boton cerrar
        titlebar.getBtncerrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!principal.getSplista().isEmpty()) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            null,
                            "Desea guardar el progreso?",
                            "Esta apunto de salir",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (respuesta == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        // Action de boton minimizar
        titlebar.getBtnmin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(JFrame.ICONIFIED);
            }
        });

        // Action boton guardar
        controlbar.getBtnguardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!principal.getSplista().isEmpty()) {
                    int resp = jfc_save.showSaveDialog(null);
                    if (resp == JFileChooser.APPROVE_OPTION) {
                        File salida = new File(jfc_save.getSelectedFile().getAbsolutePath() + ".pdr");
                        if (salida.exists()) {
                            int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Desea remplazar el archivo existente?",
                                    "Advertencia!",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (confirm == JOptionPane.YES_OPTION) {
                                try {
                                    guardar = new Save(
                                            salida.getAbsolutePath(),
                                            path,
                                            controlbar.getBpm(),
                                            controlbar.getBtnmetro().isSelected(),
                                            controlbar.getBtnloop().isSelected(),
                                            principal.getSplista().size(),
                                            principal.getSplista()
                                    );
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            try {
                                guardar = new Save(
                                        salida.getAbsolutePath(),
                                        path,
                                        controlbar.getBpm(),
                                        controlbar.getBtnmetro().isSelected(),
                                        controlbar.getBtnloop().isSelected(),
                                        principal.getSplista().size(),
                                        principal.getSplista()
                                );
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        );

        this.add(pop_arbol);

        this.add(lbminipanel);

        this.add(principal);

        this.add(treepanel);

        this.add(lblogo);

        this.add(controlbar);

        this.add(titlebar);

        setVisible(
                true);
    }

    /**
     * Reproduce el archivo
     *
     * @param sound Archivo de sonido
     * @param vol Volumen
     * @param pan Paneo
     */
    public void playSample(File sound, int vol, int pan) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            FloatControl gain_control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            FloatControl pan_control = (FloatControl) clip.getControl(FloatControl.Type.PAN);
            pan_control.setValue((float) (pan * 0.04));
            gain_control.setValue(vol);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException exc) {
            exc.printStackTrace(System.out);
        }
    }

    /**
     * Reproduce el sonido solo para seleccionar el sonido, no inlcuye vol ni
     * pan
     *
     * @param sound Archivo de sonidos
     */
    public void playSamplePreview(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            FloatControl gain_control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain_control.setValue(0.0f);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void stop() {
        if (beat > 0) {
            principal.getDesplazamiento()[beat - 1].setBackground(colores.getBackRepro());
        }
        principal.getDesplazamiento()[15].setBackground(colores.getBackRepro());
        timer_play.stop();
        beat = 0;
        controlbar.changeStop();
    }

    public Titlebar getTitlebar() {
        return titlebar;
    }

    public Controlbar getControlbar() {
        return controlbar;
    }

    public TreePanel getTreepanel() {
        return treepanel;
    }

    public PrincipalPanel getPrincipal() {
        return principal;
    }

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz("src");
    }
}
