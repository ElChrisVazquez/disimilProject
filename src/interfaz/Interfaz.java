package interfaz;

import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import colores.Colores;// Importa clase de colores
import secciones.Controlbar;
import secciones.Titlebar;// Importa el titlebar

public class Interfaz extends JFrame {

    private final int ancho = 1366;
    private final int alto = 768;
    private Colores colores;
    private ImageIcon iilogo;
    private JLabel lblogo;
    
    private Titlebar titlebar;
    private Controlbar controlbar;

    public Interfaz() throws HeadlessException, FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        this.setUndecorated(true);

        //inicializa  clase colores
        colores = new Colores();

        //Pinta el fondo del frame
        this.getContentPane().setBackground(colores.getBackground());
        
        // Carga logotipo
        iilogo = new ImageIcon("src/img/logo.png");
        
        // Inicializa logotipo
        lblogo = new JLabel(iilogo);
        lblogo.setBounds(ancho-350, alto-219, 350, 219);
        
        // Inicializa la barra de titulo
        titlebar = new Titlebar(ancho, "Sin titulo");
        titlebar.setLocation(0, 0);
        
        //Inicializa la barra de controles
        controlbar = new Controlbar();
        controlbar.setLocation(4, 55);

        this.add(lblogo);
        this.add(controlbar);
        this.add(titlebar);
        
        setVisible(true);
    }

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz();
    }
}
