package me.app17.flygame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import me.app17.flygame.gameobject.GameObject;

public class Background extends GameObject {


    public Background(int x, int y, Bitmap image) {
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
