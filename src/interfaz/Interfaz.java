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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.tools.DocumentationTool;
import secciones.Controlbar;
import secciones.PrincipalPanel;
import secciones.SoundPanel;
import secciones.Titlebar;// Importa el titlebar
import secciones.TreePanel; // Importa el panel de arbol

public class Interfaz extends JFrame {

    private final int ancho = 1000;
    private final int alto = 768;
    private File file_selected_tree, file_click_pressed;

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
        file_selected_tree = null;
        file_click_pressed = null;

        //inicializa  clase colores
        colores = new Colores();

        //Pinta el fondo del frame
        this.getContentPane().setBackground(colores.getBackground());

        // Carga logotipo
        iilogo = new ImageIcon("src/img/logo.png");

        // Carga imagen para animacion
        iiminipanel = new ImageIcon("src/img/mini.png");

        // Inicializa label para animación
        lbminipanel = new JLabel(iiminipanel);
        lbminipanel.setBounds(0, 0, 100, 15);
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
        principal = new PrincipalPanel();
        principal.setLocation(treepanel.getWidth() + 4, titlebar.getHeight());

        // Crea animación de drop&drag
        timer_minipanel = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point location_mini = MouseInfo.getPointerInfo().getLocation();
                lbminipanel.setLocation((int)(location_mini.getX()-getX()), (int)(location_mini.getY()-getY()));
                lbminipanel.setVisible(true);
                System.out.println((int)(location_mini.getX()-getX())+" ");
                System.out.print((int)(location_mini.getY()-getY()));
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
                        if(node.isFile()){
                            timer_minipanel.start();
                            lbminipanel.setVisible(true);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                timer_minipanel.stop();
                lbminipanel.setVisible(false);
                lbminipanel.setLocation(0, 0);
                treepanel.getArbol().clearSelection();
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

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz();
    }
}
