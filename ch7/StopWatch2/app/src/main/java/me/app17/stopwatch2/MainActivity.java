package me.app17.stopwatch2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView secondText;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    private int seconds;
    private boolean stop;

    private final String TAG = "MainActivity";

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seconds = 0;
        stop = true;
        findViews();


        startTimer();

        //控管訊息傳遞
        handler = new Handler() {
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

        Log.d(TAG, Thread.currentThread().toString());
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

    private void startTimer() {
        new Thread(new Runnable() {
            public void run() {
                Log.d(TAG, Thread.currentThread().toString());
                while (true) {
                    if (stop) {
                        continue;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (stop) {
                        continue;
                    }

                    seconds++;

                    int minutes = seconds / 60;
                    int hour = minutes / 60;
                    minutes = minutes % 60;
                    int leftSeconds = seconds % 60;

                    final String timer = String.format("%d:%02d:%02d", hour, minutes, leftSeconds);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = timer;

                    handler.sendMessage(message);

//                    //使用runnable
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            secondText.setText(timer);
//                        }
//                    });

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
                Message message = new Message();
                message.what = 1;
                message.obj = "0:00:00";
                handler.sendMessage(message);
                seconds = 0;
                break;
        }
    }
}