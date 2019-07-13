package secciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Controlbar extends JPanel {

    private final int alto = 35;
    private final int ancho = 550;
    private ImageIcon iinuevo, iinuevo_hover, iiabrir, iiabrir_hover, iiguardar,
            iiguardar_hover, iihelp, iiplay, iiplay_hover, iistop, iistop_hover,
            iitime, iimetro, iimetro_hover, iiloop, iiloop_hover;
    private JButton btnnuevo, btnabrir, btnguardar, btnplay, btnstop, btnmetro,
            btnloop;
    private boolean btnplay_status;
    private JLabel lbhelp, lbtime, lbbmp;

    public Controlbar() {
        this.setSize(ancho, alto);
        this.setLayout(null);

        // Inicializa imagenes
        iinuevo = new ImageIcon("src/img/btn_nuevo.png");
        iinuevo_hover = new ImageIcon("src/img/btn_nuevo_hover.png");
        iiabrir = new ImageIcon("src/img/btn_abrir.png");
        iiabrir_hover = new ImageIcon("src/img/btn_abrir_hover.png");
        iiguardar = new ImageIcon("src/img/btn_guardar.png");
        iiguardar_hover = new ImageIcon("src/img/btn_guardar_hover.png");
        iihelp  = new ImageIcon("src/img/barra_help.png");
        iiplay = new ImageIcon("src/img/btn_play.png");
        iiplay_hover = new ImageIcon("src/img/btn_play_hover.png");
        iistop = new ImageIcon("src/img/btn_stop.png");
        iistop_hover = new ImageIcon("src/img/btn_stop_hover.png");
        iitime = new ImageIcon("src/img/barra_time.png");
        iimetro = new ImageIcon("src/img/btn_metro.png");
        iimetro_hover = new ImageIcon("src/img/btn_metro_hover.png");
        iiloop = new ImageIcon("src/img/btn_loop.png");
        iiloop_hover = new ImageIcon("src/img/btn_loop_hover.png");

        // Inicializa label
        lbhelp =  new JLabel(iihelp);
        lbtime = new JLabel(iitime);
        lbbmp = new JLabel(iitime);

        // Estado del botpon de play
        btnplay_status = false;

        //Inicializa los botones
        btnnuevo = new JButton(iinuevo);
        btnabrir = new JButton(iiabrir);
        btnguardar = new JButton(iiguardar);
        btnplay = new JButton(iiplay);
        btnstop = new JButton(iistop);
        btnmetro = new JButton(iimetro);
        btnloop = new JButton(iiloop);

        // Asigna tamaño, y posicion de botones
        btnnuevo.setBounds(0, 0, 30, 35);
        btnabrir.setBounds(30, 0, 30, 35);
        btnguardar.setBounds(60, 0, 30, 35);
        
        lbhelp.setBounds(90, 0, 140, 35);
        
        btnplay.setBounds(230, 0, 55, 35);
        btnstop.setBounds(285, 0, 45, 35);

        lbtime.setBounds(330, 0, 80, 35);
        lbbmp.setBounds(410, 0, 80, 35);

        btnmetro.setBounds(490, 0, 30, 35);
        btnloop.setBounds(520, 0, 30, 35);

        // Crea eventos hover
        btnnuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnnuevo.setIcon(iinuevo_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnnuevo.setIcon(iinuevo);
            }
        });
        btnabrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnabrir.setIcon(iiabrir_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnabrir.setIcon(iiabrir);
            }
        });

        btnguardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnguardar.setIcon(iiguardar_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnguardar.setIcon(iiguardar);
            }
        });

        btnstop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnstop.setIcon(iistop_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnstop.setIcon(iistop);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (btnplay_status) {
                    btnplay_status = false;
                    btnplay.setIcon(iiplay);
                }
            }
        });

        btnplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!btnplay_status) {
                    btnplay_status = true;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btnplay_status) {
                    btnplay.setIcon(iiplay_hover);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btnplay_status) {
                    btnplay.setIcon(iiplay);
                }
            }

        });

        btnmetro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnmetro.setIcon(iimetro_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnmetro.setIcon(iimetro);
            }
        });

        btnloop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnloop.setIcon(iiloop_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnloop.setIcon(iiloop);
            }
        });

        // Agrega los componenetes
        this.add(btnloop);
        this.add(btnmetro);
        this.add(lbbmp);
        this.add(lbtime);
        this.add(btnstop);
        this.add(btnplay);
        this.add(lbhelp);
        this.add(btnguardar);
        this.add(btnabrir);
        this.add(btnnuevo);
    }

}
