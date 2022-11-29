package com.rrays;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i*30 ,200,Dir.UP, tf,Group.BAD));
        }
        while(true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
