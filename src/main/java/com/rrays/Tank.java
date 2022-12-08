package com.rrays;

import com.rrays.strategy.DefaultFireStrategy;
import com.rrays.strategy.FireStrategy;
import com.rrays.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{

    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));
    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();

    Rectangle rect= new Rectangle();
    private Random random = new Random();

    public int x,y;
    public Dir dir = Dir.DOWN;

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    FireStrategy fireStrategy=new FourDirFireStrategy();

    private boolean living = true;
    private boolean moving = false;
    public Group group= Group.BAD;
    FireStrategy fs;
    public GameModel gm;


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
        Tank.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Tank.HEIGHT = HEIGHT;
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

    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
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

        if(!living) gm.remove(this);

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
        }
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
        if (this.x > TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT-Tank.HEIGHT) y = TankFrame.GAME_HEIGHT-Tank.HEIGHT;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];

    }

//    public void fire() {
//        fireStrategy.fire(this);
//    }
    public void fire() {
        int bX = this.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y +Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        gm.add(new Bullet(bX, bY, this.dir,this.gm,this.group));
    }

    public void die() {
        this.living = false;
    }
    public void stop(){
        moving=false;
    }
}

