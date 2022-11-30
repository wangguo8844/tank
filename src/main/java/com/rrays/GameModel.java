package com.rrays;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {


    Tank myTank =new Tank(200,200,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i*30 ,200,Dir.UP,Group.BAD,this));
        }
    }
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量"+bullets.size(),10,60);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,90);
        g.setColor(c);

        myTank.paint(g);

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

    public Tank getMainTank() {
        return  myTank;
    }
}
