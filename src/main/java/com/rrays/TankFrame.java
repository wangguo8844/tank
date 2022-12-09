package com.rrays;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import static com.rrays.Dir.*;


public class TankFrame extends Frame {

    GameModel gm =GameModel.getInstance();


//    Explode e = new Explode(100,100, this);
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
        gm.paint(g);



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
                    gm.getMainTank().fire();
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
            if (!dL && !dU && !dD && !dR) gm.getMainTank().setMoving(false);
            else {
                gm.getMainTank().setMoving(true);

                if (dL) gm.getMainTank().setDir(LEFT);
                if (dR) gm.getMainTank().setDir(RIGHT);
                if (dU) gm.getMainTank().setDir(UP);
                if (dD) gm.getMainTank().setDir(DOWN);
            }
        }
    }




}
