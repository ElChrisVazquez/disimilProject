package extra;

import colores.Colores;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Pan extends JPanel{
    private final int ancho = 52;
    private final int alto = 12;
    private Colores colores;

    public Pan() {
        this.setSize(ancho, alto);
        colores = new Colores();
        this.setBackground(colores.getSoundPanel());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colores.getVolPan());
       
        //Dibuja el pan primer modulo
        g.drawRect(0, 0, 25, 10);
        
        // Dibuja el pan segundo modulo
        g.drawRect(25, 0, 25, 10);
        
    }
    
    
    
}
