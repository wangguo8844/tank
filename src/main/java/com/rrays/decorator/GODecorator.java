package com.rrays.decorator;

import com.rrays.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject go;

    public  GODecorator(GameObject go){
        this.x = go.x;
        this.y = go.y;
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);
}
