package extra;

import colores.Colores;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Vol extends JPanel {

    private final int ancho = 52;
    private final int alto = 12;
    private Colores colores;
    private int valor, pos_clicked, pos_pressed;

    public Vol() {
        this.setSize(ancho, alto);
        colores = new Colores();
        this.setBackground(colores.getSoundPanel());
        valor = 0;
        pos_clicked = 0;
        pos_pressed = 0;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pos_clicked = e.getX();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colores.getVolPan());

        // Dibuja el volumen
        g.drawRect(0, 0, 50, 10);
        g.fillRect(0, 0, 40, 10);
    }

}
