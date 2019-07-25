package avanzado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import secciones.SoundPanel;

public class Save {
    private ArrayList<SoundPanel> lista;
    private PrintWriter pw;
    private File salida;

    /**
     * 
     * @param path_origen Path de origen
     * @param path_arbol Path del arbol
     * @param bpm
     * @param metro
     * @param loop
     * @param sonidos
     * @param lista
     * @throws FileNotFoundException 
     */
    public Save(
            String path_origen,
            String path_arbol,
            Double bpm,
            boolean metro,
            boolean loop,
            int sonidos,
            ArrayList<SoundPanel> lista
    ) throws FileNotFoundException {

        salida = new File(path_origen);
        pw = new PrintWriter(salida);
        pw.write(path_origen + "\n"); // Path origen
        pw.write(path_arbol + "\n"); // Path arbol
        pw.write(String.valueOf(bpm)+"\n"); // BPM
        
        // Metronomo
        if(metro){
            pw.write("1\n");
        }else{
            pw.write("0\n");
        }
        
        // Loop
        if(loop){
            pw.write("1\n");
        }else{
            pw.write("0\n");
        }
        pw.write(String.valueOf(lista.size()) + "\n"); // Numero de sonidos
        for (int i = 0; i < lista.size(); i++) {
            pw.write(lista.get(i).getSound().getAbsolutePath() + "\n"); // Path del sonido
            if (lista.get(i).getBtnsolo().isSelected()) { // Boton Solo
                pw.write("1\n");
            } else {
                pw.write("0\n");
            }
            if (lista.get(i).getBtnmute().isSelected()) { // Boton mute
                pw.write("1\n");
            } else {
                pw.write("0\n");
            }
            pw.write(lista.get(i).getVolumen().getValor() + "\n"); // Volumen
            pw.write(lista.get(i).getPaneo().getValor() + "\n"); // Paneo
            
            // Patrones
            for (int j = 0; j < lista.get(i).getBtnpatron().length; j++) {
                if (lista.get(i).getBtnpatron()[j].isSelected()) {
                    pw.write("1");
                } else {
                    pw.write("0");
                }
            }
            pw.write("\n");
        }
        pw.close();
    }
}
