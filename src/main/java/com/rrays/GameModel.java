package com.rrays;

import com.rrays.chainOfResp.BulletTankCollider;
import com.rrays.chainOfResp.Collider;
import com.rrays.chainOfResp.ColliderChain;
import com.rrays.chainOfResp.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    public static final GameModel INSTANCE =new GameModel();

    static {
        INSTANCE.init();
    }

    Tank myTank ;
//    java.util.List<Bullet> bullets = new ArrayList<>();
//    java.util.List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
//    Collider collider = new BulletTankCollider();
//    Collider collider2= new TankTankCollider();
    ColliderChain chain= new ColliderChain();

    List <GameObject> objects = new ArrayList<>();

    public static GameModel getInstance(){
        return INSTANCE;
    }

    private GameModel(){ }
    private void init(){
        //初始换主站坦克
        myTank=new Tank(200,200,Dir.DOWN,Group.GOOD);
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        //初始化敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i*30 ,200,Dir.UP,Group.BAD);
        }
        //初始化墙
        add(new Wall(150, 150 ,200, 50));
        add(new Wall(500, 150 ,200, 50));
        add(new Wall(300, 300 ,50, 200));
        add(new Wall(550, 300 ,50, 200));
    }

    public void add (GameObject go){
            this.objects.add(go);
    }

    public void remove (GameObject go){
        this.objects.remove(go);
    }


    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.white);
//        g.drawString("子弹的数量"+bullets.size(),10,60);
//        g.drawString("敌人的数量"+tanks.size(),10,80);
//        g.drawString("爆炸的数量"+explodes.size(),10,90);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0 ; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
//        for (int i = 0 ; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
//        for (int i = 0 ; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }

//        for(Bullet b: bullets){
//            b.paint(g);
//        }
//        for (Tank t:tanks){
//            t.paint(g);
//        }

        //相互碰撞
        for(int i =0; i< objects.size(); i++){
            for (int j=i+1;j<objects.size();j++){
                GameObject o1 =objects.get(i);
                GameObject o2 =objects.get(j);
                chain.collide(o1,o2);
            }
        }

        //碰撞监测
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//
//            }
//            //  不知道什么问题  e.paint(g);
//        }
    }

    public Tank getMainTank() {
        return  myTank;
    }
}
