package com.rrays.chainOfResp;

import com.rrays.Bullet;
import com.rrays.GameObject;
import com.rrays.Tank;
import com.rrays.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b= (Bullet) o1;
            Wall w = (Wall) o2;

            if (b.rect.intersects(w.rect)) {
                b.die();
                return false;
            }
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2, o1);
        }
        return true;
    }

}
