package me.app17.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


enum WinState {
    PLAYER_WIN("玩家勝利!"),
    COMPUTER_WIN("電腦勝利!"),
    EVEN("平手");

    private String message;

    WinState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton scissorsIbn;
    private ImageButton rockIbn;
    private ImageButton paperIbn;
    private ImageView playImg;
    private ImageView computerImg;
    private TextView roundText;

    private Button startBtn;
    private Button quitBtn;

    //玩家出拳
    private Mora player;
    //電腦出拳
    private Mora computer;
    //勝負情況
    private WinState winState;
    //遊戲局數
    private int round;
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
     *
     * @return
     */
    private int getComputerMora() {
        return new Random().nextInt(Mora.PAPER + 1);
    }


    /**
     * 初始遊戲
     */
    public void initGame() {
        player = null;
        computer = null;
        winState = null;
        round = 0;
        playerWinCount = 0;
        computerWinCount = 0;
    }


    public void findViews() {
        scissorsIbn = findViewById(R.id.scissors_ibn);
        rockIbn = findViewById(R.id.rock_ibn);
        paperIbn = findViewById(R.id.paper_ibn);
        startBtn = findViewById(R.id.start_btn);
        quitBtn = findViewById(R.id.quit_btn);
        //玩家出拳
        playImg = findViewById(R.id.player_img);
        playImg.setVisibility(View.INVISIBLE);
        computerImg = findViewById(R.id.computer_img);
        computerImg.setVisibility(View.INVISIBLE);
        roundText = findViewById(R.id.round_text);

        scissorsIbn.setOnClickListener(this);
        rockIbn.setOnClickListener(this);
        paperIbn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);
    }


    /***
     * 開始遊戲
     * 等待電腦出拳
     */
    public void startGame() {
        round++;
        roundText.setText("ROUND " + round);

        //如果玩家沒有出拳(強制出剪刀)
        if (player == null) {
            player = new Mora(Mora.SCISSOR);
        }

        //玩家出拳
        playImg = findViewById(R.id.player_img);
        playImg.setImageResource(getMoraImageResId(player.getIndex()));
        playImg.setVisibility(View.VISIBLE);
        //電腦出拳
        computer = new Mora(getComputerMora());
        computerImg = findViewById(R.id.computer_img);
        computerImg.setImageResource(getMoraImageResId(computer.getIndex()));
        computerImg.setVisibility(View.VISIBLE);

        //判斷勝負
        winState = Mora.getWinState(player, computer);
        if (winState.equals(WinState.COMPUTER_WIN)) {
            playerWinCount++;
        } else if (winState.equals(winState.equals(WinState.PLAYER_WIN))) {
            computerWinCount++;
        }

        Toast.makeText(this, winState.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 取得出拳圖形id
     *
     * @param index
     * @return
     */
    public int getMoraImageResId(int index) {
        int[] resId = {R.drawable.scissors, R.drawable.rock, R.drawable.paper};
        if (index >= resId.length) {
            index = 0;
        }

        return resId[index];
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.scissors_ibn:
                Log.d(TAG, getResources().getString(R.string.scissors));
                //((ImageButton) view).getDrawable();
                player = new Mora(Mora.SCISSOR);
                startGame();
                break;
            case R.id.rock_ibn:
                Log.d(TAG, getResources().getString(R.string.rock));
                player = new Mora(Mora.ROCK);
                startGame();
                break;
            case R.id.paper_ibn:
                Log.d(TAG, getResources().getString(R.string.paper));
                player = new Mora(Mora.PAPER);
                startGame();
                break;
            case R.id.start_btn:
                Log.d(TAG, getResources().getString(R.string.start));
                break;
            case R.id.quit_btn:
                Log.d(TAG, getResources().getString(R.string.quit));
                break;
        }


//        if(view instanceof ImageView){
//            startGame();
//        }
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