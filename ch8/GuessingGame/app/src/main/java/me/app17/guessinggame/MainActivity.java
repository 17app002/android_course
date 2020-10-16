package me.app17.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton scissorsIbn;
    private ImageButton rockIbn;
    private ImageButton paperIbn;
    private Button startBtn;
    private Button quitBtn;


    //玩家出拳
    private int playerMoraIndex;
    //電腦出拳
    private int computerMoraIndex;
    //遊戲局數
    private int round;
    //是否玩家勝利
    private boolean playerWin;
    //玩家勝場
    private int playerWinCount;
    //電腦勝場
    private int computerWinCount;


    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        initGame();
    }

    /**
     * 取得電腦出拳
     * 數字代表剪刀/石頭/布(0,1,2)
     *
     * @return
     */
    private int getComputerMora() {
        return new Random().nextInt(3);
    }


    /**
     * 初始遊戲
     */
    public void initGame() {
        playerMoraIndex = -1;
        computerMoraIndex = -1;
        round = 0;
        playerWin = false;
        playerWinCount = 0;
        computerWinCount = 0;
    }

    public void findViews() {

        scissorsIbn = findViewById(R.id.scissors_ibn);
        rockIbn = findViewById(R.id.rock_ibn);
        paperIbn = findViewById(R.id.paper_ibn);
        startBtn = findViewById(R.id.start_btn);
        quitBtn = findViewById(R.id.quit_btn);


        scissorsIbn.setOnClickListener(this);
        rockIbn.setOnClickListener(this);
        paperIbn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);

    }


    public void startGame() {

    }


    @Override
    public void onClick(View view) {

        ImageView playImg = findViewById(R.id.player_img);
        switch (view.getId()) {
            case R.id.scissors_ibn:
                Log.d(TAG, getResources().getString(R.string.scissors));
                ((ImageButton) view).getDrawable();
                playImg.setImageResource(R.drawable.scissors);
                break;
            case R.id.rock_ibn:
                Log.d(TAG, getResources().getString(R.string.rock));
                playImg.setImageResource(R.drawable.rock);
                break;
            case R.id.paper_ibn:
                Log.d(TAG, getResources().getString(R.string.paper));
                playImg.setImageResource(R.drawable.paper);
                break;
            case R.id.start_btn:
                Log.d(TAG, getResources().getString(R.string.start));
                break;
            case R.id.quit_btn:
                Log.d(TAG, getResources().getString(R.string.quit));
                break;
        }
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof ImageButton) {
                Log.d(TAG, view.toString());
            } else if (view instanceof Button) {
                Log.d("Button", String.valueOf(view.getId()));
            }

        }
    };
}