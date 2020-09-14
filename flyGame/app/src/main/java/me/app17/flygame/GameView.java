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

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import me.app17.flygame.gameobject.GameObject;

public class GameView extends SurfaceView implements Runnable {
    private final int FPS = 60;
    private Thread thread;
    private boolean isPlaying;
    private Paint paint;

    private Point screenSize;
    private Point screenRatio;
    private static View instance;


    public Bitmap[] iTankImage = new Bitmap[8];
    public static Bitmap[] eTankImage = new Bitmap[8];
    public static Bitmap[] bulletImage = new Bitmap[8];
    private Tank playerTank;
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();


    public int getScreenWidth(){
        return screenSize.x;
    }


    public int getScreenHeight(){
        return screenSize.x;
    }

    public List<GameObject> getGameObject() {
        return objects;
    }

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

//        background = new Background(0, 0, GameActivity.getImage("background.png"));
//        blood = new Blood(100, 200,GameActivity.getImage("blood.png"));


        String[] sub = {"u.png", "d.png", "l.png", "r.png", "lu.png", "ru.png", "ld.png", "rd.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = GameActivity.getImage("itank" + sub[i]);
            eTankImage[i] = GameActivity.getImage("etank" + sub[i]);
            bulletImage[i] = GameActivity.getImage("missile" + sub[i]);
        }


        playerTank = new PlayerTank(400, 50, Direction.DOWN, iTankImage);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                objects.add(new EnemyTank(320 + j * 100, 450 + 100 * i, Direction.UP, eTankImage));
            }
        }

        objects.add(playerTank);
    }

    //增加物件
    public void addGameObject(GameObject object) {
        objects.add(object);
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


        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        for (GameObject object : objects) {
            object.draw(canvas);
        }

//        for (GameObject object : objects) {
//            if (!object.isAlive()) {
//                objects.remove(object);
//            }
//        }

        System.out.println(objects.size());
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
