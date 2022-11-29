package com.rrays;

import com.rrays.abstractFactory.BaseBullet;
import com.rrays.abstractFactory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {
    private static final int SPEED = 10;
    public static  int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    public boolean live = true;
    private int x,y;
    private Dir dir;
    private boolean living = true;
    private Group group = Group.BAD;
    Rectangle rect= new Rectangle();
    TankFrame tf =null;


    public Bullet(int x,int y,Dir dir, TankFrame tf, Group group) {
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y =this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live){
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        Color c= g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }



    private void move() {

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

            //update rect
            rect.x = this.x;
            rect.y=this.y;

            if (x <0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
    }

    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) return;

        //todo: 用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH,Tank.HEIGHT);
        if(rect1.intersects(rect2)) {
            tank.die();
            this.die();
            int eX = tank.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() +Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(eX,eY,tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
