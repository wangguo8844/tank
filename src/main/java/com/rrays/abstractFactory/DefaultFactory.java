package com.rrays.abstractFactory;

import com.rrays.*;

public class DefaultFactory extends GameFactory{

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x,int y,Dir dir, TankFrame tf, Group group) {
        return new RectBullet(x,y,dir,tf,group);
    }
}
