package entities;

import utilities.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.CrystalConstants.*;

public class Crystal extends Enitity{
    private BufferedImage[] animation;
    private int crystalAction = IDLE;
    private boolean isAttacked = false;
    private int xOff = 0;
    private int yOff = 47*2;
    public Crystal(float x, float y, int width, int height){
        super(x,y,width,height);
        loadSprite();
    }

    private void loadSprite() {
        BufferedImage image = LoadSave.PlayerSprite(LoadSave.CRYSTAL_SPRITE);
        animation = new BufferedImage[3];
        for (int col = 0; col < animation.length; col++) {
            animation[col] = image.getSubimage(col*37,0,37,72);
        }
    }
    public void render(Graphics g){
        g.drawImage(animation[crystalAction],(int)x,(int)y, width,height,null);
        drawHitBox(g);
        drawSmallHitBox(g);
    }
    protected void drawSmallHitBox(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect((int)x+xOff,(int)y+yOff,37*2,50);
//        System.out.println(x);
    }
    public Rectangle getBounds(){
        Rectangle CHB = new Rectangle((int)x+xOff,(int)y+yOff,74,50);
        return CHB;
    }

    public void update(){
        selectAnimation();
    }

    private void selectAnimation() {
//        int startAnimation = crystalAction;
//        if(isAttacked) {
//            crystalAction = TAKE_DAMAGE;
//        } else {
//            crystalAction = IDLE;
//        }
    }

}
