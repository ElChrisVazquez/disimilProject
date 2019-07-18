package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fuente {
    
    private Font font, fontbpm;

    /**
     * Crea fuente base
     * @throws FontFormatException
     * @throws IOException 
     */
    public Fuente() throws FontFormatException, IOException {
        font = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/uni.ttf")).deriveFont(10f);
        fontbpm = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/uni.ttf")).deriveFont(16f);
    }

    /**
     * Función getFont()
     * @return font
     */
    public Font getFont() {
        return font;
    }

    public Font getFontbpm() {
        return fontbpm;
    }
    
}
