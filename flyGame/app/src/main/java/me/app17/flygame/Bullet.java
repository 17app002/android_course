package me.app17.flygame;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.List;

import me.app17.flygame.gameobject.GameObject;
import me.app17.flygame.gameobject.MoveObject;

public class Bullet extends MoveObject {

    public Bullet(int x, int y, Direction direction, boolean enemy, Bitmap[] image) {
        super(x, y, direction, enemy, image);
        speed = 10;
    }


    @Override
    public boolean collision() {
        if (collisionBound(new Rect(0, 0, GameActivity.getInstance().getGameView().getScreenWidth(),
                GameActivity.getInstance().getGameView().getScreenHeight()))) {
            alive = false;
            return true;
        }

        List<GameObject> objects =  GameActivity.getInstance().getGameView().getGameObject();

        for (GameObject object : objects) {
            //子彈不互相抵銷(火花也不偵測)
            if (object == this || object instanceof Bullet /*|| object instanceof Explosion*/) {
                continue;
            }
            //偵測坦克碰撞
            if (object instanceof Tank) {
                if (((Tank) object).isEnemy() == enemy) {
                    continue;
                }
            }
            //碰撞後消失
            if (Rect.intersects(getRectangle(),object.getRectangle())) {
                alive = false;
                //敵方坦克消失
                if (object instanceof EnemyTank) {
                    object.setAlive(false);
                }

//                //產生爆炸火花
//                TankGame.gameClient.addGameObject(new Explosion(x + (GameClient.explosionImage[0].getWidth(null) - width) / 2,
//                        y + (GameClient.explosionImage[0].getHeight(null) - height) / 2, GameClient.explosionImage));
                return true;
            }
        }


        return false;
    }
}
