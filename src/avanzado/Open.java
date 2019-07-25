package avanzado;

import interfaz.Interfaz;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Open {

    private ArrayList<String> lista;

    public Open(File entrada, Interfaz intefaz) throws IOException {
        lista = (ArrayList<String>) Files.readAllLines(
                Paths.get(entrada.getAbsolutePath()));
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

}
