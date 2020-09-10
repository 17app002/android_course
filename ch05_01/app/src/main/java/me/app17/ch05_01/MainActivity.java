package me.app17.ch05_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.app17.ex05_01.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button jumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
        jumpBtn = findViewById(R.id.jump_btn);
        jumpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
       startActivity(intent);
    }

    @Override
    protected void onStart() {

        String LogName="tag";
        super.onStart();
        System.out.println("onStart");
//        Log.v(LogName, "This is Verbose.");
//        Log.d(LogName, "This is Debug.");
//        Log.i(LogName, "This is Information");
//        Log.w(LogName, "This is Warnning.");
//        Log.e(LogName, "This is Error.");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }


}
