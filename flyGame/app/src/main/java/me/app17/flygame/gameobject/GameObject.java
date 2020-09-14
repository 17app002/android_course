package me.app17.flygame.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public abstract class GameObject {
    protected int oldX;
    protected int oldY;
    protected int x;
    protected int y;

    protected int width;
    protected int height;
    protected Bitmap[] image;
    protected boolean alive;
    protected int frame;

    public GameObject(int x, int y, Bitmap[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.alive = true;
        width = image[0].getWidth();
        height = image[0].getHeight();
    }

    //取得物件面積
    public Rect getRectangle() {
        return new Rect(x, y, x+width, y+height);
    }

    public abstract void draw(Canvas g);

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Bitmap[] getImage() {
        return image;
    }

    public boolean isAlive() {
        return alive;
    }
}
