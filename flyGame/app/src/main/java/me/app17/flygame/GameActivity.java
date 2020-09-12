package me.app17.flygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/***
 * 遊戲主要Activity
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private static GameActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance=this;
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //取得手機螢幕長寬
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        System.out.println(screenSize);



        gameView = new GameView(this, screenSize);
        setContentView(gameView);
    }

    public static GameActivity getInstance(){
        return instance;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    //取得圖片
    public static Bitmap getImage(String imageName) {
        InputStream inputStream = null;
        try {
            inputStream = instance.getAssets().open(imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        return bitmap;
    }
}
