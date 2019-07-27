package interfaz;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Abrir extends JFrame {

    private ImageIcon iiback, iilogo, iiminimizar, iicerrar, iicerrar_hover,
            iiminimizar_hover, iico;
    private JLabel lblogo, lbeseleccione, lbback;
    private JRadioButton rbabrir, rbnuevo;
    private JButton btncontinuar, btnsalir, btnmin, btncerrar;
    private ButtonGroup bg;
    private JFileChooser jfc_abrir, jfc_nuevo;
    private FileNameExtensionFilter filter;
    private ArrayList<String> lista;

    public Abrir() throws HeadlessException {
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(3);

        // Inicializa las imagenes
        iico = new ImageIcon("src/img/icono.png");
        this.setIconImage(iico.getImage());

        iiback = new ImageIcon("src/img/biblioteca.gif");
        iicerrar = new ImageIcon("src/img/btn_cerrar.png");
        iicerrar_hover = new ImageIcon("src/img/btn_cerrar_hover.png");
        iiminimizar = new ImageIcon("src/img/btn_min.png");
        iiminimizar_hover = new ImageIcon("src/img/btn_min_hover.png");
        iilogo = new ImageIcon("src/img/logo.png");

        // Inicializa los labels
        lbback = new JLabel(iiback);
        lbeseleccione = new JLabel("Seleccione una opción: ");
        lblogo = new JLabel(iilogo);

        // Inicializa botones
        btncerrar = new JButton(iicerrar);
        btnmin = new JButton(iiminimizar);
        btncontinuar = new JButton("Continuar");
        btnsalir = new JButton("Salir");

        // Inicializa los rb
        rbabrir = new JRadioButton("Abrir un proyecto existente");
        rbnuevo = new JRadioButton("Crear un proyecto nuevo");
        bg = new ButtonGroup();
        bg.add(rbabrir);
        bg.add(rbnuevo);

        // Inicializa jfile
        jfc_abrir = new JFileChooser();
        jfc_abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filter = new FileNameExtensionFilter("dP Project", "pdr");
        jfc_abrir.setFileFilter(filter);
        jfc_nuevo = new JFileChooser();
        jfc_nuevo.setDialogTitle("Seleccione la ruta de la biblioteca");
        jfc_nuevo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Inicializa lista
        lista = new ArrayList<>();

        // Posicion
        lbback.setBounds(0, 0, 500, 500);
        btncerrar.setBounds(465, 20, 15, 15);
        btnmin.setBounds(445, 20, 15, 15);
        rbabrir.setBounds(150, 230, 200, 35);
        rbabrir.setOpaque(false);
        rbabrir.setForeground(Color.white);
        rbnuevo.setBounds(150, 260, 200, 35);
        rbnuevo.setOpaque(false);
        rbnuevo.setForeground(Color.white);
        btncontinuar.setBounds(220, 400, 90, 25);
        btnsalir.setBounds(320, 400, 90, 25);

        // Mouse listener
        btncerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btncerrar.setIcon(iicerrar_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btncerrar.setIcon(iicerrar);
            }
        });

        btnmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnmin.setIcon(iiminimizar_hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnmin.setIcon(iiminimizar);
            }
        });

        // Action Listener botones
        btncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

        btnsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btncontinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rbabrir.isSelected() && !rbnuevo.isSelected()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Selecione una acción.",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else if (rbabrir.isSelected()) {
                    int resp = jfc_abrir.showDialog(null, "Abrir");
                    if (resp == JFileChooser.APPROVE_OPTION) {
                        try {
                            lista = (ArrayList<String>) Files.readAllLines(
                                    Paths.get(jfc_abrir.getSelectedFile().getAbsolutePath()));
                            File archivo = new File(lista.get(0));
                            Interfaz abrir = new Interfaz(
                                    lista.get(1),
                                    archivo.getName()
                            );
                            abrir.openNew(jfc_abrir.getSelectedFile());
                            dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (HeadlessException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FontFormatException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    int resp2 = jfc_nuevo.showDialog(null, "Seleccionar");
                    if (resp2 == JFileChooser.APPROVE_OPTION) {
                        try {
                            Interfaz nueva = new Interfaz(
                                    jfc_nuevo.getSelectedFile().getAbsolutePath(),
                                    "Sin-Titulo"
                            );
                            dispose();
                        } catch (HeadlessException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FontFormatException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

        //Añade componentes
        this.add(btncontinuar);
        this.add(btnsalir);
        this.add(rbabrir);
        this.add(rbnuevo);
        this.add(btnmin);
        this.add(btncerrar);
        this.add(lbback);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Abrir abrir = new Abrir();
    }

}
