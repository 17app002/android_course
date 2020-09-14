package me.app17.flygame;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

import me.app17.flygame.gameobject.GameObject;
import me.app17.flygame.gameobject.MoveObject;

public abstract class Tank extends MoveObject {

    //上下左右四個方向
    protected boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction, Bitmap[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Bitmap[] image) {
        super(x, y, direction, enemy, image);
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

    public void fire() {
        GameActivity.getInstance().getGameView().addGameObject(
                new Bullet(x + width / 2 - GameView.bulletImage[0].getWidth() / 2,
                        y + height / 2 - GameView.bulletImage[0].getHeight() / 2, direction, enemy, GameView.bulletImage));
    }


    @Override
    public boolean collision() {
        if (collisionBound(new Rect(0, 0, GameActivity.getInstance().getGameView().getScreenWidth(),
                GameActivity.getInstance().getGameView().getScreenHeight()))) {
            x = oldX;
            y = oldY;
            return true;
        }

        List<GameObject> objects = GameActivity.getInstance().getGameView().getGameObject();

        for (GameObject object : objects) {
            if (object != this && Rect.intersects(this.getRectangle(),
                    object.getRectangle())) {
                x = oldX;
                y = oldY;
                return true;
            }
        }

        return false;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    private void determineDirection() {
        //上下左右
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[1] && !dirs[2]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[1] && !dirs[0] && !dirs[2]) direction = Direction.RIGHT;
    }

    @Override
    public void draw(Canvas g) {
        if (!isStop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawBitmap(image[direction.ordinal()], x, y, null);
    }

    public boolean isStop() {
        for (boolean dir : dirs) {
            if (dir) {
                return false;
            }
        }
        return true;
    }
}
