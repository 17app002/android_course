package me.app17.flygame.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


import me.app17.flygame.Direction;


public abstract class MoveObject extends GameObject {

    protected int speed;
    protected Direction direction;
    protected boolean enemy;

    public MoveObject(int x, int y, Direction direction, Bitmap[] image) {
        this(x, y, direction, false, image);
    }

    public MoveObject(int x, int y, Direction direction, boolean enemy, Bitmap[] image) {
        super(x, y, image);
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void move() {
        oldX = x;
        oldY = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }
    }

    //邊界偵測
    public boolean collisionBound(Rect rectangle) {
        boolean collision = false;
        //邊界偵測
        if (x < rectangle.left) {
            x = rectangle.left;
            collision = true;
        } else if (x > rectangle.right - width) {
            x = rectangle.right - width;
            collision = true;
        }

        if (y < 0) {
            y = rectangle.top;
            collision = true;
        } else if (y > rectangle.bottom - height) {
            y = rectangle.bottom - height;
            collision = true;
        }

        return collision;
    }

    public abstract boolean collision();


    @Override
    public void draw(Canvas g) {
        if (!alive) {
            return;
        }
        move();
        collision();

        g.drawBitmap(image[direction.ordinal()], x, y,null);
    }
}
