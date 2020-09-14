package me.app17.flygame;

import android.graphics.Bitmap;

public class PlayerTank extends Tank implements SuperFire {

    public PlayerTank(int x, int y, Direction direction, Bitmap[] image) {
        super(x, y, direction, image);
    }

    @Override
    public void superFire() {
        for (Direction direction : Direction.values()) {
            Bullet bullet = new Bullet(x + width / 2 - GameView.bulletImage[0].getWidth() / 2,
                    y + height / 2 - GameView.bulletImage[0].getHeight() / 2, direction, enemy, GameView.bulletImage);
            bullet.setSpeed(15);

            GameActivity.getInstance().getGameView().addGameObject(bullet);
        }
    }


}

