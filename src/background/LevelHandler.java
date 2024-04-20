package background;

import main.Game;
import utilities.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {
    private Game game;
    private BufferedImage backgroundSprite,propSprite;
    public LevelHandler(Game game) {
        this.game = game;
        backgroundSprite = LoadSave.PlayerSprite(LoadSave.BG_SPRITE);
        propSprite = LoadSave.PlayerSprite(LoadSave.PROP_SPRITE);
    }

    public void draw(Graphics g){
        for (int row = 0; row < 5; row++){
            for(int col=0; col < 4; col++){
                g.drawImage(backgroundSprite,row*256,col*256,null);
            }
        }
        g.drawImage(propSprite.getSubimage(353, 269, 94, 72), 540, 352, 188, 144, null);



    }

    public void update(){

    }
}
