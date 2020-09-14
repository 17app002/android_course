package me.app17.flygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class EnemyTank extends Tank {
    EnemyTank(int x, int y, Direction direction, Bitmap[] image) {
        super(x, y, direction, true, image);
    }


    //取得新方向
    public void getNewDirection() {
        Random rand = new Random();
        //方向(上下左右)
        int dir = rand.nextInt(Direction.values().length);

        if (dir <= Direction.RIGHT.ordinal()) {
            dirs[dir] = true;
        } else {
            if (dir == Direction.UP_LEFT.ordinal()) {
                dirs[0] = true;
                dirs[2] = true;
            } else if (dir == Direction.UP_RIGHT.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            } else if (dir == Direction.DOWN_LEFT.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            } else if (dir == Direction.DOWN_RIGHT.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public boolean collision() {
        if (super.collision()) {
            getNewDirection();
            return true;
        }

        return false;
    }


    public void ai() {

        Random rand = new Random();

        if (rand.nextInt(20) == 1) {
            dirs = new boolean[4];
            //停止
            if (rand.nextInt(2) == 1) {
                return;
            }
            getNewDirection();
        }
        //開火
        if (rand.nextInt(50) == 1) {
            fire();
        }
    }

    @Override
    public void draw(Canvas g) {
        ai();
        super.draw(g);

    }
}
