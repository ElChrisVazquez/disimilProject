package interfaz;

import colores.Colores;// Importa clase de colores
import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import secciones.Titlebar;

public class Interfaz extends JFrame {

    private final int ancho = 1440;
    private final int alto = 900;
    private Colores colores;
    private JMenuBar jmbBarra;
    private JMenu jmArchivo;
    private JMenuItem jmiAbrir, jmiGuardar, jmiExportar, jmiCerrrar;

    private Titlebar titlebar;

    public Interfaz() throws HeadlessException, FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);

        //inicializa  clase colores
        colores = new Colores();

        //Pinta el fondo del frame
        this.getContentPane().setBackground(colores.getBackground());

        titlebar = new Titlebar(ancho, "");
        titlebar.setLocation(0, 0);

        //Cambia los colores de la barra de menu
//        UIManager.put("Menu.background", colores.getBarColor());
        UIManager.put("MenuBar.background", colores.getBarChild());
        UIManager.put("Menu.foreground", colores.getTextColor());
        UIManager.put("MenuBar.border", false);
//        UIManager.put("Menu.font", fuente.getFont());
//        UIManager.put("MenuItem.font", fuente.getFont());

        UIManager.put("MenuItem.background", colores.getBarChild());
        UIManager.put("MenuItem.foreground", colores.getTextColor());
        UIManager.put("MenuItem.selectionBackground", colores.getTextColor());
        UIManager.put("Menu.borderPainted", false);
        UIManager.put("MenuItem.borderPainted", false);

        //Crea barra de menu
        jmbBarra = new JMenuBar();
        jmbBarra.setBounds(0, 0, 200, 35);
        jmArchivo = new JMenu("Archivo");
        jmiAbrir = new JMenuItem("Abrir");
        jmiCerrrar = new JMenuItem("Cerrar");
        jmiExportar = new JMenuItem("Exportar");
        jmiGuardar = new JMenuItem("Guardar");
        jmbBarra.add(jmArchivo);
        jmArchivo.add(jmiAbrir);
        jmArchivo.add(jmiGuardar);
        jmArchivo.add(jmiExportar);
        jmArchivo.add(jmiCerrrar);

//        add(jmbBarra);
        add(titlebar);
        setVisible(true);
    }

    public static void main(String[] args) throws HeadlessException, FontFormatException, IOException {
        Interfaz interfaz = new Interfaz();
    }
}
