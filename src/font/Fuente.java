package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fuente {
    
    private Font font;

    /**
     * Crea fuente base
     * @throws FontFormatException
     * @throws IOException 
     */
    public Fuente() throws FontFormatException, IOException {
        this.font = font;
        font = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/uni.ttf")).deriveFont(15f);
    }

    /**
     * Función getFont()
     * @return font
     */
    public Font getFont() {
        return font;
    }
}
