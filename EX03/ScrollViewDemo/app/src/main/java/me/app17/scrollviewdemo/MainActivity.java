package me.app17.scrollviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView countTv;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private Button addBtn;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTv = findViewById(R.id.count_tv);
        scrollView = findViewById(R.id.scroll_view);
        linearLayout = findViewById(R.id.scroll_layout);
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        count++;
        countTv.setText(String.valueOf(count));
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(count));
        linearLayout.addView(textView);
        //進行捲動
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });


    }
}