package entities;

import java.awt.*;

public abstract class Enitity {
    protected float x,y;
    protected int width, height;
    protected Rectangle hitbox;
    public Enitity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;

        initHitBox();
    }

    protected void drawHitBox(Graphics g){
        g.setColor(Color.RED);
        g.drawRect((int)x,(int)y,width,height);
    }

    private void initHitBox() {
        hitbox = new Rectangle((int)x,(int)y, width,height);
    }
    protected void updateHitBox() {
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }
    public Rectangle setHitbox() {
        return hitbox;
    }
}
