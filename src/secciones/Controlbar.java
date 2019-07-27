package secciones;

import colores.Colores;
import font.Fuente;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Controlbar extends JPanel {

    private final int alto = 35;
    private final int ancho = 550;
    private ImageIcon iinuevo, iinuevo_hover, iiabrir, iiabrir_hover, iiguardar,
            iiguardar_hover, iihelp, iiplay, iiplay_hover, iistop, iistop_hover,
            iitime, iimetro, iimetro_selected, iiloop, iiloop_selected;
    private JButton btnnuevo, btnabrir, btnguardar, btnplay, btnstop;
    private JToggleButton btnmetro, btnloop;
    private boolean btnplay_status;
    private JLabel lbhelp, lbtime, lbbmp, lbvalor_bpm, lbvalor_tiempo;
    private double bpm, tiempo;
    private Fuente fuente;
    private Colores colores;
    private Timer timerbpm;
    private int bpm_pressed, bpm_clicked;
    private ActionListener al_pop_bpm;

    private JPopupMenu pop_bpm;
    private JMenuItem jmi80, jmi90, jmi100, jmi110, jmi120;

    public Controlbar() throws FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setLayout(null);

        // Inicializa nativas
        bpm = 120.00;
        tiempo = 00.00;
        bpm_clicked = 0;
        bpm_pressed = 0;

        // Inicializa colores
        colores = new Colores();

        // Inicializa fuente
        fuente = new Fuente();

        // Inicializa imagenes
        iinuevo = new ImageIcon("src/img/btn_nuevo.png");
        iinuevo_hover = new ImageIcon("src/img/btn_nuevo_hover.png");
        iiabrir = new ImageIcon("src/img/btn_abrir.png");
        iiabrir_hover = new ImageIcon("src/img/btn_abrir_hover.png");
        iiguardar = new ImageIcon("src/img/btn_guardar.png");
        iiguardar_hover = new ImageIcon("src/img/btn_guardar_hover.png");
        iihelp = new ImageIcon("src/img/barra_help.png");
        iiplay = new ImageIcon("src/img/btn_play.png");
        iiplay_hover = new ImageIcon("src/img/btn_play_hover.png");
        iistop = new ImageIcon("src/img/btn_stop.png");
        iistop_hover = new ImageIcon("src/img/btn_stop_hover.png");
        iitime = new ImageIcon("src/img/barra_time.png");
        iimetro = new ImageIcon("src/img/btn_metro.png");
        iimetro_selected = new ImageIcon("src/img/btn_metro_selected.png");
        iiloop = new ImageIcon("src/img/btn_loop.png");
        iiloop_selected = new ImageIcon("src/img/btn_loop_selected.png");

        // Inicializa label
        lbhelp = new JLabel(iihelp);
        lbtime = new JLabel(iitime);
        lbvalor_tiempo = new JLabel(String.format("%.02f", tiempo));
        lbvalor_tiempo.setFont(fuente.getFontbpm());
        lbvalor_tiempo.setForeground(Color.white);
        lbbmp = new JLabel(iitime);
        lbvalor_bpm = new JLabel(String.format("%.02f", bpm));
        lbvalor_bpm.setFont(fuente.getFontbpm());
        lbvalor_bpm.setForeground(Color.white);

        // Estado del botpon de play
        btnplay_status = false;

        //Inicializa los botones
        btnnuevo = new JButton(iinuevo);
        btnnuevo.setBorder(null);
        btnabrir = new JButton(iiabrir);
        btnabrir.setBorder(null);
        btnguardar = new JButton(iiguardar);
        btnguardar.setBorder(null);
        btnplay = new JButton(iiplay);
        btnplay.setBorder(null);
        btnstop = new JButton(iistop);
        btnstop.setBorder(null);
        btnmetro = new JToggleButton(iimetro);
        btnmetro.setSelectedIcon(iimetro_selected);
        btnmetro.setBorder(null);
        btnloop = new JToggleButton(iiloop);
        btnloop.setSelectedIcon(iiloop_selected);
        btnloop.setBorder(null);

        // Inicializa el popmenu
        pop_bpm = new JPopupMenu();
        jmi100 = new JMenuItem("100 bpm", 0);
        jmi100.setName("100.00");
        jmi110 = new JMenuItem("110 bpm", 0);
        jmi110.setName("110.00");
        jmi120 = new JMenuItem("120 bpm", 0);
        jmi120.setName("120.00");
        jmi80 = new JMenuItem("80 bpm", 0);
        jmi80.setName("80.00");
        jmi90 = new JMenuItem("90 bpm", 0);
        jmi90.setName("90.00");
        pop_bpm.add(jmi120);
        pop_bpm.add(jmi110);
        pop_bpm.add(jmi100);
        pop_bpm.add(jmi90);
        pop_bpm.add(jmi80);

        // Inicializa el action para los pop del bpm
        al_pop_bpm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JMenuItem jmenu = (JMenuItem) ae.getSource();
                bpm = Double.valueOf(jmenu.getName());
                System.out.println(bpm);
                lbvalor_bpm.setText(String.format("%.02f", bpm));
                lbvalor_bpm.repaint();
            }
        };

        // Asigna tamaño, y posicion de botones
        btnnuevo.setBounds(0, 0, 30, 35);
        btnabrir.setBounds(30, 0, 30, 35);
        btnguardar.setBounds(60, 0, 30, 35);

        lbhelp.setBounds(90, 0, 140, 35);

        btnplay.setBounds(230, 0, 55, 35);
        btnstop.setBounds(285, 0, 45, 35);

        lbtime.setBounds(330, 0, 80, 35);

        lbvalor_tiempo.setBounds(360, 0, 80, 35);

        lbbmp.setBounds(410, 0, 80, 35);

        lbvalor_bpm.setBounds(420, 0, 80, 35);

        btnmetro.setBounds(490, 0, 30, 35);

        btnloop.setBounds(520, 0, 30, 35);

        // Creación de timer
        timerbpm = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bpm_pressed = (int) MouseInfo.getPointerInfo().getLocation().getY();
                if (bpm_clicked > bpm_pressed) {
                    if (bpm < 141) {
                        bpm++;
                    }
                } else {
                    if (bpm > 49) {
                        bpm--;
                    }
                }
                lbvalor_bpm.setText(String.format("%.02f", bpm));
            }
        });

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

        // Crea evento bpm
        lbbmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bpm_clicked = (int) MouseInfo.getPointerInfo().getLocation().getY();
                timerbpm.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                timerbpm.stop();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    pop_bpm.show(lbbmp, e.getX(), e.getY());
                }
            }

        });

        // Crea los eventos para el pop
        jmi100.addActionListener(al_pop_bpm);
        jmi110.addActionListener(al_pop_bpm);
        jmi120.addActionListener(al_pop_bpm);
        jmi90.addActionListener(al_pop_bpm);
        jmi80.addActionListener(al_pop_bpm);

        // Agrega los componenetes
        this.add(pop_bpm);
        this.add(btnloop);
        this.add(btnmetro);
        this.add(lbvalor_bpm);
        this.add(lbbmp);
        this.add(lbvalor_tiempo);
        this.add(lbtime);
        this.add(btnstop);
        this.add(btnplay);
        this.add(lbhelp);
        this.add(btnguardar);
        this.add(btnabrir);
        this.add(btnnuevo);
    }

    public double getBpm() {
        return bpm;
    }

    public JToggleButton getBtnmetro() {
        return btnmetro;
    }

    public JToggleButton getBtnloop() {
        return btnloop;
    }

    public JButton getBtnplay() {
        return btnplay;
    }

    public JButton getBtnnuevo() {
        return btnnuevo;
    }

    public JButton getBtnabrir() {
        return btnabrir;
    }

    public JButton getBtnguardar() {
        return btnguardar;
    }

    public JButton getBtnstop() {
        return btnstop;
    }

    public boolean isBtnplay_status() {
        return btnplay_status;
    }

    public void changeStop() {
        btnplay_status = false;
        btnplay.setIcon(iiplay);
    }

    public JLabel getLbvalor_tiempo() {
        return lbvalor_tiempo;
    }

    public void establecerBPM(Double bpm_otigen) {
        bpm = bpm_otigen;
        lbvalor_bpm.setText(String.format("%.02f", bpm));
    }

}
