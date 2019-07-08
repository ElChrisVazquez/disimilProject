package interfaz;

import colores.Colores;// Importa clase de colores
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Interfaz extends JFrame{
    
    private Colores colores;
    private JMenuBar jmbBarra;
    private JMenu jmArchivo;

   public Interfaz() throws HeadlessException {
       setSize(1440, 900);
       setDefaultCloseOperation(3);
       setLocationRelativeTo(null);
       setLayout(null);
       
       //inicializa  clase colores
       colores  = new Colores();
       
       //Pinta el fondo del frame
       getContentPane().setBackground(colores.getColorFondo());
       
       jmbBarra  = new JMenuBar();
       jmbBarra.setBounds(0, 0, 200, 35);
       jmbBarra.setOpaque(false);
       jmArchivo = new JMenu("Archivo");
       jmbBarra.add(jmArchivo);
       
       
       add(jmbBarra);
       setVisible(true);
    }
    
   
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }
}
