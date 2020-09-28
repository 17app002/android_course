package me.app17.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    private final static int START = 0;
    private final static int STOP = 1;
    private final static int RESET = 2;

    private int seconds;
    private int status;
    private int oldStatus;

    private TextView secondText;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seconds = 0;
        status = STOP;
        //紀錄目前狀態
        oldStatus=status;
        findViews();

        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            status=savedInstanceState.getInt("status");
            oldStatus=savedInstanceState.getInt("oldStatus");
        }


        new Thread(this).start();
        //runTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //儲存狀態
        oldStatus=status;
        status=STOP;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //將狀態回存，繼續計數
        status=oldStatus;
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        //儲存狀態
//        oldStatus=status;
//        status=STOP;
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        //將狀態回存，繼續計數
//        status=oldStatus;
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putInt("status",status);
        outState.putInt("oldStatus",oldStatus);
    }

    public void findViews() {
        secondText = findViewById(R.id.second_text);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);
        resetBtn = findViewById(R.id.reset_btn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                status = START;
                break;
            case R.id.stop_btn:
                status = STOP;
                break;
            case R.id.reset_btn:
                status = RESET;
                seconds = 0;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void run() {

        while (true) {
            if (status == STOP) {
                continue;
            }

            try {
                //重置碼表
                if (status == RESET) {
                    status = STOP;
                    seconds = 0;
                    final String time = String.format("%d:%02d:%02d", 0, 0, 0);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            secondText.setText(time);
                        }
                    });
                    continue;
                }
                Thread.sleep(1000);
                int hour = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                System.out.println(Thread.currentThread());
                final String time = String.format("%d:%02d:%02d", hour, minutes, sec);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        secondText.setText(time);
                    }
                });
                seconds++;


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format("%d:%02d:%02d", hour, minutes, sec);
                secondText.setText(time);
                System.out.println(Thread.currentThread());
                if (status == START) {
                    seconds++;
                }
                //每一秒呼叫自己一次
                handler.postDelayed(this, 1000);
            }
        });
    }
}

