package me.app17.handlerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //直接實作並覆寫handleMessage方法
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    textView.setText("開始下載....");
                    break;
                case 1:
                    textView.setText("下載完畢!");
                    break;
            }

        }
    };


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.hello_text);

        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        textView.setText("第三種方法..");

                    }
                });
            }
        }).start();


        final Handler handler2 = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("第二種方法..");
                    }
                });
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                try {
                    Thread.sleep(3000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                handler.sendEmptyMessage(1);
            }
        }).start();

    }
}