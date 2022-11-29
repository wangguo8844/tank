package com.rrays;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        int bX = t.x +t.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y +t.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for(Dir dir: dirs) {
           t.tf.gf.createBullet(bX, bY, t.dir,t.tf,t.group);
        }
    }
}
