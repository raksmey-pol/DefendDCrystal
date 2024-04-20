package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static String PLAYER_SPRITE = "player_ani.png";
    public static String BG_SPRITE = "bg_grass.png";
    public static String PROP_SPRITE = "prop_decor.png";
    public static String CRYSTAL_SPRITE = "crystal.png";
    public static BufferedImage PlayerSprite(String fileName) {
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName);

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }
}
