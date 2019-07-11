package secciones;

import colores.Colores;
import font.Fuente;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Titlebar extends JPanel {

    private final int alto = 80;
    private ImageIcon iiico, iicerrar, iicerrar_hover, iimin, iimin_hover;
    private JLabel lbico, lbtitle;
    private JButton btncerrar, btnmin;
    private Colores colores;
    private Fuente fuente;
    private JMenuBar jmbbarra;
    private JMenu jmarchivo;
    private JMenuItem jmiabrir, jmiguardar, jmiexportar, jmicerrrar;

    public Titlebar(int ancho, String titulo) throws FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setLayout(null);

        //Colores
        colores = new Colores();
        this.setBackground(colores.getBarColor());

        // Imagenes para logo mini
        iiico = new ImageIcon("src/img/icono_titulo.png");
        
        // Label para icono pequeño
        lbico = new JLabel(iiico);
        lbico.setLocation(-10, -10);
        lbico.setSize(45, 47);

        // Fuente
        fuente = new Fuente();

        // Label para el titulo
        lbtitle = new JLabel(titulo + " - disímil PROJECT");
        lbtitle.setLocation(25, -8);
        lbtitle.setSize(500, 45);
        lbtitle.setFont(fuente.getFont());
        lbtitle.setForeground(colores.getTextColor());

        // Imagenes para boton cerrar y minimizar
        iicerrar = new ImageIcon("src/img/btn_cerrar.png");
        iicerrar_hover = new ImageIcon("src/img/btn_cerrar_hover.png");
        iimin = new ImageIcon("src/img/btn_min.png");
        iimin_hover = new ImageIcon("src/img/btn_min_hover.png");

        // Inicializa boton cerrar
        btncerrar = new JButton(iicerrar);
        btncerrar.setBounds(ancho - 30, 5, 20, 20);
        
        // Cambia imagen con hover
        btncerrar.addMouseListener(new MouseAdapter() {
            
            // Si el mouse entra al botón
            @Override
            public void mouseEntered(MouseEvent e) {
                btncerrar.setIcon(iicerrar_hover);
            }

            // Si el mouse sale del botón
            @Override
            public void mouseExited(MouseEvent e) {
                btncerrar.setIcon(iicerrar);
            }
        });
        
        // Inicializa boton minimizar
        btnmin = new JButton(iimin);
        btnmin.setBounds(ancho-55, 5, 20, 20);
        
        // Cambia imagen con hover
        btnmin.addMouseListener(new MouseAdapter() {
            
            // Si el mouse entra al botón
            @Override
            public void mouseEntered(MouseEvent e) {
                btnmin.setIcon(iimin_hover);
            }

            // Si el mouse sale del botón
            @Override
            public void mouseExited(MouseEvent e) {
                btnmin.setIcon(iimin);
            }
        });

        // Cambia los colores de la barra de menu
        UIManager.put("MenuBar.background", colores.getBarColor());
        UIManager.put("Menu.foreground", colores.getTextColor());
        UIManager.put("MenuBar.border", false);
        UIManager.put("Menu.font", fuente.getFont());
        UIManager.put("MenuItem.font", fuente.getFont());

        UIManager.put("MenuItem.background", colores.getBarChild());
        UIManager.put("MenuItem.foreground", colores.getTextColor());
        UIManager.put("MenuItem.selectionBackground", colores.getTextColor());
        UIManager.put("Menu.borderPainted", false);
        UIManager.put("MenuItem.borderPainted", false);

        //Crea barra de menu
        jmbbarra = new JMenuBar();
        jmbbarra.setBounds(0, 25, 200, 30);
        jmarchivo = new JMenu("Archivo");
        jmiabrir = new JMenuItem("Abrir");
        jmicerrrar = new JMenuItem("Cerrar");
        jmiexportar = new JMenuItem("Exportar");
        jmiguardar = new JMenuItem("Guardar");
        jmbbarra.add(jmarchivo);
        jmarchivo.add(jmiabrir);
        jmarchivo.add(jmiguardar);
        jmarchivo.add(jmiexportar);
        jmarchivo.add(jmicerrrar);

        this.add(lbico);
        this.add(lbtitle);
        this.add(btncerrar);
        this.add(btnmin);
        this.add(jmbbarra);
    }
}
