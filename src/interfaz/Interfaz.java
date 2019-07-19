package interfaz;

import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import colores.Colores;// Importa clase de colores
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
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
//        this.setUndecorated(true);

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
        lbminipanel = new JLabel(iilogo);
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

            }
        });

        // Seleccion de arbol
        treepanel.getArbol().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                TreePath tp = treepanel.getArbol().getSelectionPath();
                if (tp != null) {
                    Object filePathToAdd = tp.getLastPathComponent();
                    if (filePathToAdd instanceof File) {
                        File node = (File) filePathToAdd;
                        System.out.println(node.getAbsolutePath());
//                        if (node.isFile() && node.getPath().endsWith(".wav")) {
//                            System.out.println(node.getPath());
//                            arbol.getArbol().clearSelection();
//                        } else if (node.isDirectory()) {
//                        }
                    }
                }
            }
        });

        // Evento Arbol drag and drop
        treepanel.getArbol().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = treepanel.getArbol().getRowForLocation(e.getX(), e.getY());
                TreePath selPath = treepanel.getArbol().getPathForLocation(e.getX(), e.getY());
                treepanel.getArbol().setSelectionPath(selPath);
                if (selRow > -1) {
                    treepanel.getArbol().setSelectionRow(selRow);
                }
            }

        });

        this.add(principal);
        this.add(treepanel);
        this.add(lblogo);
        this.add(controlbar);
        this.add(titlebar);
        this.add(lbminipanel);

        setVisible(true);
    }

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz();
    }
}
