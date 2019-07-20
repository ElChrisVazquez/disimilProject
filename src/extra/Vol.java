package extra;

import colores.Colores;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Vol extends JPanel {

    private final int ancho = 52;
    private final int alto = 12;
    private Colores colores;
    private int valor, pos_clicked, pos_pressed;
    private Timer timer;

    public Vol() {
        this.setSize(ancho, alto);
        colores = new Colores();
        this.setBackground(colores.getSoundPanel());
        valor = 100;
        pos_clicked = 0;
        pos_pressed = 0;
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos_pressed = (int) MouseInfo.getPointerInfo().getLocation().getX();
                if (pos_clicked > pos_pressed) {
                    if (valor > 0) {
                        valor-=2;
                    }
                } else {
                    if (valor < 101) {
                        valor+=2;
                    }
                }
                repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pos_clicked = (int) MouseInfo.getPointerInfo().getLocation().getX();
                timer.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                timer.stop();
            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colores.getVolPan());

        // Dibuja el volumen
        g.drawRect(0, 0, 50, 10);
        g.fillRect(0, 0, (int)valor/2, 10);
    }

    public int getValor() {
        return valor;
    }
}
