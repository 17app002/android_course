package me.app17.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {

    private TextView secondText;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    private int seconds;
    private boolean stop;
    private boolean stopStats;

    private Handler handler;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seconds = 0;
        stop = true;
        stopStats = stop;
        findViews();

        //控管訊息傳遞
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        secondText.setText(String.valueOf(msg.obj));
                        break;
                }
            }
        };

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            stop = savedInstanceState.getBoolean("stop");
            stopStats = savedInstanceState.getBoolean("stopStats");
            updateTimer(seconds);
        }

        startTimer();


        //handler = new Handler();

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                int hour = seconds / 3600;
//                int minutes = (seconds % 3600) / 60;
//                int sec = seconds % 60;
//                String time = String.format("%d:%02d:%02d", hour, minutes, sec);
//                secondText.setText(time);
//                System.out.println(Thread.currentThread());
//                if (!stop) {
//                    seconds++;
//                }
//                //每一秒呼叫自己一次
//                handler.postDelayed(this, 1000);
//            }
//        });


        //Log.d(TAG, Thread.currentThread().toString());


        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("stop", stop);
        outState.putBoolean("stopStats", stopStats);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        stopStats = stop;
        stop = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        stop = stopStats;
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }


    private void findViews() {
        secondText = findViewById(R.id.second_text);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);
        resetBtn = findViewById(R.id.reset_btn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    //更新秒數
    private String updateTimer(int seconds) {
        int minutes = seconds / 60;
        int hour = minutes / 60;
        minutes = minutes % 60;
        int leftSeconds = seconds % 60;

        String timer = String.format("%d:%02d:%02d", hour, minutes, leftSeconds);
        Message message = new Message();
        message.what = 1;
        message.obj = timer;

        handler.sendMessage(message);

        return timer;
    }


    private void startTimer() {
        new Thread(new Runnable() {
            public void run() {
                //Log.d(TAG, Thread.currentThread().toString());
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (stop) {
                        continue;
                    }
                    seconds++;

//                    //使用runnable
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            secondText.setText(timer);
//                        }
//                    });
                    String timer = updateTimer(seconds);
                    Log.d(TAG, timer);
                }
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                stop = false;
                break;
            case R.id.stop_btn:
                stop = true;
                break;
            case R.id.reset_btn:
                stop = true;
                seconds = 0;
                updateTimer(seconds);
                break;
        }
    }
}