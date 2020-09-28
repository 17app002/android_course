package me.app17.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    private int seconds;
    private boolean stop;
    private TextView secondText;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seconds = 0;
        stop = true;
        findViews();
        new Thread(this).start();
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
                stop = false;
                break;
            case R.id.stop_btn:
                stop = true;
                break;
            case R.id.reset_btn:
                stop = true;
                seconds = 0;
                break;
        }
    }

    @Override
    public void run() {

        while (true) {
            if (stop) {
                continue;
            }

            try {
                Thread.sleep(1000);
                int hour = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

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
}

