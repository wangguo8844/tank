package com.rrays.abstractFactory;

import com.rrays.Dir;
import com.rrays.Group;
import com.rrays.Tank;
import com.rrays.TankFrame;

public class BaseTank {
    private int x;
    private int y;
    private Dir dir;
    private Group group;
    private TankFrame tf;

    public BaseTank() {
    }

    public BaseTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    @Override
    public String toString() {
        return "BaseTank{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", tf=" + tf +
                '}';
    }
}
