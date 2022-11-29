package com.rrays.abstractFactory;

import com.rrays.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {


    public int x,y;
    Dir dir = Dir.DOWN;
    Rectangle rect= new Rectangle();
    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();

    FireStrategy fireStrategy=new FourDirFireStrategy();

    private Random random = new Random();

    TankFrame tf ;
    private boolean living = true;

    private boolean moving = false;
    Group group= Group.BAD;




    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        RectTank.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        RectTank.HEIGHT = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x=this.x;
        rect.y=this.y;

        if (group == Group.GOOD) {
            String goodFS = (String)PropertyMgr.get("goodFS");
            try {
                fireStrategy=(FireStrategy) Class.forName(goodFS).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
//            fireStrategy= new DefaultFireStrategy();
            fireStrategy = (t)->{
                new DefaultFireStrategy();
            };
        }

    }

    public void paint(Graphics g) {

        if(!living) tf.tanks.remove(this);

        Color c =g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED :Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(c);
        move();
    }

    private void move() {
        if(!moving)
            switch (dir) {
                case LEFT:
                    x -=SPEED;
                    break;
                case RIGHT:
                    x +=SPEED;
                    break;
                case DOWN:
                    y +=SPEED;
                    break;
                case UP:
                    y -=SPEED;
                    break;
            }



        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95)
             randomDir();

        boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x<0) x=0;
        if (this.y <30) y=30;
        if (this.x > TankFrame.GAME_WIDTH- RectTank.WIDTH) x=TankFrame.GAME_WIDTH- RectTank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT- RectTank.HEIGHT) y = TankFrame.GAME_HEIGHT- RectTank.HEIGHT;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];

    }

//    public void fire() {
//        fireStrategy.fire(this);
//    }
    public void fire() {
        int bX = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + RectTank.HEIGHT/2 - Bullet.HEIGHT/2;

        tf.bullets.add(new Bullet(bX, bY, this.dir,this.tf,this.group));
    }

    public void die() {
        this.living = false;
    }
}

