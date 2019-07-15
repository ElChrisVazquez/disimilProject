package botones;

import colores.Colores;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class BtnSound extends JToggleButton {

    private ImageIcon iisound, iisound_relased;
    private JLabel lbnombre;
    private Colores colores;

    public BtnSound(ImageIcon iisound, ImageIcon iisound_relased, String nombre) {
        this.iisound = iisound;
        this.iisound_relased = iisound_relased;
        this.setLayout(null);
        this.setIcon(iisound);
        this.setSelectedIcon(iisound_relased);
        this.setBorder(null);
        
        // Inicializa colores
        colores = new Colores();
        
        lbnombre = new JLabel(nombre);
        lbnombre.setBounds(8, 0, 70, 30);
        lbnombre.setForeground(colores.getTextColor());
        
        this.add(lbnombre);
    }
}
