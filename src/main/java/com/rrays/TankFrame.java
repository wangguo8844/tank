package com.rrays;


import com.rrays.abstractFactory.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import static com.rrays.Dir.*;


public class TankFrame extends Frame {

    Tank tank =new Tank(200,200,Dir.DOWN,this,Group.GOOD);
    public List<BaseBullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();
//    Explode e = new Explode(100,100, this);
    public GameFactory gf = new RectFactory();

    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 960;

    //双缓冲
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量"+bullets.size(),10,60);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,90);
        g.setColor(c);

        tank.paint(g);

        for (int i = 0 ; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0 ; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0 ; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

//        for(Bullet b: bullets){
//            b.paint(g);
//        }
//        for (Tank t:tanks){
//            t.paint(g);
//        }

        //碰撞监测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));

            }
            //  不知道什么问题  e.paint(g);
        }

    }
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext()) {
//            Bullet b = it.next();
//            if (!b.live) it.remove();
//        }


    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    class MyKeyListener extends KeyAdapter {

        boolean dL = false;
        boolean dR = false;
        boolean dU = false;
        boolean dD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    dL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    dR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    dD = true;
                    break;
                case KeyEvent.VK_UP:
                    dU = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
            System.out.println("keypressed");

            repaint();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    dL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    dR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    dD = false;
                    break;
                case KeyEvent.VK_UP:
                    dU = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
            System.out.println("key released");
        }

        private void setMainTankDir() {
            if (!dL && !dU && !dD && !dR) tank.setMoving(false);
            else {
                tank.setMoving(true);

                if (dL) tank.setDir(LEFT);
                if (dR) tank.setDir(RIGHT);
                if (dU) tank.setDir(UP);
                if (dD) tank.setDir(DOWN);
            }
        }
    }




}
