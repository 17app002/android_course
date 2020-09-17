package me.app17.layoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views = new ArrayList<>();
    }


    public void onImageClick(View view) {
        view.setVisibility(View.INVISIBLE);
        views.add(view);
    }

    public void onImage2Click(View view) {
        view.setVisibility(View.GONE);
        views.add(view);
    }


    public void onResetClick(View view) {
        if (views != null && views.size() > 0) {
            for (View v : views) {
                v.setVisibility(View.VISIBLE);
            }
        }
    }
}