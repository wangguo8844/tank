package com.rrays.abstractFactory;

import com.rrays.ResourceMgr;
import com.rrays.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode{


    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private boolean living = true;
    TankFrame tf = null;

    private int step = 0;


    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++],x , y ,null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        if (step >= 5) {
            tf.explodes.remove(this);
        g.setColor(c);
        }


    }

}
