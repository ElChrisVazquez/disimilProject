package interfaz;

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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.tree.TreePath;
import secciones.Controlbar;
import secciones.PrincipalPanel;
import secciones.SoundPanel;
import secciones.Titlebar;// Importa el titlebar
import secciones.TreePanel; // Importa el panel de arbol

public class Interfaz extends JFrame {

    private final int ancho = 1000;
    private final int alto = 500;
    private File file_selected;

    private Colores colores;
    private ImageIcon iilogo, iiminipanel;
    private JLabel lblogo, lbminipanel;
    private Timer timer_minipanel;

    private Titlebar titlebar;
    private Controlbar controlbar;
    private TreePanel treepanel;
    private PrincipalPanel principal;
    private SoundPanel soundpanel;

    public Interfaz() throws HeadlessException, FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);

        // Se inicializa el file que guarda la seleccion del arbol
        file_selected = null;

        //inicializa  clase colores
        colores = new Colores();

        //Pinta el fondo del frame
        this.getContentPane().setBackground(colores.getBackground());

        // Carga logotipo
        iilogo = new ImageIcon("src/img/logo.png");

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
        treepanel = new TreePanel(alto, new File("src"));
        treepanel.setLocation(4, titlebar.getHeight());

        // Inicializa el panel principal
        principal = new PrincipalPanel(alto);
        principal.setLocation(treepanel.getWidth() + 4, titlebar.getHeight());

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

        // Evento Arbol drag and drop
        treepanel.getArbol().addMouseListener(new MouseAdapter() {
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

        this.add(lbminipanel);
        this.add(principal);
        this.add(treepanel);
        this.add(lblogo);
        this.add(controlbar);
        this.add(titlebar);

        setVisible(true);
    }

    public void playSample(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            FloatControl gain_control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain_control.setValue(0);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException exc) {
            exc.printStackTrace(System.out);
        }
    }

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
    
    public void play(ArrayList<SoundPanel> splista, int bpm, boolean metro, boolean loop){
        
    }
    
    public static void idiota(){
        System.out.println("Soy una verga");
    }

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz();
    }
}
