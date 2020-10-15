package me.app17.ch05_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.app17.ex05_01.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        backBtn=findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivityForResult(intent,0);
    }
}
