package secciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Controlbar extends JPanel {

    private final int alto = 35;
    private final int ancho = 400;
    private ImageIcon iinuevo, iinuevo_hover, iiabrir, iiabrir_hover, iiguardar,
            iiguardar_hover;
    private JButton btnnuevo, btnabrir, btnguardar;

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

        //Iniciaaliza los botones
        btnnuevo = new JButton(iinuevo);
        btnabrir = new JButton(iiabrir);
        btnguardar = new JButton(iiguardar);

        // Asigna tamaño, y posicion de botones
        btnnuevo.setBounds(0, 0, 30, 35);
        btnabrir.setBounds(30, 0, 30, 35);
        btnguardar.setBounds(60, 0, 30, 35);

        // Crea eventos hover
        btnnuevo.addMouseListener(new MouseAdapter() {
            
            // Si el mouse entra al botón
            @Override
            public void mouseEntered(MouseEvent e) {
                btnnuevo.setIcon(iinuevo_hover);
            }

            // Si el mouse sale del botón
            @Override
            public void mouseExited(MouseEvent e) {
                btnnuevo.setIcon(iinuevo);
            }
        });
        btnabrir.addMouseListener(new MouseAdapter() {

            // Si el mouse entra al botón
            @Override
            public void mouseEntered(MouseEvent e) {
                btnabrir.setIcon(iiabrir_hover);
            }

            // Si el mouse sale del botón
            @Override
            public void mouseExited(MouseEvent e) {
                btnabrir.setIcon(iiabrir);
            }
        });
        
        btnguardar.addMouseListener(new MouseAdapter() {
            
            // Si el mouse entra al botón
            @Override
            public void mouseEntered(MouseEvent e) {
                btnguardar.setIcon(iiguardar_hover);
            }
            
            // Si el mouse sale del botón
            @Override
            public void mouseExited(MouseEvent e) {
                btnguardar.setIcon(iiguardar);
            }
        });

        // Agrega los componenetes
        this.add(btnguardar);
        this.add(btnabrir);
        this.add(btnnuevo);
    }

}
