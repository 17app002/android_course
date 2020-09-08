package me.app17.flygame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Image;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable {
    private final int FPS = 60;
    private Thread thread;
    private boolean isPlaying;
    private Paint paint;

    private Point screenSize;
    private Point screenRatio;
    private static View instance;


    private Background background;
    private Blood blood;

    public Bitmap[] tankImg = new Bitmap[8];
    private Tank playerTank;

    public GameView(Context context, Point screenSize) {
        super(context);
        instance = this;
        this.screenSize = screenSize;
        screenRatio = new Point();
        //取得解析度跟實際手機比例
        screenRatio.x = (int) (1920f / screenSize.x);
        screenRatio.y = (int) (1080f / screenSize.y);

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        background = new Background(0, 0, BitmapFactory.decodeResource(getResources(), R.drawable.background));
        blood = new Blood(100, 200, BitmapFactory.decodeResource(getResources(), R.drawable.blood));


        int[] tankImageId={R.drawable.itanku,R.drawable.itankd,R.drawable.itankl,R.drawable.itankr,
                R.drawable.itanklu,R.drawable.itankru,R.drawable.itankld,R.drawable.itankrd};

        for(int i=0;i<tankImg.length;i++){
            tankImg[i]=BitmapFactory.decodeResource(getResources(), tankImageId[i]);
        }

        playerTank = new Tank(400, 50, tankImg, Direction.DOWN, false);
    }

    public static GameView getInstance() {
        return (GameView) instance;
    }


    @Override
    public void run() {

        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {

    }

    private void draw() {
        if (!getHolder().getSurface().isValid())
            return;

        Canvas canvas = getHolder().lockCanvas();
        background.draw(canvas);
        blood.draw(canvas);
        playerTank.draw(canvas);





        getHolder().unlockCanvasAndPost(canvas);


    }

    public void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /***
     * 重新開始
     */
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /***
     * 暫停
     */
    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Point getScreenSize() {
        return screenSize;
    }

    public Paint getPaint() {
        return paint;
    }
}
