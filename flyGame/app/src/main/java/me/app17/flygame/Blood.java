package me.app17.flygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;



import me.app17.flygame.gameobject.GameObject;

public class Blood extends GameObject {

    public Blood(int x, int y, Bitmap image) {
        super(x, y, image);
        live = true;
    }


    @Override
    public void draw(Canvas g) {
        if (!live)
            return;

        g.drawBitmap(image, x, y, paint);
    }

}
