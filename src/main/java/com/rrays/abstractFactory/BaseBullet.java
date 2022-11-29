package com.rrays.abstractFactory;

import com.rrays.Dir;
import com.rrays.Group;
import com.rrays.Tank;
import com.rrays.TankFrame;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);

}
