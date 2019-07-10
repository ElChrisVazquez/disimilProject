package secciones;

import colores.Colores;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Titlebar extends JPanel {

    private final int alto = 35;
    private ImageIcon iiIco, iiCerrarNormal, iiCerrarHover, iiMinNormal, iiMinHover;
    private JLabel lbIco;
    private JButton btnCerrar, btnMin;
    private Colores colores;

    public Titlebar(int ancho, String titulo) {
        this.setSize(ancho, alto);
        this.setLayout(null);
        
        colores = new Colores();
        this.setBackground(colores.getBackground());
        
        iiIco = new ImageIcon("src/img/icono_titulo.png");
        iiCerrarNormal = new ImageIcon("src/img/btnCerrarNormal.png");
        iiCerrarHover = new ImageIcon("src/img/btnCerrarHover.png");
        iiMinHover = new ImageIcon("src/img/btnMinimizarHover.png");
        iiMinHover = new ImageIcon("src/img/btnMinimizarNormal.png");
        
        lbIco = new JLabel(iiIco);
        lbIco.setLocation(2, 2);
        lbIco.setSize(45, 47);
        
        this.add(lbIco);
    }
}
