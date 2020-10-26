package me.app17.ch05_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backBtn;
    private TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        backBtn = findViewById(R.id.back_btn);
        nameTv = findViewById(R.id.name_tv);

        Bundle bundle = getIntent().getExtras();
        nameTv.setText(bundle.getString("name"));


        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
