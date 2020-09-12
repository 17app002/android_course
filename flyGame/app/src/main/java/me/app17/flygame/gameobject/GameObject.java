package me.app17.flygame.gameobject;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;


import me.app17.flygame.GameView;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int oldX;
    protected int oldY;
    protected int speed;
    protected int step;

    protected boolean stopped;
    protected boolean enemy;
    protected boolean live;
    protected Bitmap image;
    protected Bitmap[] images;
    protected int width;
    protected int height;

    protected Point screenSize;
    protected Paint paint;

    public GameObject() {
        enemy = false;
        live = false;
        stopped = false;
        step = -1;
        screenSize = GameView.getInstance().getScreenSize();
        paint = GameView.getInstance().getPaint();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameObject(int x, int y, Bitmap image) {
        this();
        this.image = image;
        //image = Bitmap.createScaledBitmap(image, screenSize.x, screenSize.y, false);
        image = Bitmap.createBitmap(image);

        this.x = x;
        this.y = y;
        this.image = image;
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
    }


    public GameObject(int x, int y, Bitmap[] images) {
        this();
        this.x = x;
        this.y = y;
        this.images = images;
        if (images != null) {
            width = images[0].getWidth();
            height = images[1].getHeight();
        }
    }


    public abstract void draw(Canvas g);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Rect getRectangle() {
        return new Rect(x, y, x + image.getWidth(),
                y + image.getHeight());
    }


    public void update(Canvas g) {
        if (live)
            draw(g);
    }
}
