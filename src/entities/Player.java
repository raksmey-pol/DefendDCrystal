package entities;

import org.w3c.dom.css.Rect;
import utilities.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.Constants.Directions.*;
import static utilities.Constants.PlayerConstants.*;

public class Player extends Enitity {

    private  BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 25;
    private int playerAction = IDLE_RIGHT;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left, right, up, down;
    private final float playerSpeed = 2.0f;
    private int lastDirection = RIGHT;
    private int xOff = 30;
    private int yOff = 15;
    private Crystal crystal;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update(){
        selectAnimation();
        updatePosition();
        updateHitBox();
        updateAnimationTick();

    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex], (int)x,(int)y, width,height,null);
        drawHitBox(g);
        drawSmallHitBox(g);
    }
    protected void drawSmallHitBox(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect((int)x+xOff,(int)y+yOff,39,60);
    }
    public Rectangle getBounds(){
        Rectangle playerHitbox = new Rectangle((int)x+xOff,(int)y+yOff,39,60);
        return playerHitbox;
    }

    private void updateAnimationTick() {
        animationTick++;
        if(animationTick>= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }
    public void updatePosition() {
        moving = false;
        float newX = x;
        float newY = y;

        if (left && !right) {
            newX -= playerSpeed;
            moving = true;
        } else if (!left && right) {
            newX += playerSpeed;
            moving = true;
        }
        if (up && !down) {
            newY -= playerSpeed;
            moving = true;
        } else if (!up && down) {
            newY += playerSpeed;
            moving = true;
        }

        // Create a temporary rectangle representing the player's next position
        Rectangle playerNextPosition = new Rectangle((int) newX + xOff, (int) newY + yOff, 39, 60);

        // Check for collision with crystal
        if (playerNextPosition.intersects(crystal.getBounds())) {
            // Collision detected, do not update player position
            return;
        }

        // Update player position if no collision detected
        x = newX;
        y = newY;
    }




    //    private void updatePosition() {
//        moving = false;
//        if (left && !right) {
//            x -= playerSpeed;
//            moving = true;
//        } else if (!left && right) {
//            x += playerSpeed;
//            moving = true;
//        }
//        if (up && !down) {
//            y -= playerSpeed;
//            moving = true;
//        } else if (!up && down) {
//            y += playerSpeed;
//            moving = true;
//        }
//    }
    private boolean checkCollision() {
        return getBounds().intersects(crystal.getBounds());
    }
    public void setCrystal(Crystal crystal) {
        this.crystal = crystal;
    }
    public int setSpriteDirection() {
        if(left) {
            lastDirection = LEFT;
            return LEFT;
        }
        if (right) {
            lastDirection = RIGHT;
            return RIGHT;
        }
        if(up) {
            lastDirection = UP;
            return UP;
        }
        if(down) {
            lastDirection = DOWN;
            return DOWN;
        }
        return 0;
    }


    private void selectAnimation() {
        int startAnimation = playerAction;
        if (moving) {
            switch (setSpriteDirection()) {
                case LEFT:
                    playerAction = RUNNING_RIGHT;
                    break;
                case RIGHT:
                    playerAction = RUNNING_RIGHT;
                    break;
                case UP:
                    playerAction = RUNNING_UP;
                    break;
                case DOWN:
                    playerAction = RUNNING_DOWN;
                    break;
                default:
                    playerAction = IDLE_RIGHT;
            }
        } else {
            switch (lastDirection) {
                case LEFT:
                    playerAction = IDLE_RIGHT;
                    break;
                case RIGHT:
                    playerAction = IDLE_RIGHT;
                    break;
                case UP:
                    playerAction = IDLE_UP;
                    break;
                case DOWN:
                    playerAction = IDLE_DOWN;
                    break;
                default:
                    playerAction = IDLE_RIGHT;
            }
        }
        if (attacking) {
            playerAction = FIGHT_RIGHT;
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animationTick =0;
        animationIndex=0;
    }

    private void loadAnimations() {

            BufferedImage image = LoadSave.PlayerSprite(LoadSave.PLAYER_SPRITE);
            animations = new BufferedImage[10][6];

            int rows = animations.length;
            int cols = animations[0].length;


            for (int row = 0; row < rows; row++)
                for (int col = 0; col < cols; col++)
                    animations[row][col] = image.getSubimage(col * 32, row * 32, 32, 32);

    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void pauseMovement() {
        left = false;
        right = false;
        up = false;
        down= false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
