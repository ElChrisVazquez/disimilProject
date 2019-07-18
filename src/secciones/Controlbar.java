package secciones;

import colores.Colores;
import font.Fuente;
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
import javax.swing.JPanel;
import javax.swing.Timer;

public class Controlbar extends JPanel {

    private final int alto = 35;
    private final int ancho = 550;
    private ImageIcon iinuevo, iinuevo_hover, iiabrir, iiabrir_hover, iiguardar,
            iiguardar_hover, iihelp, iiplay, iiplay_hover, iistop, iistop_hover,
            iitime, iimetro, iimetro_hover, iiloop, iiloop_hover;
    private JButton btnnuevo, btnabrir, btnguardar, btnplay, btnstop, btnmetro,
            btnloop;
    private boolean btnplay_status;
    private JLabel lbhelp, lbtime, lbbmp, lbvalor_bpm, lbvalor_tiempo;
    private double bpm, tiempo;
    private Fuente fuente;
    private Colores colores;
    private Timer timerbpm;
    private int bpm_pressed, bpm_clicked;

    public Controlbar() throws FontFormatException, IOException {
        this.setSize(ancho, alto);
        this.setLayout(null);
        
        // Inicializa nativas
        bpm = 120.00;
        tiempo = 0;
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
        lbvalor_tiempo = new JLabel(String.format("%.02f", tiempo));
        lbvalor_tiempo.setFont(fuente.getFontbpm());
        lbvalor_tiempo.setForeground(colores.getTextColor());
        lbbmp = new JLabel(iitime);
        lbvalor_bpm = new JLabel(String.format("%.02f", bpm));
        lbvalor_bpm.setFont(fuente.getFontbpm());
        lbvalor_bpm.setForeground(colores.getTextColor());

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
        btnmetro = new JButton(iimetro);
        btnmetro.setBorder(null);
        btnloop = new JButton(iiloop);
        btnloop.setBorder(null);

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
                bpm_pressed =  (int) MouseInfo.getPointerInfo().getLocation().getY();
                if(bpm_clicked>bpm_pressed){
                    if(bpm<141){
                        bpm++;
                    }
                }else{
                    if(bpm>49){
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
        
        
        
        
        // Crea evento bpm
        lbbmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                bpm_clicked =  (int) MouseInfo.getPointerInfo().getLocation().getY();
                timerbpm.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                timerbpm.stop();
            }
            
            
        });

        // Agrega los componenetes
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

}
