package com.rrays.strategy;

import com.rrays.Bullet;
import com.rrays.Tank;

public class DefaultFireStrategy implements  FireStrategy{

    @Override
    public void fire(Tank t) {
        int bX = t.x +t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y +t.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bX, bY, t.dir,t.gm,t.group);
    }
}
